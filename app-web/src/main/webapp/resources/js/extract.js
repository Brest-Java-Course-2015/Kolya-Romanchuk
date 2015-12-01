$('#datepicker').datepicker({
    format:'yyyy-mm-dd'
});

$('#btnFilter').click(function(){
    filter();
});

function filter() {
    console.log('filter');
    $.ajax({
        type: 'GET',
        url: document.location.href+"/filter/"+$('#dataFrom').val()+"/"+$('#dataBefore').val(),
        dataType: "json",
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('filter: ' + textStatus);
        }
    });
}

function drawRow(transaction) {
    var row = $("<tr />")
    $("#transactionList").append(row);
    row.append($("<td>" + transaction.checknumbersender + "</td>"));
    row.append($("<td>" + transaction.checknumberrecipient + "</td>"));
    row.append($("<td>" + transaction.summa + "</td>"));
    row.append($("<td>" + transaction.date + "</td>"));

}

function renderList(data) {
    $('#transactionList tr').remove();
    $.each(data, function (index, transaction) {
        drawRow(transaction);
    });
}
$('#datepicker').datepicker({
    format:'yyyy-mm-dd'
});
totalSumm();
$('#btnFilter').click(function(){
    if($('#dataFrom').val() == "" || $('#dataBefore').val()==""){
        alert('Не введены промежутки фильтрации');
    }else{
        filter();
        totalFilterSumm();
    }

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
            alert('Ошибка');
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

function totalSumm() {
    console.log('totalSumm');
    $.ajax({
        type: 'GET',
        url: document.location.href + "/summ",
        dataType: "json",
        success: summ,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function totalFilterSumm() {
    console.log('totalSumm');
    $.ajax({
        type: 'GET',
        url: document.location.href + "/summ/"+$('#dataFrom').val()+"/"+$('#dataBefore').val(),
        dataType: "json",
        success: summ,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function summ(data) {
    $('#totalSumm').html(data);
}
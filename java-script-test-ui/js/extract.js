var URL_TRANSACTION_LIST = "http://localhost:8080/rest/transactions";

findAll();

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: URL_TRANSACTION_LIST,
        dataType: "json",
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function drawRow(transaction) {
    var row = $("<tr />")
    $("#checkList").append(row);
    row.append($("<td>" + transaction.checknumbersender + "</td>"));
    row.append($("<td>" + transaction.checknumberrecipient + "</td>"));
    row.append($("<td>" + transaction.summa + "</td>"));
    row.append($("<td>" + transaction.date + "</td>"));
}

function renderList(data) {
    $('#checkList tr').remove();
    $.each(data, function (index, transaction) {
        drawRow(transaction);
    });
}

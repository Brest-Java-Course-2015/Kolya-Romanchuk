var PROTOCOL = "http://";
var HOST = "localhost:8080";
var URL_CHECKS_LIST = "/rest/checks";

findAll();

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: PROTOCOL+HOST+URL_CHECKS_LIST,
        dataType: "json",
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function drawRow(check) {
    var row = $("<tr />")
    $("#checkList").append(row);
    row.append($("<td>" + '<a href="#" data-identity="' + check.id_check + '">' + check.checknumber + '</a></td>'));
    row.append($("<td>" + check.summa + "</td>"));
}

function renderList(data) {
    $('#checkList tr').remove();
    $.each(data, function (index, check) {
        drawRow(check);
    });
}


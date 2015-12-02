findAll();
function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: document.location.href+"/user/count",
        dataType: "json",
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

//function drawRow(count) {
//
//}

function renderList(data) {
    $('#countUser').html(data);
//    var row = $("<span />")
//        $("#countUser").append(row);
//        row.append($("Количество пользователей: " + data));
}

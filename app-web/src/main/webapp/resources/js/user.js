console.log(login);


findAll();
function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: document.location.href+"/checklist/"+login,
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
    row.append($("<td>" + check.checknumber + "</td>"));
    row.append($("<td>" + check.summa + "</td>"));
}

function renderList(data) {
    $('#checkList tr').remove();
    $.each(data, function (index, check) {
        drawRow(check);
    });
}

function getIdUser(login) {
    console.log(login);
    console.log('getIdUser');
    $.ajax({
        type: 'GET',
        url: document.location.href+"/"+login,
        dataType: "json",
        success: inserId,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });

    return id_user;
}

function inserId(data){
$.each(data, function (index, user) {
        id_user = user.id_user;
    });
}
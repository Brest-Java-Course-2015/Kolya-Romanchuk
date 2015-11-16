var URL_USERS_LIST = "http://localhost:8080/rest/users";

findAll();

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: URL_USERS_LIST,
        dataType: "json",
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function drawRow(user) {
    var row = $("<tr />")
    $("#userList").append(row);
    row.append($("<td>" + '<a href="#" data-identity="' + user.id_user + '">' + user.id_user + '</a></td>'));
    row.append($("<td>" + user.login + "</td>"));
    row.append($("<td>" + user.lastname + "</td>"));
    row.append($("<td>" + user.firstname + "</td>"));

}

function renderList(data) {
    $('#userList tr').remove();
    $.each(data, function (index, user) {
        drawRow(user);
    });
}


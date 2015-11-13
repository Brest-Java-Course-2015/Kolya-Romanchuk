var URL_USERS = "http://localhost:8080/rest/users";
var URL_USER = "http://localhost:8080/rest/user";


findAll();

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: URL_USERS,
        dataType: "json", // data type of response
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function drawRow(user) {
    console.log('drawRow');
    var row = $("<tr />")
    $("#allUsers").append(row);
//    row.append($("<td>" + '<a href="#" data-identity="' + user.id_user + '">' + user.id_user + '</a></td>'));
    row.append($("<td>" + user.id_user + "</td>"));
    row.append($("<td>" + user.login + "</td>"));
    row.append($("<td>" + user.password + "</td>"));
    row.append($("<td>" + user.firstname + "</td>"));
    row.append($("<td>" + user.lastname + "</td>"));
}

function renderList(data) {
    console.log('renderList');
    $('#allUsers tr').remove();
    $.each(data, function (index,user) {

        drawRow(user);
    });
}

function addUser() {
    console.log('addUser');
    $.ajax({
        url:URL_USER,
        type: 'POST',
        dataType: "application/json",
        data: formToJson(),
        success: function (data, textStatus, jqXHR) {
                    alert('User created successfully');
                    $('#userId').val(data.id_user);
                    findAll();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert('addUser error: ' + textStatus);
                }
    });
}

function formToJson() {
    var id_user = $('#userId').val();
    console.log('formToJson');
    return JSON.stringify({
        "userId": id_user == "" ? null : id_user,
        "login":$('#login').val(),
        "password":$('#password').val(),
        "firstname":$('#firstname').val(),
        "lastname":$('#lastname').val()
    });

}

$('#btnSave').click(function () {
    addUser();
    return false;
//    return false;
});
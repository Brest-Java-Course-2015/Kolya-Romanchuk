var PROTOCOL = "http://";
var HOST = "localhost:8080";
var URL_USERS_LIST = "/rest/users";
var URL_NEWUSER_ADD = "/rest/user";


findAll();

//$("#addUserTable").click( function(){
//    console.log('add user');
//    header("Location newuser.html");
//})

function findAll() {
console.log(PROTOCOL+HOST+URL_USERS_LIST);
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: PROTOCOL+HOST+URL_USERS_LIST,
        dataType: "json",
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function drawRow(user) {
    var btn = document.getElementById("deleteUser")
    var row = $("<tr/>");
    $("#userList").append(row);
    row.append($("<td>" + '<a href="#" data-identity="' + user.id_user + '">' + user.id_user + '</a></td>'));
    row.append($("<td>" + user.login + "</td>"));
    row.append($("<td>" + user.lastname + "</td>"));
    row.append($("<td>" + user.firstname + "</td>"));
    row.append($("<td>" + $('<button/>').text('delete') + "</td>"));

}

function renderList(data) {
    $('#userList tr').remove();
    $.each(data, function (index, user) {
        drawRow(user);
    });
}

$('#btnAddUser').click(function(){
//    proof();
    addUser();
    clear();
});

function addUser() {
    console.log('addUser');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: PROTOCOL+HOST+URL_NEWUSER_ADD,
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('User created successfully');
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addUser error: ' + textStatus);
        }
    });
}

function formToJSON() {
    console.log('formToJSON');
    return JSON.stringify({
        "login": $('#userLogin').val(),
        "password": $('#userPassword').val(),
        "firstname": $('#userFirstname').val(),
        "lastname": $('#userLastname').val(),
    });
}

function clear(){
    console.log('clear');
    $('#userLogin').val('');
    $('#userPassword').val('');
    $('#userFirstname').val('');
    $('#userLastname').val('');
}



$('#btnAddUser').click(function(){

    addUser();
    document.location="http://"+document.location.host+"/admin";
//    document.location.replace("http://"+document.location.host+"/admin");

});

function addUser() {
    console.log('addUser');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: document.location.href+"/create",
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
        },
        error: function (jqXHR, textStatus, errorThrown) {
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
$('#btnAddUser').click(function(){

    addUser();

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
            document.location="http://"+document.location.host+"/admin";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Такой пользователь существует либо данные введены не корректно');
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
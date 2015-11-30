$('#btnAddUser').click(function(){
    addUser();
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
            document.location.href="${pageContext.request.contextPath}/admin"
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
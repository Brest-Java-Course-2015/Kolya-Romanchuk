var URL_NEWUSER_ADD = "http://localhost:8080/rest/user";
//var URL_PROOF= "http://localhost:8080/rest/user/";



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
        url: URL_NEWUSER_ADD,
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('User created successfully');
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

//function proof(){
//    console.log('proof');
//    $.ajax({
//        type:'GET',
//        dataType: "json",
//        url:"http://localhost:8080/rest/user/"+$('#userLogin').val(),
//        success: function(data){
//            if(data == null){
//                $("#responseLogin").text("Логин занят").css("color","red");
//            }else{
//                addUser();
//                clear();
//            }
//        }
//
//
//    })
}
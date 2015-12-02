$('#btnAddCheck').click(function(){
    addCheck();

});

function addCheck() {
    console.log('addCheck');
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
            aler('Такой чек существует либо данные введены не корректно')
        }
    });
}

function formToJSON() {
    console.log('formToJSON');
    return JSON.stringify({
        "checknumber": $('#ckeckNumber').val(),
        "summa": $('#checkSumma').val(),
    });
}
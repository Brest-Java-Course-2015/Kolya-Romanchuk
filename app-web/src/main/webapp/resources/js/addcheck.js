$('#btnAddCheck').click(function(){
    addCheck();
    document.location="http://"+document.location.host+"/admin";
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
        },
        error: function (jqXHR, textStatus, errorThrown) {
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
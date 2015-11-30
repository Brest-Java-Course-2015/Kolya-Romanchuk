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
            document.location.href="${pageContext.request.contextPath}/admin"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addCheck error: ' + textStatus);
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
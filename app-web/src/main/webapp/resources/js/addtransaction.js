$('#btnAddTransaction').click(function(){
    addTransaction();
});

function addTransaction() {
    console.log('addTransaction');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: document.location.href+"/create",
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            document.location.href="${pageContext.request.contextPath}/user"
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addTransaction error: ' + textStatus);
        }
    });
}

function formToJSON() {
    console.log('formToJSON');
    return JSON.stringify({
        "checknumbersender": $('input[name=inlineRadioOptions]').val(),
        "checknumberrecipient": $('#checkRecipient').val(),
        "summa": $('#summa').val(),
    });
}


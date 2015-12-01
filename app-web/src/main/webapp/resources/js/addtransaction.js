$('#btnAddTransaction').click(function(){
    addTransaction();
});

function addTransaction() {
    console.log($('input[name=inlineRadioOptions]:checked').val());
    console.log('addTransaction');
    if($('input[name=inlineRadioOptions]:checked').val() == $('#checkRecipient').val()){
        location.reload();
    }else {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: document.location.href+"/create",
            dataType: "json",
            data: formToJSON(),
            success: function (data, textStatus, jqXHR) {
                location.reload();

            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('addTransaction error: ' + textStatus);
            }
        });
    }
}

function formToJSON() {
    console.log('formToJSON');
    return JSON.stringify({
        "checknumbersender": $('input[name=inlineRadioOptions]:checked').val(),
        "checknumberrecipient": $('#checkRecipient').val(),
        "summa": $('#summa').val(),
    });
}


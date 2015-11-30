function delUser(id_user){
    console.log('delUser');
    $.ajax({
        type:'DELETE',
        url:document.location.href+"/"+id_user+"/deleteuser",
        success: function (data) {
                    location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
                    alert('delUser error: ' + textStatus);
        }

    })
}
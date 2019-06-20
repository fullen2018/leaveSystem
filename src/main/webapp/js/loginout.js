$(function () {
    //查询
    $("#loginout_btn").click(function (){

        $.ajax({
            type: 'get',
            url: "/44551/loginout",
            dataType: 'json',
            success: function(data){
              window.location.href = "/";
            }
        })
    });
});
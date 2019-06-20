$(function () {
    $("#btn_submit").click(function(){

        var password = $("#password").val();
        var repassword = $("#repassword").val();
        var token = JSON.parse(localStorage.getItem("token"));
        console.log("修改密码  token.no:"+token.no);
        console.log("修改密码  token.roleId:"+token.roleId);

        if(validate(password,repassword)){
            $.ajax({
                type: "put",
                url: '/44551/resetpassw',
                data: {
                    "id": token.no,
                    "password": password,
                    "role": token.roleId
                },
                dataType: "json",
                success: function (data) {
                    if (data.code == 1) {
                        alert("修改成功");
                        window.location.href="login.html";
                    }
                    else {
                        alert("修改失败");
                    }
                },
                error: function () {
                    alert("修改失败");
                }
            });
        }
});

    $("#back_btn").click(function(){
        window.history.back();
    });
});

function validate(password,repassword) {
    if(password == "" || repassword == ""){
        alert("请输入新密码");
        return false;
    }
    if(password != repassword){
        alert("两次输入的密码不一致");
        return false;
    }
    return true;
}

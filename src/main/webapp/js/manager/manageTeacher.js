// 所有的课程
var allClass;

$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();


    //删除
    $("#btn_delete").click(function (){
        var temp= $("#supplierTable").bootstrapTable('getSelections');
        if(temp.length<=0) {
            alert("请至少选中一行")
        } else {
            var putTemp = new Array();
            var bb = "";
            for(var i=0;i<temp.length;i++){
               /* putTemp[i]=temp[i].id;
                var aa = temp[i].id;*/
                bb =bb+ "ids="+temp[i].teacherId+"&";
            }
            bb = bb.substr(0,bb.length-1);
            console.log(bb);
            $.ajax({
                type: "DELETE",
                url: "/teacher/deletes"+"?"+ bb,
               /* data: {"ids": bb},*/
                dataType: "json",
                success: function(data) {
                    if(data.code == "200"){
                        alert("删除成功");
                        window.location.href="manageTeacher.html";
                    }
                    else{
                        alert("删除失败");
                        window.location.href="manageTeacher.html";
                    }
                },
                error: function() {
                    alert("删除失败");
                    window.location.href="manageTeacher.html";
                }
            });
        }
    });

    //编辑弹框出现
    $("#btn_edit").click(function(){
       var temp= $("#supplierTable").bootstrapTable('getSelections');
       if(temp.length<=0){
           alert("请至少选中一行");
       }else if(temp.length==1){

           $("#supplierEdit").modal({show: true});
           //向地址下拉框里面注入数据
           var str;
           str+='<option value="">'+"--请选择--"+'</option>'
           for(var i = 0;i<allClass.length;i++){
               str+='<option value='+allClass[i].classId+'>'+allClass[i].className+'</option>'
           }
           $(".selectpickerEdit").html(str);
           //   $(".selectpickerEdit" ).selectpicker('refresh');


           //勾选id对应的那条数据内容注入到弹框里面
           $("#teacherNameEdit").val(temp[0].teacherName);
           $(".selectpickerEdit").val(temp[0].classId);

           //提交
           $("#btn_submit_Edit").click(function () {
               var id = temp[0].teacherId;
               var teacherName = $("#teacherNameEdit").val();
               var classId = $(".selectpickerEdit").val();


               if(validateEdit(teacherName,classId)){
                   $.ajax({
                       type: "put",
                       url: '/teacher/update',
                       data: {
                           "teacherId": id,
                           "teacherName": teacherName,
                           "classId": classId
                       },
                       dataType: "json",
                       success: function (data) {
                           if (data.code == 200) {
                               alert("修改成功");
                               $("#supplierEdit").modal({show: false});
                               window.location.href="manageTeacher.html";
                           }
                           else {
                               alert("修改失败");
                               $("#supplierEdit").modal({show: false});
                               window.location.href="manageTeacher.html";
                           }
                       },
                       error: function () {
                           alert("修改失败");
                           $("#supplierEdit").modal({show: false});
                           window.location.href="manageTeacher.html";
                       }
                   });
               }
                else{
                  // 输入的信息不完整
               }

           });
       }else{
           alert('最多只能选择一行');
       }
    });


    //新增弹框出现
    $("#btn_add").click(function (){
       $("#supplierAdd").modal({show:true});

       //向地址下拉框里面注入数据
        var str;
        str+='<option value="">'+"--请选择--"+'</option>'
        for(var i = 0;i<allClass.length;i++){
            str+='<option value='+allClass[i].classId+'>'+allClass[i].className+'</option>'
        }
        $(".selectpicker").html(str);
    });
    $("#btn_submit").click(function (){
        var teacherName = $("#teacherName").val();
        var classId = $(".selectpicker").val();
        var password = $("#password").val();
        var repassword = $("#repassword").val();

        if(validateAddForm(teacherName,classId,password,repassword)){
            console.log("add");
            $.ajax({
                type: "post",
                url: '/teacher/add',
                data: {
                    "teacherName": teacherName,
                    "classId": classId,
                    "teacherPassword": password,
                    "roleId":2
                },
                dataType: "json",
                success: function (data) {
                    if(data.code==200) {
                        alert("新增成功");
                        $("#supplierAdd").modal({show:false});
                        window.location.href="manageTeacher.html";
                    }
                    else{
                        alert("新增失败");
                        $("#supplierAdd").modal({show:false});
                        window.location.href="manageTeacher.html";
                    }
                },
                error: function () {
                    alert("新增失败");
                    $("#supplierAdd").modal({show:false});
                    window.location.href="manageTeacher.html";
                }
            });
        }
    });

});

/*获取所有的班级*/
function getAllClass() {
    $.ajax({
        type:"GET",
        url:"/class/findAllClass",
        async:true,
        dataType: 'json',
        success:function (data) {
            console.log(data)
            allClass = data.data;
            initSearchInput(allClass);
        }
    });
}

/*初始化搜索框*/
function initSearchInput(allClass) {
    //向地址下拉框里面注入数据
    var str = null;
    str+='<option value="">'+"--所有班级--"+'</option>'
    for(var i = 0;i<allClass.length;i++){
        str+='<option value='+allClass[i].classId+'>'+allClass[i].className+'</option>'
    }
    $(".searGrade").html(str);
}


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        // 获取所有的班级
        getAllClass();

        $('#supplierTable').bootstrapTable({
            url: '/teacher/list',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [5,10,20],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 3,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 526,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            responseHandler: function(result) {
                return {
                    //总页数,前面的key必须为"total"
                    total : result.amount,

                    //行数据，前面的key要与之前设置的dataField的值一致.
                    data : result.data
                };
            },
            onLoadSuccess: function(backDate) {
                console.log(backDate.data);
                $('#supplierTable').bootstrapTable('removeAll');

                $('#supplierTable').bootstrapTable('append',backDate.data);
            },

            columns: [{
                checkbox: true
            }, {
                field: 'teacherId',
                title: '工作号',
            },{
                field: 'teacherName',
                title: '姓名',
            },{
                field: 'clazz.className',
                title: '班级名称',
            }]
        });
    };

    //查询
    $("#btn_query").click(function (){
        console.log($("#Name").val());

        teacherName = $("#searName").val();
        teacherId = $("#searNo").val();
        classId = $("#searGrade").val();
        $.ajax({
            type: 'get',
            url: "/teacher/list",
            dataType: 'json',
            data: {
                limit: 10,   //页面大小
                curPage: 1,  //页码
                teacherName:teacherName ,
                teacherId:teacherId ,
                classId:classId
            },
            success: function(data){
                console.log(data);
                total:data.amount;
                $('#supplierTable').bootstrapTable('removeAll');

                $('#supplierTable').bootstrapTable('append',data.data);
            },
        })
    });

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            curPage: parseInt(params.offset)/parseInt(params.limit)+1,  //页码
            // goodsName: $("#Name").val()  //名称
        };
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};

/*添加数据校验*/
function validateAddForm(studentName,classId,password,repassword) {
    if(studentName == "" || classId == "" || password == "" || repassword == ""){
        alert("请填写必要的信息");
        return false;
    }

    if(password != repassword){
        alert("两次输入的密码不一样");
        return false;
    }

    return true;
}

/*修改校验*/
function validateEdit(studentName,classId) {
    if(studentName == "" || classId == ""){
        alert("请填写必要的信息");
        return false;
    }

    return true;
}
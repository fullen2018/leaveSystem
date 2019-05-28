var maxLimtDays;
$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    //删除
    $("#btn_delete").click(function (){
        var temp= $("#orderTable").bootstrapTable('getSelections');
        if(temp.length<=0) {
            alert("请至少选中一行")
        } else {
            var putTemp = new Array();
            var content = "";
            for(var i=0;i<temp.length;i++){
                content += "ids="+temp[i].leaveId+"&";
            }
            $.ajax({
                type: "delete",
                url: "/leave/deletes?"+ content.substr(0,content.length-1),
                /* data: {"ids": bb},*/
                dataType: "json",
                success: function(data) {
                    if(data.code == 200){
                        alert("删除成功");
                        window.location.reload();
                    }
                    else{
                        alert("删除失败");
                    }
                },
                error: function() {
                    alert("连接失败");
                }
            });
        }
    });

    //查询
    $("#btn_query").click(function (){
        var token = JSON.parse(localStorage.getItem("token"));
        $.ajax({
            type: 'get',
            url: "/leave/list",
            dataType: 'json',
            data: {
                limit: 10,   //页面大小
                curPage: 1,  //页码
                status: $("#statusSelect").val(),
                studentId:token.no
            },
            success: function(data){
                $('#orderTable').bootstrapTable('removeAll');
                $('#orderTable').bootstrapTable('append', data.data);
            }
        })
    });

    //新增弹框出现
    $("#btn_add").click(function (){
        $("#leaveAdd").modal({show:true});
    });

    $("#btn_submit_add").click(function (){
        var cause = $("#cause").val();
        var startTime = $("#startTimeAdd").val();
        var endTime = $("#endTimeAdd").val();

        if(validate(cause,startTime,endTime)){

            // 计算请假的天数
            var startTM = new Date(startTime);
            var endTM = new Date(endTime);
            var totalDay = (endTM-startTM)/(60*60*24*1000) + 1;
            console.log("请假天数",totalDay);
            if(totalDay < 1 || totalDay > maxLimtDays){
                alert("请假日期需在"+maxLimtDays+"天内");
                return;
            }
            var token = JSON.parse(localStorage.getItem("token"));
           console.log("得到"+token);
            $.ajax({
                type: "post",
                url: '/leave/add',
                data: {
                    "studentId":token.no,
                    "cause": cause,
                    "startTime": startTime,
                    "endTime": endTime,
                    "totalDay": totalDay,
                    "status":1
                },
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if(data.code==200) {
                        alert("新增成功");
                        $("#supplierAdd").modal({show:false});
                        window.location.href="studentIndex.html";
                    }
                    else{
                        alert("新增失败");
                        $("#supplierAdd").modal({show:false});
                        window.location.href="studentIndex.html";
                    }
                },
                error: function () {
                    alert("新增失败");
                    $("#supplierAdd").modal({show:false});
                    window.location.href="studentIndex.html";
                }
            });
        }
        else {
            alert("新增失败\n请输入完整信息！！")
        }

    });
});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#orderTable').bootstrapTable({
            url: '/leave/list',         //请求后台的URL（*）
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
            pageList: [5, 10],         //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
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
            onLoadSuccess: function(data) {
                $('#orderTable').bootstrapTable('removeAll');
                $('#orderTable').bootstrapTable('append',data.data);
            },
            columns: [{
                checkbox: true
            }, {
                field: 'leaveId',
                title: '假条编号'
            }, {
                field: 'startTime',
                title: '开始时间',
                // visible:false
            }, {
                field: 'endTime',
                title: '结束时间'
            }, {
                field: 'totalDay',
                title: '天数'
            },{
                field: 'status',
                title: '状态',
                formatter:function(value,row,index){
                    var element = "";
                    if(value == 1) {
                        element = '<span style="color:#0000ff">'+'审批中'+'</span>';
                    }else if(value == 2){
                        element = '<span style="color:#00ff00">'+'通过'+'</span>';
                    }else if(value == 3) {
                        element = '<span style="color:#FF0000">'+'未通过'+'</span>';
                    }else{
                        element = '<span>'+value+'</span>';
                    }
                    return element;
                }
            }, {
                field: 'tool',
                title: '操作',
                align: 'center',
                formatter:function(value,row,index){
                    var element =
                        "<a class='edit' href='../html/leaveDetail.html?id="+row.leaveId +"'>详情</a> ";
                    return element;
                }
            }
            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var token = JSON.parse(localStorage.getItem("token"));
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            curPage: parseInt(params.offset)/parseInt(params.limit)+1,  //页码
            studentId:token.no
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

var validate = function (cause,startTime,endTime) {
    if(cause == "" || startTime == "" || endTime == ""){
        return false;
    }
    return true;
};

function doData(data) {
    for(var i = 0; i < data.length; i++){
        if(data[i].type == 2){
            maxLimtDays = data[i].content;
        }
    }
}

$.validator.setDefaults({
    submitHandler : function() {
        updateUser();

    }
});

var app = new Vue({
    el: '#app',
    data:{
        birthday:''
    },
    methods:{
        getAllRoleName:function(roleName,birthday) {
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'user/getAllRoleName',
                error : function(request) {
                    parent.layer.alert("Connection error");
                },
                success : function(data) {
                    if (data.code === 200) {
                        $("#userRole").html("");
                        var level = "";
                        level += "<div class='layui-input-inline'>";
                        level += "<select id='userRole' name='modules' lay-verify='required' lay-search=''style='width: 235px;height: 33.9px;border: 1px solid #ccc;border-radius: 4px;'>";
                        for (var i = 0; i < data.data.allRoleName.length; i++){
                            level += "<option value='"+data.data.allRoleName[i]+"'>"+data.data.allRoleName[i]+"</option>";
                        }
                        level += "</select></div>";
                        $("#userRole").append(level);
                        $("#userRole option[value='"+roleName+"']").attr("selected","selected");
                        app.birthday = birthday;
                    }
                }
            });
        },
        validateRule:function() {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {
                    id : {
                        required : true
                    }, name : {
                        required : true
                    }, password : {
                        required : true
                    }
                },
                messages : {
                    name : {
                        required : icon + "请输入用户名"
                    }, password : {
                        required : icon + "请输入密码"
                    }
                }
            })
        }
    },
    mounted:function () {
        if ($("#sysSex").val() !== "") {
            $("[name='sysSex'][value="+$("#sysSex").val()+"]").prop("checked", "checked");
        }
        this.getAllRoleName($("#roleName").val());
        this.validateRule();
    }
});

function updateUser(){
    var sysUser = {
        'sysId':$("#sysId").val(),
        'sysName':$("#sysName").val(),
        'sysSex':$('input:radio:checked').val()===undefined?"":$('input:radio:checked').val(),
        'userRole':$('#userRole option:selected').text(),
        'sysMobile':$("#sysMobile").val(),
        'sysEmail':$("#sysEmail").val(),
        'sysOfficeAddress':$("#sysOfficeAddress").val(),
    };
    if (isMobileEmail(sysUser.sysMobile)){
        $.ajax({
            cache : true,
            type : "POST",
            url : context + 'user/updateUser',
            data :JSON.stringify(sysUser),
            dataType : 'json',
            contentType:'application/json',
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                if (data.code === 200) {
                    if (data.data.code === 200){
                        parent.layer.msg("操作成功");
                    } else if(data.data.code === 200){
                        parent.layer.msg("操作失败");
                    }
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                }
            }
        });
    }
}

function isMobileEmail(sysMobile) {
    var flag = true;
    if (sysMobile !== ""){
        var phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!phoneReg.test(sysMobile)) {
            layer.msg("手机号格式不正确，请重新输入！");
            document.getElementById("sysMobile").value = "";
            flag =  false;
        }
    }
    return flag;
}

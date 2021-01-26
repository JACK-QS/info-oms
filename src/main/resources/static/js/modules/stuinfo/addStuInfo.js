$.validator.setDefaults({
    submitHandler : function() {
        addStuInfo();
    }
});

var app = new Vue({
    el: '#app',
    data:{
        value1: ''
    },
    methods:{
        validateRule:function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {

                    stuNum : {
                        required : true
                    },
                    stuName : {
                        required : true
                    }
                },
                messages : {
                    stuNum  : {
                        required : icon + "请输入学生学号"
                    },
                    stuName : {
                        required : icon + "请输入学生姓名"
                    }
                }
            })
        }
    },
    mounted:function () {
        if ($('input:radio:checked').val() === undefined){
            $("[name='sex'][value='男']").prop("checked", "checked");
        }
        this.validateRule();
    }
});

function addStuInfo(){
    var stuInfo = {
        'stuNum':$("#stuNum").val(),
        'stuName':$("#stuName").val(),
        'stuAge':$('#stuAge').text(),
        'stuSex':$("#stuSex").val(),
        'stuNation':$("#stuNation").val(),
        'stuTelephone':$("#stuTelephone").val(),
        'stuOriginPlace':$("#stuOriginPlace").val(),
        'stuDepartment':$("#stuDepartment").val(),
        'stuSpecialities':$("#stuSpecialities").val(),
        'stuClass':$("#stuClass").val(),
        'stuInstructor':$("#stuInstructor").val(),
        'stuFatherName':$("#stuFatherName").val(),
        'stuFatherNum':$("#stuFatherNum").val(),
        'stuMotherName':$("#stuMotherName").val(),
        'stuMotherNum':$("#stuMotherNum").val(),
    };
    if (isMobileEmail($("#stuTelephone").val())){
        $.ajax({
            cache : true,
            type : "POST",
            url : context + 'stuInfo/addStuInfo',
            data :JSON.stringify(stuInfo),
            dataType : 'json',
            contentType:'application/json',
            error : function(request) {
                parent.layer.alert("Connection error");
            },
            success : function(data) {
                if (data.code === 200) {
                    if (data.data.code === 200){
                        layer.msg("操作成功");
                    } else if(data.data.code === 501){
                        layer.msg("该用户已存在，操作失败");
                    } else if(data.data.code === 500){
                        layer.msg("操作失败");
                    }
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                }
            }
        });
    }
}

function isMobileEmail(stuTelephone) {
    var flag = true;
    if (stuTelephone!== ""){
        var phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!phoneReg.test(stuTelephone)) {
            layer.msg("手机号格式不正确，请重新输入！");
            document.getElementById("stuTelephone").value = "";
            flag =  false;
        }
    }
    return flag;
}
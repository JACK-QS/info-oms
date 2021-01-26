var app =  new Vue({
    el: '#app',
    data:{
        ruleForm: {
            sysId:'',
            sysName: '',
            sysSex: '',
            userRole: '',
            sysMobile: '',
            sysEmail: '',
            sysOfficeAddress: ''
        },
        rules: {
            sysName: [
                { required: true, message: '请输入用户名', trigger: 'blur' }
            ]
        }
    },
    methods: {
        submitForm:function(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    if (isMobileEmail(app.ruleForm.mobile)){
                        $.ajax({
                            url: context + 'user/editUser',
                            type: 'POST',
                            data: JSON.stringify(app.ruleForm),
                            dataType : 'json',
                            contentType:'application/json',
                            success: function (res) {
                                if (res.code === 200) {
                                    app.$message.success(res.message);
                                } else {
                                    app.$message.error(res.message);
                                }
                            }
                        })
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
             });
        }
    },
    mounted:function() {
        var sysUser = JSON.parse($("#sysUser").val());
        this.ruleForm.sysId = sysUser.sysId;
        this.ruleForm.sysName = sysUser.sysName;
        this.ruleForm.sysSex = sysUser.sysSex;
        this.ruleForm.userRole = $("#roleName").val();
        this.ruleForm.sysMobile = sysUser.sysMobile;
        this.ruleForm.sysEmail = sysUser.sysEmail;
        this.ruleForm.sysOfficeAddress = sysUser.sysOfficeAddress;
    }
});

function isMobileEmail(mobile) {
    var flag = true;
    if (mobile !== ""){
        var phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!phoneReg.test(mobile)) {
            app.$message.error("手机号格式不正确，请重新输入！");
            app.ruleForm.mobile = "";
            flag =  false;
        }
    }
    return flag;
}

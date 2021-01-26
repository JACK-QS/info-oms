$.validator.setDefaults({
    submitHandler : function() {
        updateRole();
    }
});

var roleId=$("#roleId").val();
var app = new Vue({
    el:"#app",
    data:{
        data: [],
        defaultProps: {
            children: 'children',
            label: 'menuName'
        },
        expandAll:true,
        expandedKeys:[],
        checkedKeys:[]  //所有被选中的子节点
    },
    methods:{
        getData: function() {
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'role/getRoleMenu',
                data:{
                    "roleId":roleId
                },
                error : function(request) {
                    parent.layer.alert("Connection error");
                },
                success : function(data) {
                    if (data.code === 200) {
                        app.data = data.data.menuList;
                        app.expandedKeys = data.data.parentIds;
                        app.checkedKeys = data.data.ids;
                    }
                }
            });
        },
        getCheckedKeys:function () {
            return this.$refs.tree.getCheckedKeys();
        },
        getHalfCheckedKeys:function (value) {
            return this.$refs.tree.getHalfCheckedKeys().concat(value);
        },
        validateRule:function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#signupForm").validate({
                rules : {
                    roleId : {
                        required : true
                    }, roleName : {
                        required : true
                    }, roleAuthoritr : {
                        required : true
                    }
                },
                messages : {
                    roleName : {
                        required : icon + "请输入角色名称"
                    }, roleAuthoritr : {
                        required : icon + "请输入角色权限"
                    }
                }
            })
        }
    },
    mounted:function () {
        this.getData();
        this.validateRule();
    }
});

function updateRole(){
    var childrenId = app.getCheckedKeys();
    var roleVO = {
        'roleId':$("#roleId").val(),
        'roleName':$("#roleName").val(),
        "roleAuthoritr":$("#roleAuthoritr").val(),
        'ids':app.getHalfCheckedKeys(childrenId)
    };
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'role/updateRole',
        data :JSON.stringify(roleVO),
        dataType : 'json',
        contentType:'application/json',
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code === 200) {
               if (data.data.code === 200){
                   parent.layer.msg("操作成功");
               } else if (data.data.code === 500){
                   parent.layer.msg("操作失败");
               }
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            }
        }
    });
}
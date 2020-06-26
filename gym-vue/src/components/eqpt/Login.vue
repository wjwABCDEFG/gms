<template>
    <div class="login_container">
        <!--登录块-->
        <div class="login_box">
            <div class="avatar_box"> 
                <img src="../assets/gdou.png" />
            </div>
            <!--表单区域-->
            <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0">
                <!--用户名-->
                <el-form-item prop="username" >
                    <el-input v-model="loginForm.username" prefix-icon="iconfont icon-denglu"></el-input>
                </el-form-item>
                <!--密码-->
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" prefix-icon="iconfont icon-mima"  type="password"></el-input>
                </el-form-item>
                <!--按钮-->
                <el-form-item class="btns">
                    <el-button type="primary" @click="login()">提交</el-button>
                    <el-button type="info" @click="resetLoginForm()">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            //表单数据对象
            loginForm:{
                username:"adimin",
                password:"123456"
            },
            //验证对象
            loginRules:{
                //校验用户名
                username:[
                    { required: true, message: '请输入用户名称', trigger: 'blur' }, //required用户名必填项验证，blur失去焦点事件
                    { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' } //验证长度
                ],
                //校验密码
                password:[
                    { required: true, message: '请输入用户密码', trigger: 'blur' }, //密码必填项验证，blur失去焦点事件
                    { min: 6, max: 10, message: '长度在 6到 10 个字符', trigger: 'blur' } //验证长度
                ],
            },
        };
    },
    methods:{
        //重置表单内容
        resetLoginForm(){
            this.$refs.loginFormRef.resetFields();
        },
        //登录方法
        login(){
            //1、验证校验规则
            this.$refs.loginFormRef.validate(async valid =>{
                if(!valid) return; //验证失败
                const {data:res} = await this.$http.post("http://localhost:9002/test"); //访问后台
                if ( res == "ok"){
                    this.$message.success("操作成功！"); //信息提示
                    this.$router.push({path:"/eqpthome"}) //页面路由跳转
                }else{
                    this.$message.error("操作失败"); //错误提示
                }
            });
        },
    }
    

}
</script>
<style lang="less" scoped>
//根结点样式
.login_container{
    background-color: #2b2b2b;
    height: 100%;
}
.login_box{
    width: 450px;
    height: 300px;
    background-color: #fff;
    border-radius: 8px;
    position: absolute;
    left:50%;
    top: 50%;
    transform: translate(-50%,-50%);
    .avatar_box{
        width: 130px;
        height: 130px;
        border: 1px solid #eee;
        border-radius: 50%;
        padding: 10px;
        box-shadow:0 0 10px #ddd;
        position: absolute;
        left: 50%;
        transform: translate(-50%,-50%);
        background-color: rgb(50, 85, 197);
        img{
            width:100%;
            height: 100%;
            border-radius: 50%;
            background-color: azure;
        }
    }
}
.login_form{
    position: absolute;
    bottom: 0;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
}
.btns{
    display: flex;
    justify-content: flex-end;
}
</style>
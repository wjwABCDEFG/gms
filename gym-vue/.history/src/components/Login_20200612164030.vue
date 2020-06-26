<template>
    <div class="login_container">
        <div class="login_box">
            <div class="avatar_box">
                <img src="../assets/logo.jpg" alt/>
            </div>
            <h2>广东海洋大学体育馆管理系统</h2>
            <el-form ref="loginFormRef" :rules="loginrules" :model="LoginForm" class="login_form" label-width="0">
                <el-form-item prop="username">
                    <el-input placeholder="请选择用户名" v-model="LoginForm.username" prefix-icon="el-icon-user"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input placeholder="请输入密码" v-model="LoginForm.password" prefix-icon="el-icon-lock" show-password></el-input>
                    <!--<el-input v-model="LoginForm.password"></el-input>-->
                </el-form-item>
                <el-form-item class="btns">
                    <el-button type="primary" @click="login">登录</el-button>
                    <el-button type="success" ><router-link to='/register'>注册</router-link></el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {    
    data(){
        return{
            LoginForm:{
                username:"admin",
                password:"123456",
            },
            loginrules:{
                username:[
                    {
                        required: true,
                        message: "请输入用户名",
                        trigger: 'blur'
                    }
                ],
                password:[
                    {
                        required: true,
                        message: "请输入密码",
                        trigger: 'blur'
                    },
                    {
                        min: 6,
                        max: 12,
                        message: "密码长度为6~12位",
                        trigger: 'blur'
                    }
                ],
            }
        }
    },
    methods:{
        login(){
            this.$refs.loginFormRef.validate(async valid =>{
                if (!valid) {
                    console.log("fail");
                    return;
                }
                const {data:res} = await this.$http.post("login",this.LoginForm);
                if(res.msg == "success"){

                    window.localStorage.setItem("user",JSON.stringify(res.data.user))
                    //console.log(res);
                    this.$message.success("登陆成功!");
                    this.$router.push({path:"/home"});
                }else{
                    this.$message.error("登陆失败!");
                }
            })
        }
    }
}
</script>



<style lang="less" scoped>
    .login_container{
        height:100%;
    }
    h2{
        margin: 69px;
    }
    .login_box{
        width: 450px;
        height: 300px;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
    }
    .avatar_box{
        width: 130px;
        height: 130px;
        border-radius: 50%;
        padding: 10px;
        box-shadow: 0 0 10px #ddd;
        position: absolute;
        left: 50%;
        transform: translate(-50%,-50%);
    }
    img{
        width: 100%;
        height: 100%;
        border-radius: 50%;
    }
    .btns{
        display:flex;
        justify-content: flex-end;
    }
    .login_form{
        position: absolute;
        bottom: 0%;
        width: 100%;
        padding: 0 10px;
        box-sizing: border-box;
    }
    a{
        text-decoration: none;
        color: white;
    }
    router-link-active {
        text-decoration: none;
    }
</style>
<template>
    <div class="register_container">
        <div class="register_box">
            <div class="avatar_box">
                <img src="../assets/logo.jpg" alt/>
            </div>
            <h2>广东海洋大学体育馆管理系统</h2>
            <el-form ref="registerFormRef" :rules="registerrules" :model="RegisterForm" class="register_form" label-width="0">
                <el-form-item prop="username">
                    <el-input placeholder="请输入用户名" v-model="RegisterForm.username" prefix-icon="el-icon-user"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input placeholder="请输入密码" v-model="RegisterForm.password" prefix-icon="el-icon-lock" show-password></el-input>
                </el-form-item>
                <el-form-item prop="repassword">
                    <el-input placeholder="请确认密码" v-model="RegisterForm.repassword" prefix-icon="el-icon-lock" show-password></el-input>
                </el-form-item>
                <el-form-item prop="realname">
                    <el-input placeholder="请输入真实姓名" v-model="RegisterForm.realname" prefix-icon="el-icon-document"></el-input>
                </el-form-item>
                <el-form-item prop="email">
                    <el-input placeholder="请输入邮箱" v-model="RegisterForm.email" prefix-icon="el-icon-message"></el-input>
                </el-form-item>
                <el-form-item prop="phone">
                    <el-input placeholder="请输入手机号" v-model="RegisterForm.phone" prefix-icon="el-icon-phone-outline"></el-input>
                </el-form-item>
                <el-form-item class="btns">
                    <el-button type="success" @click="register">注册</el-button>
                    <router-link to='/login'>已有帐号，直接登录</router-link>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        var checkPhone = (rule, value, callback) => {
            const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/
            if (!value) {
            return callback(new Error('电话号码不能为空'))
            }
            setTimeout(() => {
            // Number.isInteger是es6验证数字是否为整数的方法,但是我实际用的时候输入的数字总是识别成字符串
            // 所以我就在前面加了一个+实现隐式转换

            if (!Number.isInteger(+value)) {
                callback(new Error('请输入数字值'))
            } else {
                if (phoneReg.test(value)) {
                callback()
                } else {
                callback(new Error('电话号码格式不正确'))
                }
            }
            }, 100)
        }
        var checkEmail = (rule, value, callback) => {
            const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
            if (!value) {
            return callback(new Error('邮箱不能为空'))
            }
            setTimeout(() => {
                if (mailReg.test(value)) {
                    callback()
                } else {
                    callback(new Error('请输入正确的邮箱格式'))
                }
            }, 100)
        };
        var checkPass = (rule, value, callback) => {
            if (!value) {
            callback(new Error('请再次输入密码'));
            } else if (value !== this.RegisterForm.password) {
            callback(new Error('两次输入密码不一致!'));
            } else {
            callback();
            }
        };
        return{
            RegisterForm:{
                username:"",
                password:"",
                realname:"",
                email:"",
                phone:"",
            },
            registerrules:{
                username:[
                    {
                        required: true,
                        message: "请输入用户名",
                        trigger: 'blur'
                    }
                ],
                realname:[
                    {
                        required: true,
                        message: "请输入真实姓名",
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
                repassword:[
                    {
                        validator: checkPass,
                        trigger: 'blur' 
                    }
                ],
                email:[
                    {
                        validator: checkEmail,
                        trigger: 'blur'
                    }
                ],
                phone:[
                    {
                        validator: checkPhone,
                        trigger: 'blur'
                    }
                ],
            }
        }
    },
    methods:{
        register(){
            this.$refs.registerFormRef.validate(async valid =>{
                if (!valid) {
                    console.log("fail");
                    return;
                }
                const {data:res} = await this.$http.post("http://localhost:9000/register",this.RegisterForm);
                if(res.msg == "success"){
                    this.$message.success("注册成功!");
                    this.$router.push({path:"/login"});
                }else{
                    this.$message.error("注册失败!");
                }
            })
        }
    }
}
</script>

<style lang="less" scoped>
    .register_container{
        height:100%;
    }
    h2{
        margin: 69px;
    }
    .register_box{
        width: 450px;
        height: 300px;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top: 40%;
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
    .register_form{
        position: absolute;
        bottom: -80%;
        width: 100%;
        padding: 0 10px;
        box-sizing: border-box;
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
    a{
        text-decoration: none;
        color: blue;
        font-size: 10px;
    }
    router-link-active {
        text-decoration: none;
    }
</style>
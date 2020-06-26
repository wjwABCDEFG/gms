<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人</el-breadcrumb-item>
      <el-breadcrumb-item>修改个人资料</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 表格视图区 -->
    <div class="form">
        <el-form  ref="dataFormRef" :model="DataForm" :rules="datarules" label-position="right" label-width="80px" >
            <el-form-item label="用户id">
                <el-input v-model="DataForm.user_id" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="username">
                <el-input v-model="DataForm.username"></el-input>
            </el-form-item>
            <el-form-item label="真实姓名" prop="realname">
                <el-input v-model="DataForm.realname"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="DataForm.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="DataForm.email"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
                <el-input v-model="DataForm.phone"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="medium" @click="updateUserData">确定</el-button>
            </el-form-item>
        </el-form>
    </div>
  </div>
</template>



<script>
export default {
  data() {
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
    return {
        DataForm: {
          user_id:'',
          username: '',
          realname: '',
          password:'',
          email: '',
          phone: ''
        },
     
      // 验证规则
      datarules:{
        username:[
           { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 5, max: 8, message: "长度在 5 到 8 个字符", trigger: "blur" }
        ],
        password:[
           { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 50, message: "长度在 6 到 12 个字符", trigger: "blur" }
        ],
        email:[
           {validator: checkEmail,trigger: 'blur'}
        ],
        phone:[
          {validator: checkPhone,trigger:'blur'}
        ],
      },
    };
  },

  created() {
    this.fillForm();
  },

  methods: {
    fillForm(){
      const userFlag = window.localStorage.getItem("user");
      let user=JSON.parse(userFlag);
      this.DataForm = user[0];
    },
    updateUserData(){
      this.$refs.dataFormRef.validate(async valid =>{
        if (!valid) {
          console.log("fail");
          return;
        }
        const {data:res} = await this.$http.post("http://localhost:9000/updateUserData",this.DataForm);
        window.localStorage.setItem("user",JSON.stringify(res.data));
        this.fillForm();
        this.$message.success("修改成功!");
      })
    }
    }
}
</script>

<style lang="less" scoped>
.form{
        width: 450px;
        height: 300px;
        border-radius: 3px;
        position: absolute;
        // left: 50%;
        // top: 50%;
        // transform: translate(-50%,-50%);
    }
.DataForm{
        position: absolute;
        // bottom: 0%;
        width: 100%;
        // padding: 0 10px;
        box-sizing: border-box;
    }
</style>
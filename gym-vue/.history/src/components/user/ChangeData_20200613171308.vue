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
        <el-form :data="tableData" :rules="rules" label-position="right" label-width="80px" :model="PersonDataForm">
            <el-form-item label="用户id">
                <el-input v-model="PersonDataForm.user_id" disabled=true></el-input>
            </el-form-item>
            <el-form-item label="用户名">
                <el-input v-model="PersonDataForm.username"></el-input>
            </el-form-item>
            <el-form-item label="真实姓名">
                <el-input v-model="PersonDataForm.realname"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="PersonDataForm.password"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="PersonDataForm.email"></el-input>
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="PersonDataForm.phone"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="medium">确定</el-button>
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
        tableData: {
          user_id:'',
          username: '',
          realname: '',
          password:'',
          email: '',
          phone: ''
        },
     
      // 验证规则
      rules:{
        username:[
           { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 5, max: 8, message: "长度在 5 到 8 个字符", trigger: "blur" }
        ],
        password:[
           { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 12, message: "长度在 6 到 12 个字符", trigger: "blur" }
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
            trigger:'blur'
          }
        ],
      },
      
    };
  },

  created() {
    fillForm();
  },

  methods: {
   async fillForm(){
    const {data:res} = await this.$http.get("getUserData",user.user_id);
    if(res.code != 200) return this.$message.error("获取数据失败");
    this.tableData = res.data;
   },
  //  updataData(){
  //    this.$refs.DataForm.validate.validate(async valid =>{
  //               if (!valid) {
  //                   console.log("fail");
  //                   return;
  //               }
  //               const {data:res} = await this.$http.post("login",this.LoginForm);
  //     })
  //   },
  },
};
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
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
        <el-form class="DataForm" label-position="right" label-width="80px" :model="PersonDataForm">
            <el-form-item label="用户名">
                <el-input v-model="PersonDataForm.name"></el-input>
            </el-form-item>
            <el-form-item label="真实姓名">
                <el-input v-model="PersonDataForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="PersonDataForm.email"></el-input>
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
    return {
        PersonDataForm: {
          name: '',
          email: '',
          phone: ''
        },
     
      // 验证规则
      addFormRules:{
        username:[
           { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 5, max: 8, message: "长度在 5 到 8 个字符", trigger: "blur" }
        ],
        password:[
           { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
        ],
        email:[
           { required: true, message: "请输入邮箱", trigger: "blur" },
          { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
        ],
      },
      // 修改用户表单验证规则
      editFormRules:{
          password:[
           { required: true, message: "请输入密码", trigger: "blur" },
           { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
          ],
          email:[
           { required: true, message: "请输入邮箱", trigger: "blur" },
           { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
          ],
      },
    };
  },

  created() {
    
  },

  methods: {
   
  }
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
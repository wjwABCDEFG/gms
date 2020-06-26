<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
      <el-breadcrumb-item :to="{ path: '/homeinfo' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>一级目录</el-breadcrumb-item>
      <el-breadcrumb-item>List表格</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 表格视图区 -->
    
    <el-form class="PasswordForm" :rules="rules" label-width="80px" :model="PasswordForm">
        <h1>修改密码</h1>
        <el-form-item prop="oldpassword">
            <el-input placeholder="请输入旧密码" v-model="PasswordForm.oldpassword" prefix-icon="el-icon-lock" show-password></el-input>
        </el-form-item>
        <el-form-item prop="newpassword">
            <el-input placeholder="请输入新密码" v-model="PasswordForm.newpassword" prefix-icon="el-icon-lock" show-password></el-input>
        </el-form-item>
        <el-form-item class="btns">
            <el-button type="primary">确定</el-button>
        </el-form-item>
    </el-form>

  </div>
</template>

<script>
export default {
    data(){
      return{
        PasswordForm:{
          oldpassword:"",
          newpassword:"",
        }
      //   rules:{
      //     oldpassword:[
      //       {
      //         min: 6,
      //         max: 12,
      //         message: "密码长度为6~12位",
      //         trigger: 'blur',
      //       },
      //     ],
      //     newpassword:[
      //       {
      //         min: 6,
      //         max: 12,
      //         message: "密码长度为6~12位",
      //         trigger: 'blur',
      //       },
      //     ],
      // }
    }
    
  }
}
</script>

<style scope>
h1{
  margin: 10px;
}
.PasswordForm{
  position: absolute;
  width: 100%;
  box-sizing: border-box;
}
</style>
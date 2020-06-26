<template>
    <div class="main">
        <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="器材租用"></el-page-header>

    <!--信息填写卡片-->
    <el-card>
      <!--提示区-->
      <el-alert title="申请已提交，请等待审核" type="success"  center :closable="false"></el-alert>
      <!--步骤条区-->
      <el-steps :active="3" align-center finish-status="success">
        <el-step title="租用信息填写"></el-step>
        <el-step title="租用信息确认"></el-step>
        <el-step title="租用申请提交"></el-step>
      </el-steps>
      <!--信息区-->
      <el-form ref="rentFrom" label-width="100px" :model="rentFrom">
        <el-form-item label="器材名称：" prop="eqptName">
          <el-input v-model="rentFrom.eqptName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="租用数量：" prop="Amount">
          <el-input v-model="rentFrom.Amount" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="学号：" prop="studentID">
          <el-input v-model="rentFrom.studentID" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="姓名：" prop="Tenant">
          <el-input v-model="rentFrom.Tenant" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="联系电话：" prop="Telphone">
          <el-input v-model="rentFrom.Telphone" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="支付金额(元):" prop="PayAmount">
          <el-input v-model="rentFrom.PayAmount" :disabled="true"></el-input>
        </el-form-item>
         <el-form-item label="提交时间" prop="rentTime">
          <el-input v-model="rentFrom.rentTime" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item >
          <el-button @click="goBack" >返回</el-button>       
        </el-form-item>
      </el-form>
    </el-card>
    </div>
</template>
<script>
export default {
    data() {
    return {
      rentFrom: {}
    };
  },
  created(){
    this.rentFrom.eqptName = window.sessionStorage.getItem("eqptName");
    this.rentFrom.Amount = window.sessionStorage.getItem("Amount");
    this.rentFrom.studentID = window.sessionStorage.getItem("studentID");
    this.rentFrom.Tenant = window.sessionStorage.getItem("Tenant");
    this.rentFrom.Telphone = window.sessionStorage.getItem("Telphone");
    this.rentFrom.PayAmount = window.sessionStorage.getItem("PayAmount");
    this.rentFrom.rentTime = window.sessionStorage.getItem("rentTime");
  },
  methods:{
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
  }
}
</script>
<style lang="less" scoped>
.el-form {
  margin: 0px auto;
  width: 60%;
}
.el-input {
  width: 70%;
}
</style>
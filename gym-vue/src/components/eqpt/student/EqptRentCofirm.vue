<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="器材租用"></el-page-header>

    <!--信息填写卡片-->
    <el-card>
      <!--提示区-->
      <el-alert title="请确定信息后再支付" type="error" effect="dark" center :closable="false"></el-alert>
      <!--步骤条区-->
      <el-steps :active="1" align-center finish-status="success">
        <el-step title="租用信息填写"></el-step>
        <el-step title="租用信息确认"></el-step>
        <el-step title="租用申请提交"></el-step>
      </el-steps>
      <!--表单填写区-->
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
        <el-form-item>
          <el-button @click="comeback">上一步</el-button>
          <el-button type="primary" @click="pay">支付</el-button>
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
  created() {
    this.rentFrom.eqptName = window.sessionStorage.getItem("eqptName");
    this.rentFrom.Amount = window.sessionStorage.getItem("Amount");
    this.rentFrom.studentID = window.sessionStorage.getItem("studentID");
    this.rentFrom.Tenant = window.sessionStorage.getItem("Tenant");
    this.rentFrom.Telphone = window.sessionStorage.getItem("Telphone");
    this.rentFrom.PayAmount = window.sessionStorage.getItem("PayAmount");
  },
  methods: {
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
    comeback() {
      this.$router.push({ path: "/eqptRent" }); //返回上一步
    },
    pay() {
      this.$prompt("请输入密码", "提示", {
        inputType: "password",
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^\d{6}$/,
        inputErrorMessage: "密码不正确"
      }).then(async ({ value }) => {
        var date = new Date();
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? "0" + m : m;
        var d = date.getDate();
        d = d < 10 ? "0" + d : d;
        var rentTime = y + "-" + m + "-" + d;
        const { data: res } = await this.$http.post("http://localhost:9002/eqptApp", {
          eqptName: this.rentFrom.eqptName,
          amount: this.rentFrom.Amount,
          studentID: this.rentFrom.studentID,
          tenant: this.rentFrom.Tenant,
          telphone: this.rentFrom.Telphone,
          payAmount: this.rentFrom.PayAmount,
          verifyState: "未审核",
          rentTime: rentTime
        });
        if (res.code != 200) return this.$message.error("租用申请提交失败");
        window.sessionStorage.setItem("eqptName", this.rentFrom.eqptName);
        window.sessionStorage.setItem("Amount", this.rentFrom.Amount);
        window.sessionStorage.setItem("studentID", this.rentFrom.studentID);
        window.sessionStorage.setItem("Tenant", this.rentFrom.Tenant);
        window.sessionStorage.setItem("Telphone", this.rentFrom.Telphone);
        window.sessionStorage.setItem("PayAmount", this.rentFrom.PayAmount);
        window.sessionStorage.setItem("rentTime", rentTime);
        if (res.code == 200) {
          this.$router.push({ path: "/EqptRentEnd" });
        }
      });
    }
  }
};
</script>
<style  lang="less" scoped>
.el-form {
  margin: 0px auto;
  width: 60%;
}
.el-input {
  width: 70%;
}
</style>
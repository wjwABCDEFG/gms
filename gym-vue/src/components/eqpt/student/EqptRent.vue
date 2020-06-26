<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="器材租用"></el-page-header>

    <!--信息填写卡片-->
    <el-card>
      <!--提示区-->
      <el-alert title="填写租用器材相关信息" type="info" center show-icon :closable="false"></el-alert>
      <!--步骤条区-->
      <el-steps :active="0" align-center finish-status="success">
        <el-step title="租用信息填写"></el-step>
        <el-step title="租用信息确认"></el-step>
        <el-step title="租用申请提交"></el-step>
      </el-steps>
      <!--表单填写区-->
      <el-form ref="rentFromRef" :model="rentFrom" label-width="80px" :rules="rentFromRules">
        <el-form-item label="器材" prop="eqptName">
          <el-select v-model="rentFrom.eqptName" placeholder="请选择">  <!-- @focus="getOption"-->
            <el-option
              v-for="item in options"
              :key="item.eqptName"
              :value="item.eqptName"
              :label="item.eqptName+'--'+item.unitPrice+'元/个'"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="租用数量" prop="Amount">
          <el-input-number v-model="rentFrom.Amount" :min="1" :max="10"></el-input-number>
        </el-form-item>
        <el-form-item label="学号" prop="studentID">
          <el-input v-model="rentFrom.studentID"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="Tenant">
          <el-input v-model="rentFrom.Tenant"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="Telphone">
          <el-input v-model="rentFrom.Telphone"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="next">下一步</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
export default {
  data() {
    var checkTelphone = (rule, value, cb) => {
      const regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
      if (regPhone.test(value)) {
        return cb();
      }
      cb(new Error("请输入合法的手机号"));
    };

    return {
      rentFrom: {}, //租用申请表
      options: [], //器材类型表
      Amount: 1, //计数器初始值为1
      value: "",
      rentFromRules: {
        eqptName: [
          { required: true, message: "请选择器材类型", trigger: "blur" }
        ],
        Amount: [{ required: true, message: "请选择数量", trigger: "blur" }],
        studentID: [{ required: true, message: "请填写学号", trigger: "blur" }],
        Tenant: [{ required: true, message: "请填写姓名", trigger: "blur" }],
        Telphone: [
          { required: true, message: "请填写联系电话", trigger: "blur" },
          { validator: checkTelphone, trigger: "blur" }
        ]
      }
    };
  },
  created() {
      this.getOption();
  },
  methods: {
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
    async getOption() {
      const { data: res } = await this.$http.get("http://localhost:9002/queryType");
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res);
      this.options = res.data;
    },
   next() {
      this.$refs.rentFromRef.validate(async value => {
        if (!value) return; //预校验失败
        console.log(this.rentFrom.eqptName);
        window.sessionStorage.setItem("eqptName", this.rentFrom.eqptName);
        window.sessionStorage.setItem("Amount", this.rentFrom.Amount);
        window.sessionStorage.setItem("studentID", this.rentFrom.studentID);
        window.sessionStorage.setItem("Tenant", this.rentFrom.Tenant);
        window.sessionStorage.setItem("Telphone", this.rentFrom.Telphone);
        const { data: res } = await this.$http.get("http://localhost:9002/getPrice", {
        params: {
          eqptName: this.rentFrom.eqptName
        }
      });
       var PayAmount = this.rentFrom.Amount * res;
        window.sessionStorage.setItem("PayAmount", PayAmount);
        this.$router.push({ path: "/EqptRentCofirm" });
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
</style>
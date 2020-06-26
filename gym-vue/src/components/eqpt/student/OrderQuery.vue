<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="租用申请查询"></el-page-header>

    <!--卡片视图-->
    <el-card>
      <el-row style="padding:10px 0">
        <el-col :span="8">
          <el-input placeholder="请输入手机号码" v-model="orederQuery">
            <el-button slot="append" icon="el-icon-search" @click="OrderQuery"></el-button>
          </el-input>
        </el-col>
      </el-row>

      <el-table :data="rentList" border style="width: 100%" height="400">
        <el-table-column prop="orderID" label="订单编号" fixed="left" ></el-table-column>
        <el-table-column prop="eqptName" label="器材名称" ></el-table-column>
        <el-table-column prop="studentID" label="学号" width="120px"></el-table-column>
        <el-table-column prop="tenant" label="租用人" ></el-table-column>
        <el-table-column prop="telphone" label="联系电话"  width="120px"></el-table-column>
        <el-table-column prop="amount" label="租用数量" ></el-table-column>
        <el-table-column prop="payAmount" label="支付金额" ></el-table-column>
        <el-table-column prop="rentTime" label="申请时间" ></el-table-column>
        <el-table-column prop="apprTime" label="审核时间" ></el-table-column>
        <el-table-column prop="verifyState" label="审核状态" >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.verifyState=== '未审核' ? 'danger' : 'success'"
              disable-transitions
            >{{scope.row.verifyState}}</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      rentList: [] ,//租用申请列表
      orederQuery:" ",
      user: '',
      phoneNum: '',
    };
  },
  created() {
      // 获取当前登录的用户
      this.user = JSON.parse(window.localStorage.getItem("user"));// 取出session里当前登录用户
      // console.log(this.user);
      if(this.user != null){
          this.phoneNum = this.user[0].phone;
      }else{
          this.$message.error("未获取到当前登录用户，请重新登录");
      }
      this.init();
  },
  methods: {
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
    async OrderQuery() {
      // console.log(this.orederQuery);
      const { data: res } = await this.$http.get("http://localhost:9002/orederQuery", {
        params: {
          telphone: this.orederQuery
        }
      });
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res);
      this.rentList = res.data;
    },
    async init() {
      // console.log(this.orederQuery);
      const { data: res } = await this.$http.get("http://localhost:9002/orederQuery", {
        params: {
          telphone: this.phoneNum
        }
      });
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res);
      this.rentList = res.data;
    }
  }
};
</script>
<style lang="less" scoped>
</style>
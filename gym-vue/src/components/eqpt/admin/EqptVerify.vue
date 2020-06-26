<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="租用审核"></el-page-header>

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
        <el-table-column prop="orderID" label="订单编号" fixed="left" width="90px"></el-table-column>
        <el-table-column prop="eqptName" label="器材名称" width="100px"></el-table-column>
        <el-table-column prop="studentID" label="学号" width="120px"></el-table-column>
        <el-table-column prop="tenant" label="租用人" width="100px"></el-table-column>
        <el-table-column prop="telphone" label="联系电话" width="120px"></el-table-column>
        <el-table-column prop="amount" label="租用数量" width="90px"></el-table-column>
        <el-table-column prop="payAmount" label="支付金额" width="90px"></el-table-column>
        <el-table-column prop="rentTime" label="申请时间" width="120px"></el-table-column>
        <el-table-column prop="verifyState" label="审核状态" width="100px">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.verifyState=== '未审核' ? 'danger' : 'success'"
              disable-transitions
            >{{scope.row.verifyState}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" fixed="right" width="120px">
          <template slot-scope="scope">
            <el-button
              @click="handleEdit(scope.row.eqptName,scope.row.orderID)"
              icon="el-icon-edit-outline"
              plain
              size="small"
            ></el-button>
            <el-button
              @click="rejectRent(scope.row.orderID)"
              icon="el-icon-circle-close"
              plain
              size="small"
              type="danger"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--相应类型的器材列表-->
    <el-dialog title="器材列表" :visible.sync="dialogTableVisible">
      <el-table
        :data="choseEqptList"
        style="width: 100%"
        :default-sort="{prop: 'eqid', order: 'ascending'}"
        ref="multipleTable"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="eqptID" label="器材编号" align="center" sortable></el-table-column>
        <el-table-column prop="eqptName" label="器材类别" align="center"></el-table-column>
        <el-table-column prop="eqptStatus" label="器材状态" align="center">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.eqptStatus === '完好' ? 'success' : scope.row.eqptStatus ==='损坏'?'danger':'warning'"
              disable-transitions
            >{{scope.row.eqptStatus}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="rentalStatus" label="出租状态" align="center">
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.rentalStatus=== '已出租' ? 'info' : '#409EFF'"
              disable-transitions
            >{{scope.row.rentalStatus}}</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 20px">
        <el-button @click="checkBox">通过</el-button>
        <el-button @click="dialogTableVisible=false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
// import global from "@/api/global_var";
import global from "../../../api/global_var";
export default {
  data() {
    return {
      rentList: [], //租用申请列表
      choseEqptList: [], //可选的器材列表
      multipleSelection: [], //多选框
      dialogTableVisible: false,
      orederQuery:" "
    };
  },
  created() {
    this.getRentList();
  },
  methods: {
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
    async getRentList() {
      const { data: res } = await this.$http.get("http://localhost:9002/rentList");
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res);
      this.rentList = res.data;
    },
    async handleEdit(eqptName, orderID) {
      this.dialogTableVisible = true;
      this.getChoseEqptList(eqptName);
      global.flag = orderID;
      console.log(global.flag + "测试时");
    },
    async getChoseEqptList(eqptName) {
      const { data: res } = await this.$http.get("http://localhost:9002/choseEqptList", {
        params: {
          eqptName: eqptName
        }
      });
      if (res.code != 200) return this.$message.error("获取数据失败");
      if (res.code == 200) {
        console.log(res);
        this.choseEqptList = res.data;
      }
    },
    rejectRent(orderID) {
      console.log(orderID);
      this.$confirm("此操作将驳回租用申请, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(async () => {
          var date = new Date();
          var y = date.getFullYear();
          var m = date.getMonth() + 1;
          m = m < 10 ? "0" + m : m;
          var d = date.getDate();
          d = d < 10 ? "0" + d : d;
          var apprTime = y + "-" + m + "-" + d;
          const { data: res } = await this.$http.post("http://localhost:9002/rejectRent", {
            orderID: orderID,
            apprTime: apprTime
          });
          if (res.code != 200) return this.$message.error("驳回操作失败");
          if (res.code == 200) {
            this.$message({
              type: "success",
              message: "驳回申请!"
            });
            this.getRentList();
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消驳回"
          });
        });
    },
    agreeRent() {
      this.$confirm("此操作将无法撤回, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(async () => {
        let arr = this.$refs.multipleTable.selection;
        let multis = [];
        for (let i = 0; i < arr.length; i++) {
          multis.push(arr[i].eqptID);
        }
        console.log(global.flag + "测试时");
        var date = new Date();
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? "0" + m : m;
        var d = date.getDate();
        d = d < 10 ? "0" + d : d;
        var apprTime = y + "-" + m + "-" + d;
        const { data: res } = await this.$http.post("http://localhost:9002/agreeRent", multis);
        if (res.code != 200) return this.$message.error("操作失败");
        if (res.code == 200) {
          const { data: res } = await this.$http.post("http://localhost:9002/agreeVerify", {
            orderID: global.flag,
            apprTime: apprTime
          });
          if (res.code != 200) return this.$message.error("操作有误");
          if (res.code == 200) {
            this.dialogTableVisible = false;
            this.getRentList(); 
            this.$message.success("通过申请");
          }
        }
      });
    },
    // 选择框
    checkBox: function() {
      if (this.$refs.multipleTable.selection.length === 0) {
        this.$message({
          message: "请至少勾选一项，再进行操作",
          type: "warning"
        });
      } else {
        this.agreeRent();
      }
    },
    //搜素框
    async OrderQuery() {
      console.log(this.orederQuery);
      const { data: res } = await this.$http.get("http://localhost:9002/orederQuery", {
        params: {
          telphone: this.orederQuery
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
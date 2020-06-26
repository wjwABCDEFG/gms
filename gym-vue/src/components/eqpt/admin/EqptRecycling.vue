<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="器材回收"></el-page-header>

    <el-card>
      <!--搜素框-->
      <el-row style="padding:10px 0" :gutter="30">
        <el-col :span="50">
          <el-input placeholder="请输入" v-model="searchRent" class="input-with-select">
            <el-select v-model="value" slot="prepend" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                @change="showMessage($event)"
              ></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="queryRent"></el-button>
          </el-input>
        </el-col>
        <el-button class="el-icon-refresh-left" @click="getEqptList">刷新</el-button>
      </el-row>

      <!--列表-->

      <el-table
        :data="eqptList"
        border
        style="width: 100%"
        :default-sort="{prop: 'eqid', order: 'ascending'}"
        height="380"
      >
        <el-table-column prop="eqptID" label="器材编号" align="center" sortable></el-table-column>
        <el-table-column prop="eqptName" label="器材类别" align="center" sortable></el-table-column>
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
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              plain
              size="small"
              icon="el-icon-bottom"
              @click="eqptRecycling(scope.row.eqptID)"
            >回收</el-button>
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
      options: [
        {
          value: "1",
          label: "器材类型"
        },
        {
          value: "2",
          label: "器材编号"
        }
      ],
      value: "", //默认选择为空
      searchRent: "", //查询绑定
      eqptList: []
    };
  },
  created() {
    this.getEqptList();
  },
  methods: {
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
    async getEqptList() {
      const { data: res } = await this.$http.get("http://localhost:9002/searchRent");
      if (res.code !== 200) return this.$message.error("获取失败");
      this.eqptList = res.data;
      console.log(res.data);
    },
    eqptRecycling(eqptID) {
      this.$confirm("是否回收器材?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(async () => {
          this.$confirm("器材是否损坏?", "提示", {
            confirmButtonText: "没有",
            cancelButtonText: "损坏",
            type: "danger"
          })
            .then(async () => {
              const { data: res } = await this.$http.post(
                "http://localhost:9002/recyclingEqpt",
                eqptID
              );
              if (res.code !== 200) return this.$message.error("操作失败");
              this.getEqptList();
              this.$message({
                type: "success",
                message: "回收成功!"
              });
            })
            .catch(async () => {
              const { data: res } = await this.$http.post(
                "http://localhost:9002/eqptDamage",
                eqptID
              );
              if (res.code !== 200) return this.$message.error("操作失败");
              this.getEqptList();
              this.$message({
                type: "success",
                message: "回收成功!"
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消回收"
          });
        });
    },
    async queryRent() {
      console.log(this.value);
      console.log(this.searchRent);
      if (this.value == 1) {
        const { data: res } = await this.$http.get("http://localhost:9002/queryByType", {
          params: {
            eqptName: this.searchRent
          }
        });
        if (res.code != 200) return this.$message.error("操作失败");
        console.log(res.data);
        this.eqptList = res.data;
      } else if (this.value == 2) {
        const { data: res } = await this.$http.get("http://localhost:9002/queryByID", {
          params: {
            eqptID: this.searchRent
          }
        });
        if (res.code != 200) return this.$message.error("操作失败");
        console.log(res.data);
        this.eqptList = res.data;
      } else {
        this.$message.error("请选择查询类型");
      }
    }
  }
};
</script>
<style lang="less" scoped>
.el-select {
  width: 120px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>
<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="器材租用标准查询"></el-page-header>

    <!--搜素框-->
      <el-row style="padding:10px 0" :gutter="30">
        <el-col :span="50">
          <el-input placeholder="请输入" v-model="input" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="getChargStandard">搜索</el-button>
          </el-input>
        </el-col>
        <el-button class="el-icon-refresh-left" @click="getTypeList">刷新</el-button>
      </el-row>

    <!--库存数据-->
    <el-table :data="typeList" border style="width: 100%">
      <el-table-column prop="eqptName" label="器材类型"></el-table-column>
      <el-table-column prop="unitPrice" label="租用单价(元/个)"></el-table-column>
      <el-table-column prop="totalQty" label="总数量"></el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //器材分类的数据列表，默认为空
      typeList: [],  
      input:''
    };
  },
  created() {
    this.getTypeList();
  },

  methods: {
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
    //获取器材类型数据
    async getTypeList() {
      const { data: res } = await this.$http.get("http://localhost:9002/queryType");
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res);
      this.typeList = res.data;
    },
    //查询租用收费标准
    async getChargStandard(){
        console.log(this.input)
        const {data:res} = await this.$http.get("http://localhost:9002/getChargStandard",{
          params: {
            eqptName: this.input
          }
        });
        if(res.data == null) return this.$message.error("获取数据失败");
        this.typeList = res.data
    }
  }
};
</script>
<style lang="less" scoped>
.main {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
}
.title {
  width: 100%;
  height: 30px;
  align-items: center;
}
</style>
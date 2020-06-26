<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="库存查询"></el-page-header>

    <el-col style="padding:10px">
      <el-button type="primary" @click="addTypeVisible=true">添加类别</el-button>
      <el-button class="el-icon-refresh-left" @click="getTypeList">刷新</el-button>
    </el-col>

    <!--添加类别的对话框-->
    <el-dialog title="新增器材类别" :visible.sync="addTypeVisible" width="30%" @close="addTypeClosed">
      <el-form :model="addTypeform" label-width="80px" ref="addTypeRef" :rules="addTypeRules">
        <el-form-item label="器材类别" prop="eqptName">
          <el-input v-model="addTypeform.eqptName"></el-input>
        </el-form-item>
        <el-form-item label="租用单价" prop="unitPrice">
          <el-input v-model="addTypeform.unitPrice"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addTypeVisible = false">取 消</el-button>
        <el-button type="primary" @click="addType">确 定</el-button>
      </div>
    </el-dialog>

    <!--库存数据-->
    <el-table :data="typeList" border style="width: 100%">
      <el-table-column prop="eqptName" label="器材类型"></el-table-column>
      <el-table-column prop="unitPrice" label="租用单价(元/个)"></el-table-column>
      <el-table-column prop="totalQty" label="总数量"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            @click="handleEdit(scope.$index, scope.row)"
            icon="el-icon-edit-outline"
            plain
            size="medium"
          >修改单价</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //器材分类的数据列表，默认为空
      typeList: [],
      addTypeVisible: false,
      addTypeform: {
        eqptName: "",
        unitPrice: ""
      },
      addTypeRules: {
        eqptName: [
          { required: true, message: "请输入器材类型", trigger: "blur" }
        ],
        unitPrice: [
          { required: true, message: "请输入租用单价", trigger: "blur" }
        ]
      }
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
    addTypeClosed() {
      this.$refs.addTypeRef.resetFields();
    },
    addType() {
      console.log(this.addTypeform);
      this.$refs.addTypeRef.validate(async value => {
        if (!value) return; //预校验失败
        const { data: res } = await this.$http.post(
          "http://localhost:9002/addType",
          this.addTypeform
        );
        if (res.code != 200) return this.$message.error("添加失败");
        if (res.code == 200) {
          this.addTypeVisible = false;
          this.getTypeList();
          return this.$message.success("添加成功");
        }
        this.getTypeList();
      });
    },
    handleEdit(index, row) {
      this.$prompt("请输入单价", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^[-\+]?\d+$/,
        inputErrorMessage: "请输入正整数"
      })
        .then(async ({ value }) => {
          var eqptName= row.eqptName;
          var unitPrice = value;
          console.log(row.eqptName);
          console.log(value);
          const { data: res } = await this.$http.post("http://localhost:9002/chargePrice", {eqptName:row.eqptName,unitPrice:value});
          if (res.code != 200) return this.$message.error("修改失败");
          if (res.code == 200) return this.$message.success("修改成功");
          this.getTypeList();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消修改"
          });
        });
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
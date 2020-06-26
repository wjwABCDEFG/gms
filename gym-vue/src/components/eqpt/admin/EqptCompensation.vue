<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="损坏赔偿"></el-page-header>
    <!--添加赔偿订单-->
    <el-col style="padding:10px">
      <el-button type="primary" @click="addCompensationVisible=true">新增赔偿</el-button>
      <el-button class="el-icon-refresh-left">刷新</el-button>
    </el-col>

    <!--添加类别的对话框-->
    <el-dialog
      title="新增器材类别"
      :visible.sync="addCompensationVisible"
      width="30%"
      @close="addCompensationeClosed"
    >
      <el-form
        :model="addCompensationForm"
        label-width="80px"
        ref="addCompensationRef"
        :rules="addCompensationRules"
      >
        <el-form-item label="器材状态" prop="eqptStatus">
          <el-input v-model="addCompensationForm.eqptStatus" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="器材编号" prop="eqptID">
          <el-select v-model="addCompensationForm.eqptID" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.eqptID"
              :label="item.label"
              :value="item.eqptID"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学号" prop="studentID">
          <el-input v-model="addCompensationForm.studentID"></el-input>
        </el-form-item>
        <el-form-item label="处理状态" prop="cpstStatus">
          <el-input v-model="addCompensationForm.cpstStatus"></el-input>
        </el-form-item>
        <el-form-item label="赔偿金额" prop="indemnity">
          <el-input v-model="addCompensationForm.indemnity"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addCompensationVisible = false">取 消</el-button>
        <el-button type="primary" @click="addCompensation">确 定</el-button>
      </div>
    </el-dialog>

    <!--赔偿订单-->
    <el-table :data="compensationeList" border style="width: 100%">
      <el-table-column prop="compensateID" label="编号"></el-table-column>
      <el-table-column prop="eqptID" label="器材编号"></el-table-column>
      <el-table-column prop="studentID" label="学号"></el-table-column>
      <el-table-column prop="cpstStatus" label="处理状态">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.cpstStatus === '未赔偿' ? 'danger' :'success'"
            disable-transitions
          >{{scope.row.cpstStatus}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="indemnity" label="赔偿金额"></el-table-column>
      <el-table-column prop="submissionTime" label="新增时间"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            @click="handleEdit(scope.$index, scope.row)"
            icon="el-icon-edit-outline"
            plain
            size="medium"
          >赔偿处理</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      addCompensationVisible: false,
      options: [],
      eqptID: "",
      compensationeList: [],
      addCompensationForm: {
        eqptStatus: "损坏",
        eqptID: "",
        studentID: "",
        cpstStatus: "",
        indemnity: ""
      },
      addCompensationRules: {
        eqptID: [
          { required: true, message: "请选择器材编号", trigger: "blur" }
        ],
        studentID: [
          { required: true, message: "请输入赔偿者学号", trigger: "blur" }
        ],
        cpstStatus: [
          { required: true, message: "请输入赔偿状态", trigger: "blur" }
        ],
        indemnity: [
          { required: true, message: "请输入赔偿金额", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getCompensationeList();
    this.getOptions();
  },
  methods: {
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
    addCompensationeClosed() {
      this.$refs.addCompensationRef.resetFields();
    },
    //获取损坏的器材编号
    async getOptions() {
      const { data: res } = await this.$http.get("http://localhost:9002/getOptions");
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res.data);
      this.options = res.data;
    },
    async getCompensationeList() {
      const { data: res } = await this.$http.get("http://localhost:9002/getCompensationeList");
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res.data);
      this.compensationeList = res.data;
    },
    async addCompensation() {
      var date = new Date();
      var y = date.getFullYear();
      var m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      var d = date.getDate();
      d = d < 10 ? "0" + d : d;
      var submissionTime = y + "-" + m + "-" + d;
      const { data: res } = await this.$http.post("http://localhost:9002/addCompensation", {
        eqptID: this.addCompensationForm.eqptID,
        studentID: this.addCompensationForm.studentID,
        cpstStatus: this.addCompensationForm.cpstStatus,
        indemnity: this.addCompensationForm.indemnity,
        submissionTime: submissionTime
      });
      if (res.code != 200) return this.$message.error("器材赔偿提交失败");
      this.getCompensationeList();
      this.addCompensationVisible = false;
    },
    handleEdit(index, row) {
      this.$confirm("请确定收到赔款再操作,是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(async () => {
          console.log(row.compensateID)
          const { data: res } = await this.$http.post("http://localhost:9002/finishCompensation",{compensateID:row.compensateID});
          if (res.code != 200) return this.$message.error("操作失败");
          console.log(res.data);
          this.getCompensationeList();
          this.$message({
            type: "success",
            message: "操作成功!"
          });
        })
    }
  }
};
</script>
<style lang="less" scoped>
</style>
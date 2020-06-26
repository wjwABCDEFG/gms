<template>
  <div class="main">
    <!--标题栏-->
    <el-page-header class="title" @back="goBack" content="器材管理"></el-page-header>
    <!--查询表单-->
    <el-form :inline="true" :model="queryForm" class="demo-form-inline" ref="queryRef">
      <el-form-item label="器材编号" prop="eqptID">
        <el-input v-model="queryForm.eqptID" placeholder="请输入"></el-input>
      </el-form-item>
      <el-form-item prop="btn">
        <el-button type="primary" @click="queryEqpt"  icon="el-icon-search">查询</el-button>
        <el-button @click="resetForm" icon="el-icon-refresh">重置</el-button>
      </el-form-item>
    </el-form>
    <!--新增按钮-->
    <el-button type="primary" size="medium" @click="addEqptVisible = true">新增器材</el-button>
    <el-button size="medium" class="el-icon-refresh-left" @click="getEqptList">刷新</el-button>

    <!--新增器材对话框-->
    <!--标题-->
    <el-dialog title="新增器材" :visible.sync="addEqptVisible" width="35%" @close="addDialogClosed">
      <!--器材添加表单-->
      <el-form ref="addEqptRef" :model="addEqptForm" label-width="80px" :rules="addEqptRules">
        <el-form-item label="器材编号" prop="eqptID">
          <el-col :span="16">
            <el-input v-model="addEqptForm.eqptID"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="器材类型" prop="eqptName">
          <el-col :span="16">
            <el-input v-model="addEqptForm.eqptName" width="30px"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="器材状态" prop="eqptStatus">
          <el-select v-model="addEqptForm.eqptStatus" placeholder="请选择器材状态">
            <el-option label="完好" value="完好"></el-option>
            <el-option label="损坏" value="损坏"></el-option>
            <el-option label="保修" value="保修"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="租用状态" prop="rentalStatus">
          <el-select v-model="addEqptForm.rentalStatus" placeholder="请选择租用状态">
            <el-option label="未出租" value="未出租"></el-option>
            <el-option label="已出租" value="已出租"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addEqptVisible = false">取 消</el-button>
        <el-button type="primary" @click="addEqpt">确 定</el-button>
      </div>
    </el-dialog>

    <!--表格-->
    <div class="eqptinfo">
      <el-table
        :data="eqptList"
        border
        style="width: 100%"
        :default-sort="{prop: 'eqid', order: 'ascending'}"
        height="380"
      >
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
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button plain size="small" icon="el-icon-edit-outline" @click="repairEqptByID(scope.row.eqptID)">报修</el-button>
            <el-button @click="deleteClick(scope.row.eqptID)" type="danger" plain icon="el-icon-delete"  size="small">报废</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //搜索表单的数据绑定对象
      queryForm: {
        eqptID: "",
        eqptName: ""
      },
      //器材列表
      eqptList: [],
      //控制添加表单的隐藏
      addEqptVisible: false,
      //添加器材的表单数据
      addEqptForm: {
        eqptID: "",
        eqptName: "",
        eqptStatus: "",
        rentalStatus: ""
      },
      addEqptRules: {
        eqptID: [
          { required: true, message: "请输入器材编号", trigger: "blur" }
        ],
        eqptName: [
          { required: true, message: "请输入器材类型", trigger: "blur" }
        ],
        eqptStatus: [{ required: true }],
        rentalStatus: [{ required: true }]
      }
    };
  },

  created() {
    //查询eqptList
    this.getEqptList();
  },

  methods: {
    goBack() {
      this.$router.push({ path: "/homeinfo" }); //返回首页
    },
    resetForm() {
      this.$refs.queryRef.resetFields();
    },
    // 获取器材信息
    async getEqptList() {
      const { data: res } = await this.$http.get("http://localhost:9002/eqptInfo");
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res);
      this.eqptList = res.data;
    },
    //查询器材
    async queryEqpt() {
      const { data: res } = await this.$http.get("http://localhost:9002/queryEqpt", {
        params: {
          eqptID: this.queryForm.eqptID
        }
      });
      if (res.code != 200) return this.$message.error("获取数据失败");
      console.log(res);
      this.eqptList = res.data;
    },
    //监听添加器材对话框的关闭事件
    addDialogClosed() {
      this.$refs.addEqptRef.resetFields();
    },
    //点击确定，添加新的器材
    addEqpt() {
      this.$refs.addEqptRef.validate(async value => {
        if (!value) return; //预校验失败
        const { data: res } = await this.$http.post(
          "http://localhost:9002/addEqpt",
          this.addEqptForm
        );
       if (res.code != 200) return this.$message.error("添加失败");
       if (res.code == 200) {
         this.addEqptVisible = false;
         this.getEqptList();
         return this.$message.success("添加成功");    
        } 
       this.getEqptList();
      });
    },
    //根据id选择报修器材
    async repairEqptByID(eqptID){
        const repairConfirmResult = await this.$confirm(
          '是否报修该器材？','提示',{
            confirmButtonText:'确定',
            cancelButtonText:'取消',
            type:'warning'
          }
        ).catch(err => err) //返回错误
        //确认，返回值为字符串confirm/ 取消返回值为cancel
        if(repairConfirmResult !== 'confirm'){
          return this.$message.info('已取消报修')
        }
        const {data:res} = await this.$http.post("http://localhost:9002/repairEqpt",eqptID)
        if(res.code != 200){
          return this.$message.error('报修失败！')
        }
        console.log(res)
        this.$message.success('报修成功！')
        this.getEqptList()
    },
    //根据id报废器材
    async deleteClick(eqptID) {
      const deleteConfirmResult = await this.$confirm(
        '是否永久报废该器材？','提示',{
          confirmButtonText:'确定',
          cancelButtonText:'取消',
          type:'warning'
        }
      ).catch(err =>err)
      if(deleteConfirmResult !== 'confirm'){
        return this.$message.info('已取消报废')
      }
      const {data:res} = await this.$http.post("http://localhost:9002/deleteEqpt",eqptID)
        if(res.code != 200){
          return this.$message.error('报废失败！')
        }
        console.log(res)
        this.$message.success('报废成功！')
        this.getEqptList()
    },
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
.demo-form-inline {
  width: 100%;
  height: 50px;
  padding: 10px;
}
.eqptinfo {
  padding: 5px;
}
</style>
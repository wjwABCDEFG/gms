<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>个人</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区 -->
    <el-card>
      
      <el-col :span="11">
        <div :model="roleselect">
          <label>查询管理员</label>
          <el-select v-model="roleselect.role_id" placeholder="请选择角色">
            <el-option label="用户管理员" value=1></el-option>
            <el-option label="场地管理员" value=2></el-option>
            <el-option label="赛事管理员" value=3></el-option>
            <el-option label="器材管理员" value=4></el-option>
            <el-option label="普通用户" value=5></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="selectRole"></el-button>
        </div>
      </el-col>
    

      <!-- 用户列表 -->
      <el-table :data="tableData"  style="width: 75%;text-align:center;font-size:15px">
        <el-table-column align="center" label="用户名" prop="username"></el-table-column>
        <el-table-column align="center" label="真实姓名" prop="realname"></el-table-column>
        <el-table-column align="center" label="角色" prop="rolename"></el-table-column>
        <el-table-column align="center" label="邮箱" prop="email"></el-table-column>
        <el-table-column align="center" label="电话" prop="phone"></el-table-column>
      </el-table>

      <!-- 分页 -->
      <el-pagination style="margin-left:400px;margin-top:20px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[10, 20]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-card>
    
  </div>
</template>
<script>
export default {
  data() {
    return {
      //testData
      tableData: [],
      roleselect:{
        role_id:"",
      },
      // 请求数据
      queryInfo: {
        query: "",
        pageNum: 1,
        pageSize: 5
      },
      userlist: [],// 用户列表
     
      total: 0, // 最大数据记录
      // addDialogVisible: false ,// 对话框显示
      // // 添加用户表单项
      // addForm: {
      //   username:'',
      //   password:'',
      //   email:'',
      // }, 
      // // 控制修改用户对话框显示/隐藏
      // editDialogVisible:false,
      // // 修改用户信息
      // editForm:{},
      // // 验证规则
      // addFormRules:{
      //   username:[
      //      { required: true, message: "请输入用户名", trigger: "blur" },
      //     { min: 5, max: 8, message: "长度在 5 到 8 个字符", trigger: "blur" }
      //   ],
      //   password:[
      //      { required: true, message: "请输入密码", trigger: "blur" },
      //     { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
      //   ],
      //   email:[
      //      { required: true, message: "请输入邮箱", trigger: "blur" },
      //     { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
      //   ],
      // },
      // // 修改用户表单验证规则
      // editFormRules:{
      //     password:[
      //      { required: true, message: "请输入密码", trigger: "blur" },
      //      { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
      //     ],
      //     email:[
      //      { required: true, message: "请输入邮箱", trigger: "blur" },
      //      { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
      //     ],
      // },
    };
  },
  created() {
    this.getUserList();
  },
  methods: {
    
    // 监听pageSize改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getUserList(); // 数据发生改变重新申请数据
    },
    // 监听pageNum改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getUserList(); // 数据发生改变重新申请数据
    },
    
    async getUserList(){
      const {data:res} = await this.$http.get("http://localhost:9000/getUserList");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.tableData = res.data;
    },

    async selectRole(){
      // console.log(this.roleselect.role_id);
      const {data:res} = await this.$http.post("http://localhost:9000/selectRole",this.roleselect.role_id);
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.tableData = res.data;
    }
  }
};
</script>
<style lang="less" scoped>

.arrow-text {
    font-size:14px;
}

label{
  margin: 1px;
}
</style>
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>一级目录</el-breadcrumb-item>
      <el-breadcrumb-item>List表格</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区 -->
    <el-card>
      <el-row :gutter="35">
        <el-col :span="5">
          <el-button type="primary" @click="addDialogVisible = true">+ 新 建</el-button>
          <el-button>批量操作</el-button>
        </el-col>
        <el-col :span="7">
          <!-- 搜索添加 -->
          <el-input placeholder="请输入搜索内容" >
            <el-button slot="append" icon="el-icon-search" ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" >添加用户</el-button>
        </el-col>
      </el-row>

      <!-- 用户列表 -->
      <el-table  :data="tableData"  style="width: 75%;text-align:center;font-size:15px">
        <el-table-column align="center" label="编号" prop="num"></el-table-column>
        <el-table-column align="center" label="场地类别" prop="class"></el-table-column>
        <el-table-column align="center" label="场地位置" prop="position"></el-table-column>
        <el-table-column align="center" label="场地可预约时间" prop="time"></el-table-column>
        <el-table-column align="center" label="场地情况" prop=""><el-tag>运行中</el-tag></el-table-column>
        <el-table-column align="center" label="负责人" prop="chargeName"></el-table-column>
        <el-table-column align="center" label="费用" prop="cost"></el-table-column>

        <el-table-column align="center" label="操作">
          <template >

            <el-button type="text" size="medium">删除</el-button>
            <el-button type="text" size="medium">修改</el-button>
          </template>
        </el-table-column>
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

    <!-- 创建新用户对话框 -->
    <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%"
    @close="addDialogClosed">
      <!-- 内容主体区域 -->
      <el-form :model="addForm" :rules="addFormRules"
        ref="addFormRef" label-width="70px">
        <!-- 用户名 -->
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
      </el-form>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //testData
      tableData: [{
            num: 'Y102515325',
            class: '体育馆',
            position: '体育馆二楼206',
            time: '周一8:10-11.50；周二14:30-16.05',
            status: 'true',
            cost: '20',
            chargeName: '李光昊',
          },
          {
            num: 'Y102515325',
            class: '体育馆',
            position: '体育馆二楼206',
            time: '周一8:10-11.50；周二14:30-16.05',
            status: 'true',
            cost: '20',
            chargeName: '李光昊',
          },
          {
            num: 'Y102515325',
            class: '体育馆',
            position: '体育馆二楼206',
            time: '周一8:10-11.50；周二14:30-16.05',
            status: 'true',
            cost: '20',
            chargeName: '李光昊',
          }],

      // 请求数据
      queryInfo: {
        query: "",
        pageNum: 1,
        pageSize: 5
      },
      userlist: [],// 用户列表
     
      total: 0, // 最大数据记录
      addDialogVisible: false ,// 对话框显示
      // 添加用户表单项
      addForm: {
        username:'',
        password:'',
        email:'',
      }, 
      // 控制修改用户对话框显示/隐藏
      editDialogVisible:false,
      // 修改用户信息
      editForm:{},
      // 验证规则
      addFormRules:{
        username:[
           { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 5, max: 8, message: "长度在 5 到 8 个字符", trigger: "blur" }
        ],
        password:[
           { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
        ],
        email:[
           { required: true, message: "请输入邮箱", trigger: "blur" },
          { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
        ],
      },
      // 修改用户表单验证规则
      editFormRules:{
          password:[
           { required: true, message: "请输入密码", trigger: "blur" },
           { min: 6, max: 8, message: "长度在 6 到 8 个字符", trigger: "blur" }
          ],
          email:[
           { required: true, message: "请输入邮箱", trigger: "blur" },
           { min: 5, max: 15, message: "请输入正确邮箱地址", trigger: "blur" }
          ],
      },
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
    
    
    // 关闭窗口
    editDialogClosed(){
      this.$refs.editFormRef.resetFields();
    },
   
  }
};
</script>
<style lang="less" scoped>

.arrow-text {
    font-size:14px;
}



</style>
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
      <!-- <el-row>
        <el-button type="primary">+ 新 建</el-button>
        <el-button>批量操作</el-button>
        <el-input placeholder="请输入搜索内容" >
            <el-button slot="append" icon="el-icon-search" ></el-button>
        </el-input>
      </el-row> -->
      <el-row :gutter="35">
        <el-col :span="5">
          <el-button type="primary" @click="addDialogVisible = true">+ 新 建</el-button>
          <!-- <el-button>批量操作</el-button> -->
        </el-col>
        <el-col :span="7">
          <!-- 搜索添加 -->
          <!-- <el-input placeholder="请输入搜索内容" >
            <el-button slot="append" icon="el-icon-search" ></el-button>
          </el-input> -->
        </el-col>
        <!-- <el-col :span="4">
          <el-button type="primary" >添加用户</el-button>
        </el-col> -->
      </el-row>

      <!-- 用户列表 -->
      <el-table  :data="venueInfoList" :default-sort ="{prop:'id',order:'ascending'}" style="width: 75%;text-align:center;font-size:15px">
        <el-table-column align="center" sortable label="编号" prop="id"></el-table-column>
        <el-table-column align="center" label="场地类别" prop="venueClass"></el-table-column>
        <el-table-column align="center" label="场地位置" prop="position"></el-table-column>
        <el-table-column align="center" label="场地可预约时间" prop="period"></el-table-column>
        <el-table-column align="center" label="场地情况" prop="">
          <template slot-scope="scope">
            <el-tag v-show="scope.row.status==1">运行中</el-tag>
            <el-tag v-show="scope.row.status==2" type="danger">已关闭</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="费用" prop="">
          <template slot-scope="scope">
            <span style="font-size:18px;font-weight:bold">￥{{scope.row.cost}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="负责人" prop="">
          <template slot-scope="scope">
            <el-button type="text" @click="showCharge(scope.$index)" size="medium">{{scope.row.venueCharge.name}}</el-button>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">

            <el-button type="text" size="medium" @click="openDelete(scope.row.id)">删除</el-button>
            <!-- <el-button type="text" size="medium">修改</el-button> -->
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



    <!-- 点击负责人姓名显示信息 -->
    <el-dialog title="负责人信息" :visible.sync="dialogTableVisible" style="" class="chargeShow">
      <!-- <span>负责人信息</span> -->
      <!-- 内容主体区域 -->
      <el-form ref="addFormRef" label-width="100px" style="" class="chargeForm">
        <!-- 负责人 -->
        <el-form-item label="负责人:" style="margin-top:20px">
          {{venueInfoList[venueChargeIndex].venueCharge.name}}
        </el-form-item>
        <!-- 负责场地 -->
        <el-form-item label="负责场地:">
          {{venueInfoList[venueChargeIndex].position}}
        </el-form-item>
        <!-- 所属单位 -->
        <el-form-item label="所属单位:">
          {{venueInfoList[venueChargeIndex].venueCharge.unit}}
        </el-form-item>
        <!-- 工作时间 -->
        <el-form-item label="工作时间:">
          {{venueInfoList[venueChargeIndex].venueCharge.workTime}}
        </el-form-item>
        <!-- 联系电话 -->
        <el-form-item label="联系电话:">
          {{venueInfoList[venueChargeIndex].venueCharge.phone}}
        </el-form-item>
        <el-button type="primary" @click="dialogTableVisible = false" style="margin-left:40%">确 定</el-button>
      </el-form>
    </el-dialog>


    <!-- 新建场地对话框 -->
    <el-dialog title="新建场地信息" :visible.sync="addDialogVisible" width="30%"
    @close="addDialogVisible = false" class="addVenueShow">
      <!-- 内容主体区域 -->
      <el-form :model="newVenueInfo" :rules="addFormRules"
        ref="addFormRef" label-width="125px" class="addVenueForm">
        <!-- 选择场地类别 -->
        <el-form-item label="场地信息" prop="venueId" style="margin-top:20px">
          <el-select v-model="newVenueInfo.venueId" placeholder="请选择场地" style="width:85%">
            <el-option
                v-for="item in venueList"
                :key="item.id"
                :label="item.id+'-'+item.venueClass+'-'+item.position+'-'+item.venueCharge.name+'-'+item.status"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 选择场地位置 -->
        <el-form-item label="场地可预约时间" prop="periodId">
          <el-select v-model="newVenueInfo.periodId" placeholder="请选择时间段" style="width:85%">
            <el-option
                v-for="item in venuePeriodList"
                :key="item.id"
                :label="item.period"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="场地费用" prop="cost">
          <el-input v-model="newVenueInfo.cost" style="width:25%" placeholder="单位：元"></el-input>
          <span style="font-size:20px"> ￥</span>
        </el-form-item>
      </el-form>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer" >
        <el-button @click="addDialogVisible = false" style="margin-left:30px">取 消</el-button>
        <el-button type="primary" @click="addVenueInfo()">确 定</el-button>
      </span>
    </el-dialog>



  </div>
</template>
<script>
export default {
  data() {
    return {
      venueInfoList:[],
      dialogTableVisible: false,    // 控制负责人窗口是否显示
      venueChargeIndex:0,             // 存储负责人序号，方便窗口显示
      venueList:[],
      venuePeriodList:[],
      newVenueInfo:{
        venueId:'',
        periodId:'',
        cost:'',
        statusId:'1',
      },
      // 请求数据
      queryInfo: {
        query: "",
        pageNum: 1,
        pageSize: 5
      },

     
      total: 0, // 最大数据记录
      addDialogVisible: false ,// 对话框显示
      // 添加用户表单项
      addForm: {
        username:'',
        password:'',
        cost:'',
      }, 
      // 控制修改用户对话框显示/隐藏
      editDialogVisible:false,
      // 修改用户信息
      editForm:{},
      // 验证规则
      
      addFormRules:{
        venueId:[
           { required: true, message: '请选择场地', trigger: 'blur' }
        ],
        periodId:[
           { required: true, message: '请选择时间', trigger: 'blur' }
        ],
        cost:[
            { required: true, message: "请输入场地费用", trigger: "blur" },
            { min: 1, max: 3, message: "长度在 1 到 3 个字符", trigger: "blur" }

        ],
      },
    }; 
  },
  created() {
    this.getVenueInfoList();
    this.getVenueList();
    this.getVenuePeriodList();
  },
  methods: {
    // 查询所有的场地信息
    async getVenueInfoList(){
      // console.log("come in");
      
      const {data:res} = await this.$http.get("http://localhost:9003/getVenueInfo");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueInfoList = res.data;
    },
    // 获取所有场地
    async getVenueList(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenue");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueList = res.data;
    },
    // 获取所有的时间段
    async getVenuePeriodList(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenuePeriod");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venuePeriodList = res.data;

    },

    // 新建场地
    async addVenueInfo1(){
        this.addDialogVisible = false;
        console.log(this.newVenueInfo);
        const {data:res} = await this.$http.post("http://localhost:9003/addVenueInfo", this.newVenueInfo);
        if(res.code != 200) return this.$message.error("获取数据失败");
        this.getVenueInfoList();
        // 成功，  发异步请求，外层函数加async
        this.$notify({
          title: '成功',
          message: '场地新增成功',
          type: 'success'
        });
    },
    
    addVenueInfo(){
         this.$refs.addFormRef.validate(async valid => {
           // 不存在则不执行
            if (!valid){
              this.$notify.error({
              title: '错误',
              message: '请先按要求填写信息'
            });
            }else{
              this.addDialogVisible = false;
              // console.log(this.newVenueInfo);
              const {data:res} = await this.$http.post("http://localhost:9003/addVenueInfo", this.newVenueInfo);
              if(res.code != 200) return this.$message.error("获取数据失败");
              this.getVenueInfoList();
              // 成功，  发异步请求，外层函数加async
              this.$notify({
                title: '成功',
                message: '场地新增成功',
                type: 'success'
              });
            } 
         })
    },


    //显示场地负责人信息
    showCharge(index){
      this.venueChargeIndex = index;
      this.dialogTableVisible = true;

    },
    openDelete(id){
        this.$confirm('此操作将删除该场地信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(async () => {
        console.log(id);
        await this.$http.post(`http://localhost:9003/venueInfoDelete?id=${id}`);
        this.getVenueInfoList();
        // 成功，  发异步请求，外层函数加async
        this.$notify({
          title: '成功',
          message: '场地信息删除成功',
          type: 'success'
        });
      }).catch(() => {
          //点取消，什么也不做
      })
    },
    // 监听pageSize改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize;
      this.getVenueInfoList(); // 数据发生改变重新申请数据
    },
    // 监听pageNum改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getVenueInfoList(); // 数据发生改变重新申请数据
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

.chargeShow{
  margin-left:25%;
  width:50%;
  text-align:center;
  // font-size:20px;
  font-weight:bold;
  color:#409EFF;
  margin-bottom: 0px;
}
.addVenueShow{
  text-align:center;
  font-weight:bold;
  margin-bottom: 0px;
}

.addVenueForm{
  text-align:left;
  border-top-width:4px;
  border-top-style:solid;
  border-top-color:#409EFF;
}

.chargeForm{
  text-align:left;
  // font-size:17px;
  font-weight:normal;
  border-top-width:4px;
  border-top-style:solid;
  border-top-color:#409EFF;
}



</style>
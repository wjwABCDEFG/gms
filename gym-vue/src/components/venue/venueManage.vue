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
        <!-- <el-col :span="7"> -->
          <!-- 搜索添加 -->
          <!-- <el-input placeholder="请输入搜索内容" >
            <el-button slot="append" icon="el-icon-search" ></el-button>
          </el-input> -->
        <!-- </el-col> -->
        <!-- <el-col :span="4">
          <el-button type="primary" >添加用户</el-button>
        </el-col> -->
      </el-row>

      <!-- 用户列表 -->
      <el-table  :data="venueList" :default-sort ="{prop:'id',order:'ascending'}" style="width: 75%;text-align:center;font-size:15px">
        <el-table-column align="center" sortable label="编号" prop="id"></el-table-column>
        <el-table-column align="center" label="场地类别" prop="venueClass"></el-table-column>
        <el-table-column align="center" label="场地位置" prop="position"></el-table-column>
        <el-table-column align="center" label="场地运行情况" prop="">
          <template slot-scope="scope">
            <el-tag v-show="scope.row.status==1">运行中</el-tag>
            <el-tag v-show="scope.row.status==2" type="danger">已关闭</el-tag>
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
            <el-button type="text" size="medium" 
            @click="updateVenueVisible = true;
            updateVenueIndex=scope.$index
            updateVenue.classId = scope.row.classId
            updateVenue.positionId = scope.row.positionId
            updateVenue.venueId = scope.row.id
            updateVenue.chargePersonId = scope.row.venueCharge.id
            updateVenue.statusId = scope.row.status
            changeStatus()">修改</el-button>
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
          {{venueList[venueChargeIndex].venueCharge.name}}
        </el-form-item>
        <!-- 负责场地 -->
        <el-form-item label="负责场地:">
          {{venueList[venueChargeIndex].position}}
        </el-form-item>
        <!-- 所属单位 -->
        <el-form-item label="所属单位:">
          {{venueList[venueChargeIndex].venueCharge.unit}}
        </el-form-item>
        <!-- 工作时间 -->
        <el-form-item label="工作时间:">
          {{venueList[venueChargeIndex].venueCharge.workTime}}
        </el-form-item>
        <!-- 联系电话 -->
        <el-form-item label="联系电话:">
          {{venueList[venueChargeIndex].venueCharge.phone}}
        </el-form-item>
        <el-button type="primary" @click="dialogTableVisible = false" style="margin-left:40%">确 定</el-button>
      </el-form>
    </el-dialog>


    <!-- 新建场地对话框 -->
    <el-dialog title="新建场地" :visible.sync="addDialogVisible" width="30%"
    @close="addDialogVisible = false" class="addVenueShow">
      <!-- 内容主体区域 -->
      <el-form :model="newVenue" :rules="addFormRules"
        ref="addFormRef" label-width="120px" class="addVenueForm">
        <!-- 选择场地类别 -->
        <el-form-item label="场地类别" prop="classId" style="margin-top:20px">
          <el-select 
          v-model="newVenue.classId" 
          placeholder="请选择场地" 
          style="width:85%">
            <el-option
                v-for="item in venueClassList"
                :key="item.id"
                :label="item.venueClass"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 选择场地位置 -->
        <el-form-item label="场地位置" prop="positionId">
          <el-select v-model="newVenue.positionId" placeholder="请选择场地" style="width:85%">
            <el-option
                v-for="item in venuePositionList"
                :key="item.id"
                :label="item.position"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 选择场地负责人 -->
        <el-form-item label="指定负责人" prop="chargePersonId">
          <el-select v-model="newVenue.chargePersonId" placeholder="请选择场地" style="width:85%">
            <el-option
                v-for="item in venueChargeList"
                :key="item.id"
                :label="item.name+'-'+item.unit+'-'+item.phone"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="场地状态">
            <el-switch
                v-model="newVenue.statusId"
                active-text="运行"
                inactive-text="">
            </el-switch>
        </el-form-item>
      </el-form>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer" >
        <el-button @click="addDialogVisible = false" style="margin-left:30px">取 消</el-button>
        <el-button type="primary" @click="addVenue()">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 更新场地对话框 -->
    <el-dialog title="更新场地信息" :visible.sync="updateVenueVisible" width="30%"
    @close="updateVenueVisible = false" class="addVenueShow">
      <!-- 内容主体区域 -->
      <el-form :model="newVenue" :rules="addFormRules"
        ref="addFormRef" label-width="120px" class="addVenueForm">
        <!-- 选择场地类别 -->
        <el-form-item label="场地类别" prop="classId" style="margin-top:20px">
          <el-select 
          v-model="updateVenue.classId" 
          placeholder="请选择场地" 
          style="width:85%">
            <el-option
                v-for="item in venueClassList"
                :key="item.id"
                :label="item.venueClass"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 选择场地位置 -->
        <el-form-item label="场地位置" prop="positionId">
          <el-select v-model="updateVenue.positionId" placeholder="请选择场地" style="width:85%">
            <el-option
                v-for="item in venuePositionList"
                :key="item.id"
                :label="item.position"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 选择场地负责人 -->
        <el-form-item label="指定负责人" prop="chargePersonId">
          <el-select v-model="updateVenue.chargePersonId" placeholder="请选择场地" style="width:85%">
            <el-option
                v-for="item in venueChargeList"
                :key="item.id"
                :label="item.name+'-'+item.unit+'-'+item.phone"
                :value="item.id">

            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="场地状态">
            <el-switch
                v-model="updateVenue.statusId"
                active-text="运行"
                inactive-text="">
            </el-switch>
        </el-form-item>
      </el-form>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer" >
        <el-button @click="updateVenueVisible = false" style="margin-left:30px">取 消</el-button>
        <el-button type="primary" @click="updateVenueInfo()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      venueList:[],
      venueClassList:[],
      venuePositionList:[],
      venueChargeList:[],
      newVenue:{
          classId: '',
          positionId: '',
          chargePersonId: '',
          statusId: 1,
      },

      updateVenue:{
          venueId:'',
          classId: '',
          positionId: '',
          chargePersonId: 2,
          statusId: 1,
      },

      dialogTableVisible: false,    // 控制负责人窗口是否显示
      venueChargeIndex:0,             // 存储负责人序号，方便窗口显示

      // 请求数据
      queryInfo: {
        query: "",
        pageNum: 1,
        pageSize: 5
      },
      userlist: [],// 用户列表
     
      total: 0, // 最大数据记录
      addDialogVisible: false ,// 对话框显示
      updateVenueVisible: false,
      // 添加用户表单项
      addForm: {
        username:'',
        password:'',
        cost:'',
      }, 
      // 控制修改用户对话框显示/隐藏
      editDialogVisible:false,
      // 验证规则
      addFormRules:{
        classId:[
           { required: true, message: '请选择场地类型', trigger: 'blur' }
        ],
        positionId:[
           { required: true, message: '请选择场地位置', trigger: 'blur' }
        ],
        chargePersonId:[
           { required: true, message: '请指定负责人', trigger: 'blur' }
        ],
      },
    };
  },
  created() {
    this.getVenueList();
    this.getVenueClass();
    this.getVenuePosition();
    this.getVenueCharge();
  },
  methods: {

    // 查询所有的场地
    async getVenueList(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenue");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueList = res.data;
    },

    // 查询所有场地类别
    async getVenueClass(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenueClass");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueClassList = res.data;
      // console.log(this.venueClassList);
      
    },

    // 查询所有场地位置
    async getVenuePosition(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenuePosition");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venuePositionList = res.data;
    },

    changeStatus(){
      if(this.updateVenue.statusId==1){
        this.updateVenue.statusId = true;
      }else{
        this.updateVenue.statusId = false;
      }
      
    },  
    // 查询所有场地负责人
    async getVenueCharge(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenueCharge");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueChargeList = res.data;
    },

    // 新建场地
    async addVenue(){
        if(this.newVenue.statusId != true){
            this.newVenue.statusId = 2;
        }else{
            this.newVenue.statusId = 1;
        }
        this.addDialogVisible = false;
        // console.log(this.newVenue);
        const {data:res} = await this.$http.post("http://localhost:9003/addVenue", this.newVenue);
        if(res.code != 200) return this.$message.error("获取数据失败");
        this.getVenueList();
        // 成功，  发异步请求，外层函数加async
        this.$notify({
          title: '成功',
          message: '场地新增成功',
          type: 'success'
        });
    },

    // 新建场地
    async updateVenueInfo(){
        if(this.updateVenue.statusId != true){
            this.updateVenue.statusId = 2;
        }else{
            this.updateVenue.statusId = 1;
        }
        this.updateVenueVisible = false;
        console.log(this.updateVenue);

        await this.$http.post("http://localhost:9003/updateVenue", this.updateVenue);
        // if(res.code != 200) return this.$message.error("获取数据失败");
        this.getVenueList();
        // 成功，  发异步请求，外层函数加async
        this.$notify({
          title: '成功',
          message: '场地新增成功',
          type: 'success'
        });
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
        await this.$http.post(`http://localhost:9003/venueDelete?id=${id}`);
        this.getVenueList();
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
      this.getvenueList(); // 数据发生改变重新申请数据
    },
    // 监听pageNum改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getvenueList(); // 数据发生改变重新申请数据
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
.chargeForm{
  text-align:left;
//   font-size:17px;
  font-weight:normal;
  border-top-width:4px;
  border-top-style:solid;
  border-top-color:#409EFF;
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


</style>
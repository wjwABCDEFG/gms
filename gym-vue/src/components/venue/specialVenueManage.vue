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
        </el-col>
        <!-- <el-col :span="7">
          <el-input placeholder="请输入搜索内容" >
            <el-button slot="append" icon="el-icon-search" ></el-button>
          </el-input>
        </el-col> -->
        <el-radio-group v-model="tabPosition" 
        style="margin-left: 40%;"
        @change="changeAppInfoType()">
            <el-radio-button label=1>学校课程</el-radio-button>
            <el-radio-button label=2>比赛训练</el-radio-button>
        </el-radio-group>

      </el-row>
      
      

      <!-- 用户列表 -->
      <el-table  :data="specialVenueList" :default-sort ="{prop:'id',order:'ascending'}" style="width: 75%;text-align:center;font-size:15px">
        <el-table-column align="center" sortable label="编号" prop="id"></el-table-column>
        <el-table-column align="center" label="单位性质" prop="">
            <template >
                <span v-show="tabPosition == 1" >学校课程</span>
                <span v-show="tabPosition == 2" >比赛训练</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="场地类别" prop="venueClass"></el-table-column>
        <el-table-column align="center" label="场地位置" prop="position"></el-table-column>
        <el-table-column align="center" label="每周使用日期" prop="">
            <template slot-scope="scope">
                <span v-show="scope.row.weekId==1" >星期日</span>
                <span v-show="scope.row.weekId==2" >星期一</span>
                <span v-show="scope.row.weekId==3" >星期二</span>
                <span v-show="scope.row.weekId==4" >星期三</span>
                <span v-show="scope.row.weekId==5" >星期四</span>
                <span v-show="scope.row.weekId==6" >星期五</span>
                <span v-show="scope.row.weekId==7" >星期六</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="使用时间段" prop="period"></el-table-column>
        <el-table-column align="center" label="使用单位" prop="useUnit"></el-table-column>
        <el-table-column align="center" label="场地运行情况" prop="">
          <template slot-scope="scope">
            <el-tag v-show="scope.row.status==1">运行中</el-tag>
            <el-tag v-show="scope.row.status==2" type="danger">已关闭</el-tag>
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

    <!-- 新建场地对话框 -->
    <el-dialog title="分配特殊场地及时间段" :visible.sync="addDialogVisible" width="30%"
    @close="addDialogVisible = false" class="addVenueShow">
      <!-- 内容主体区域 -->
      <el-form :model="newSpecialVenue"
        ref="addFormRef" label-width="125px" class="addVenueForm">
        <!-- 选择场地类别 -->
        <el-form-item label="场地信息" prop="venueId" style="margin-top:20px">
          <el-select v-model="newSpecialVenue.venueInfoId" placeholder="请选择场地" style="width:85%">
            <el-option
                v-for="item in venueList"
                :key="item.id"
                :label="item.id+'-'+item.venueClass+'-'+item.position+'-'+item.period+'-'+item.status"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 选择场地可预约时间 -->
        <el-form-item label="选择使用单位" prop="">
          <el-select v-model="newSpecialVenue.schoolUnitId" placeholder="请选择单位" style="width:85%">
            <el-option
                v-for="item in schoolUnit"
                :key="item.id"
                :label="item.id+'-'+item.unitClass+'-'+item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- 选择场地可预约时间 -->
        <el-form-item label="使用时间日期" prop="periodId">
          <el-select v-model="newSpecialVenue.weekId" placeholder="请选择星期几" style="width:45%">
            <el-option
                v-for="item in week"
                :key="item.id"
                :label="item.week"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="场地费用" prop="cost">
          <el-input v-model="newSpecialVenue.cost" style="width:25%" placeholder="单位：元"></el-input>
          <span style="font-size:20px"> ￥</span>
        </el-form-item> -->
      </el-form>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer" >
        <el-button @click="addDialogVisible = false" style="margin-left:30px">取 消</el-button>
        <el-button type="primary" @click="addSpecialVenueInfo()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      specialVenueList:[],
      venuePeriodList:[],
      venueList:[],
      schoolUnit:[],
      week:[
          {id:1, week:"星期日"},
          {id:2, week:"星期一"},
          {id:3, week:"星期二"},
          {id:4, week:"星期三"},
          {id:5, week:"星期四"},
          {id:6, week:"星期五"},
          {id:7, week:"星期六"},
      ],
      tabPosition: 1,
      newSpecialVenue:{
          venueInfoId: '',
          schoolUnitId: '',
          weekId: '',
      },
      dialogTableVisible: false,    // 控制负责人窗口是否显示


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
      
    };
  },
  created() {
    this.getSpecialVenueList(1);
    this.getVenueInfoList();
    // this.getVenuePeriodList();
    this.getSchoolUnitList(this.tabPosition);
  },
  methods: {

    // 查询所有的场地
    async getSpecialVenueList(classId){
      const {data:res} = await this.$http.get(`http://localhost:9003/getAllSpecialVenueInfo?unitClassId=${classId}`);
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.specialVenueList = res.data;
    //   console.log(this.specialVenueList);
    },

     // 获取所有场地
    async getVenueInfoList(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenueInfo");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueList = res.data;
    },
    // 获取所有的时间段
    async getVenuePeriodList(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenuePeriod");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venuePeriodList = res.data;

    },
    // 获取所有的学校单位
    async getSchoolUnitList(tabPosition){
      const {data:res} = await this.$http.get(`http://localhost:9003/getSchoolUnit?tabPosition=${tabPosition}`);
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.schoolUnit = res.data;
      console.log("eeeeeeeeeeee");
      

    },
    changeAppInfoType(){
        this.getSchoolUnitList(this.tabPosition);
        if(this.tabPosition==1){
            this.getSpecialVenueList(1);
        
        }else{
            this.getSpecialVenueList(2);
        }
    },
    // 新建特殊场地信息
    async addSpecialVenueInfo(){
        const {data:res} = await this.$http.post("http://localhost:9003/addSpecialVenueInfo", this.newSpecialVenue);
        if(res.code != 200){
            this.$notify.error({
              title: '错误',
              message: '该场地及时间段已被分配'
            });
        }else{
            this.addDialogVisible = false;
            // console.log(this.newSpecialVenue);
            this.getSpecialVenueList(this.tabPosition);
            // 成功，  发异步请求，外层函数加async
            this.$notify({
                title: '成功',
                message: '该特殊场地分配成功',
                type: 'success'
            });
        }

    },

    openDelete(id){
        this.$confirm('此操作将删除该特殊场地信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(async () => {
        console.log(id);
        await this.$http.post(`http://localhost:9003/specialVenueDelete?id=${id}`);
        this.getSpecialVenueList(this.tabPosition);
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
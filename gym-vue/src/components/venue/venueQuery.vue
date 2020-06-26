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
      
      <el-row :gutter="25">
        <el-col :span="10">
            <el-radio-group v-model="tabPosition" 
                style="margin-bottom: 30px;"
                @change="changeAppInfoType()">
                <el-radio-button label=0>全部</el-radio-button>
                <el-radio-button 
                v-for="item in venueClassList"
                :key="item.id"
                :label="item.id"
                >{{item.venueClass}}
                </el-radio-button>
            </el-radio-group>
        </el-col>
        <el-col :span="7">
          <el-input placeholder="请输入搜索内容" v-model="input"  @input="updateView($event)">
            <el-button slot="append" icon="el-icon-search" v-on:click="getVenueInfoById()"></el-button>
          </el-input>
        </el-col>

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
        <!-- <el-table-column align="center" label="负责人" prop="">
          <template slot-scope="scope">
            <el-button type="text" @click="showCharge(scope.$index)" size="medium">{{scope.row.venueCharge.name}}</el-button>
          </template>
        </el-table-column> -->
        <el-table-column align="center" label="费用" prop="">
          <template slot-scope="scope">
            <span style="font-size:18px;font-weight:bold">￥{{scope.row.cost}}</span>
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


  </div>
</template>
<script>
export default {
  data() {
    return {
      venueInfoList:[],
      dialogTableVisible: false,    // 控制负责人窗口是否显示
      venueChargeIndex:0,             // 存储负责人序号，方便窗口显示
      tabPosition: 0,
      input:'',
      venueClassList:[],
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
      
    }; 
  },
  created() {
    this.getVenueInfoList();
    this.getVenueClass();
    // this.getVenueList();
    // this.getVenuePeriodList();
  },
  methods: {
    // 查询所有的场地信息
    async getVenueInfoList(){
      // console.log("come in");
      
      const {data:res} = await this.$http.get("http://localhost:9003/getVenueInfo");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueInfoList = res.data;
    },
    // 查询所有场地类别
    async getVenueClass(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenueClass");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueClassList = res.data;
    //   console.log(this.venueClassList);
      
    },

    async getVenueInfoListByClassId(classId){
      const {data:res} = await this.$http.get(`http://localhost:9003/getVenueInfoByClassId?classId=${classId}`);
    //    console.log(res.code);
      if(res.code != 200){
          this.venueInfoList = [];
          console.log(this.venueInfoList);
          
          this.$notify.info({
              title: '错误',
              message: '该类别暂无相关场地信息'
            });
      }else{
          
          this.venueInfoList = res.data;
      }
      
    },

    updateView(e) {
        this.$forceUpdate()
    },

    //显示场地负责人信息
    showCharge(index){
      this.venueChargeIndex = index;
      this.dialogTableVisible = true;

    },
    async getVenueInfoById(){
        console.log(this.input);
        if(!this.input){
            this.$notify.error({
              title: '错误',
              message: '请输入场地信息编号'
            });
        }else{
            const {data:res} = await this.$http.get(`http://localhost:9003/getVenueInfoById?id=${this.input}`);
            if(res.code != 200) {
                this.$notify.error({
                title: '错误',
                message: '场地信息不存在'
                });
                this.venueInfoList = [];
                this.input='';
            }else{
                this.$notify({
                    title: '成功',
                    message: '场地信息查询成功',
                    type: 'success'
                });
                this.venueInfoList = []
                this.venueInfoList[0] = res.data;
                console.log(this.venueInfoList);
                this.$forceUpdate()
                
            }
        }
    },

    changeAppInfoType(){
        // console.log(this.tabPosition);
        if(this.tabPosition==0){
          this.getVenueInfoList();
        }else{
          this.getVenueInfoListByClassId(this.tabPosition);
        }
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
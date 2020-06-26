<template>
    <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>一级目录</el-breadcrumb-item>
      <el-breadcrumb-item>List表格</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="content" >
        <div class="title">
            <span >待办处理</span>
            <span class="title_right">已处理</span>
        </div>
        <div class="number" >
            <span>{{length}}</span>
            <span class="number_right">{{dealNum}}</span>
        </div>
    </div>

    <!-- 卡片视图区 -->
    <el-card>
      <el-radio-group v-model="tabPosition" 
      style="margin-bottom: 30px;"
      @change="changeAppInfoType()">
        <el-radio-button label=1>未处理</el-radio-button>
        <el-radio-button label=2>已处理</el-radio-button>
     </el-radio-group>


      <!-- 用户列表 -->
      <el-table  :data="appMissInfoList" :default-sort ="{prop:'code',order:'ascending'}" style="width: 75%;text-align:center;font-size:15px">
        <el-table-column align="center" sortable label="审批号" prop="code"></el-table-column>
        <el-table-column align="center" label="场地类型" prop="venueClass"></el-table-column>
        <el-table-column align="center" label="场地位置" prop="position"></el-table-column>
        <el-table-column align="center" label="使用日期" prop="contestTime"></el-table-column>
        <el-table-column align="center" label="预约时间段" prop="period"></el-table-column>
        <el-table-column align="center" label="申请人" prop="username"></el-table-column>
        <!-- <el-table-column align="center" label="提交时间" prop="submitTime"></el-table-column> -->
        <el-table-column align="center" label="超时天数" prop="">
          <template slot-scope="scope">
            <span style="font-size:18px;font-weight:bold">{{scope.row.days}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="审核状态" prop="">
          <template slot-scope="scope">
            <el-tag v-show="scope.row.appStatus==2" type="info">已失约</el-tag>
            <el-tag v-show="scope.row.status==1">正常</el-tag>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" >
          <template slot-scope="">
            <el-tag v-show="ifShowOperat" type="danger" >未处理</el-tag>
            <el-tag v-show="!ifShowOperat">已处理</el-tag>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" >
          <template slot-scope="scope">
            <el-button type="text" size="medium" 
            @click="addDialogVisible = true;dealInfo.venueAppId=scope.row.id; dealInfo.userId = scope.row.userId"
             v-show="ifShowOperat">处理</el-button>
            <el-button type="text" size="medium" v-show="!ifShowOperat">查看信息</el-button>
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
    <el-dialog title="选择处理类型" :visible.sync="addDialogVisible" width="30%"
    @close="addDialogVisible = false" class="addVenueShow">
      <!-- 内容主体区域 -->
      <el-form 
        ref="addFormRef" label-width="125px" class="addVenueForm">
        
        <!-- 选择场地位置 -->
        <el-form-item label="处理类型" prop="periodId" style="margin-top:20px">
          <el-select v-model="dealInfo.classId" placeholder="请选择时间段" style="width:85%">
            <el-option
                v-for="item in classList"
                :key="item.id"
                :label="'禁止申请-'+item.class+'-个月'"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        
      </el-form>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer" >
        <el-button @click="addDialogVisible = false" style="margin-left:30px">取 消</el-button>
        <el-button type="primary" @click="addAppDealInfo()">确 定</el-button>
      </span>
    </el-dialog>

    </div>
</template>
<script>
export default {
    data(){
        return{
            appMissInfoList:[],
            ifShowOperat:true,
            tabPosition: 1,
            auditNum:0,
            addDialogVisible: false,
            classId:'',
            lenght:0,
            index:'',
            dealNum: 0,

            dealInfo:{
                userId:'',
                venueAppId:'',
                classId:''
            },

            unAuditNum:0,
            // 请求数据
            queryInfo: {
                query: "",
                pageNum: 1,
                pageSize: 5
            },
            classList:[
                {id:1, class:1},
                {id:2, class:3},
                {id:3, class:6},
            ]
        }
    },
    created() {
        this.getAppMissInfoList();
        this.getVenueAppCount();
        

    },
    methods: {
        // 查询所有的场地申请信息
        async getAppMissInfoList(){
            // console.log("come in");
            const {data:res} = await this.$http.get(`http://localhost:9003/getAllAppMissInfo`);
            if(res.code != 200) {
                this.$notify({
                title: '通知',
                message: '此栏目下暂无相关信息',
                type: 'info'
                });
            }
            this.appMissInfoList = res.data;
            this.length = this.appMissInfoList.length;
        },

        // 查询所有的场地申请信息
        async getAppDealInfoList(){
            // console.log("come in");
            const {data:res} = await this.$http.get(`http://localhost:9003/getAllDealInfo`);
            if(res.code != 200) {
                this.appMissInfoList = [];
                this.$notify({
                title: '通知',
                message: '此栏目下暂无相关信息',
                type: 'info'
                });
            }
            this.appMissInfoList = res.data;
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

        // 新建场地
        async addAppDealInfo(){
            this.addDialogVisible = false;
            console.log(this.dealInfo)

            const {data:res} = await this.$http.post("http://localhost:9003/addDealInfo", this.dealInfo);
            if(res.code != 200) return this.$message.error("获取数据失败");
            this.getAppMissInfoList();
            // 成功，  发异步请求，外层函数加async
            this.$notify({
            title: '成功',
            message: '处理成功',
            type: 'success'
            });
            this.getVenueAppCount();
        },

        // 计算场地申请信息数量
        async getVenueAppCount(){
            // console.log("come in");
            const {data:res} = await this.$http.get(`http://localhost:9003/countDealInfo`);
            if(res.code!= 200) return this.$message.error("获取数据失败");
            this.dealNum = res.data;
        },

        changeAppInfoType(){
            if(this.tabPosition==1){
                this.getAppMissInfoList();
                this.ifShowOperat = true;
            }else{
                this.getAppDealInfoList();
                this.ifShowOperat = false;
            }
        },

       
    }
    
}
</script>
<style lang="less" scoped>

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

.arrow-text {
    font-size:14px;
}

.content{
    // text-align: center;
    // width: 150px;
    // background-color: #F5F5F5;
    padding-top: 30px;
    height: 80px;
    width:75%;
}

.title{
    // text-align: center;
    font-size: 18px;
    margin-left: 24%;
    color: #909399;
}
.title_right{
    margin-left: 49%;
}

.number{
    // text-align: center;
    font-size: 30px;
    font-weight: bold;
    margin-left: 26%;
    color: #606266;
}
.number_right{
    margin-left: 56%;
}

</style>
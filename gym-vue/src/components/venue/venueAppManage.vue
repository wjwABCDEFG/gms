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
            <span >已审核</span>
            <span class="title_right">待审核</span>
        </div>
        <div class="number" >
            <span>{{auditNum}}</span>
            <span class="number_right">{{unAuditNum}}</span>
        </div>
    </div>

    <!-- 卡片视图区 -->
    <el-card>
      <el-radio-group v-model="tabPosition" 
      style="margin-bottom: 30px;"
      @change="changeAppInfoType()">
        <el-radio-button label=1>未审核</el-radio-button>
        <el-radio-button label=2>已审核</el-radio-button>
     </el-radio-group>


      <!-- 用户列表 -->
      <el-table  :data="venueAppList" :default-sort ="{prop:'code',order:'ascending'}" style="width: 75%;text-align:center;font-size:15px">
        <el-table-column align="center" sortable label="审批号" prop="code"></el-table-column>
        <el-table-column align="center" label="场地类型" prop="venueClass"></el-table-column>
        <el-table-column align="center" label="场地位置" prop="position"></el-table-column>
        <el-table-column align="center" label="预约日期" prop="appTime"></el-table-column>
        <el-table-column align="center" label="预约时间段" prop="period"></el-table-column>
        <el-table-column align="center" label="申请人" prop="username"></el-table-column>
        <el-table-column align="center" label="提交时间" prop="submitTime"></el-table-column>

        <el-table-column align="center" label="审核状态" prop="">
          <template slot-scope="scope">
            <el-tag v-show="scope.row.status==2" type="danger">未审核</el-tag>
            <el-tag v-show="scope.row.status==3">审核通过</el-tag>
            <el-tag v-show="scope.row.status==4" type="danger">审核拒绝</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" >
          <template slot-scope="scope">
            <el-button type="text" size="medium" @click="openApproval(scope.row.code)" v-show="ifShowOperat">通过</el-button>
            <el-button type="text" size="medium" @click="openDelete(scope.row.code)" v-show="ifShowOperat">拒绝</el-button>
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
    </div>
</template>
<script>
export default {
    data(){
        return{
            venueAppList:[],
            ifShowOperat:true,
            tabPosition: 1,
            auditNum:0,
            unAuditNum:0,
            // 请求数据
            queryInfo: {
                query: "",
                pageNum: 1,
                pageSize: 5
            },
        }
    },
    created() {
        this.getVenueAppList(2);
        this.getVenueAppCount();
        // console.log(this.venueAppList);
        

    },
    methods: {
        // 查询所有的场地申请信息
        async getVenueAppList(statusId){
            // console.log("come in");
            const {data:res} = await this.$http.get(`http://localhost:9003/getVenueApp?statusId=${statusId}`);
            if(res.code != 200) {
                this.$notify({
                title: '通知',
                message: '此栏目下暂无相关信息',
                type: 'info'
                });
            }
            this.venueAppList = res.data;
        },

        // 计算场地申请信息数量
        async getVenueAppCount(){
            // console.log("come in");
            const {data:res} = await this.$http.get(`http://localhost:9003/countVenueApp?statusId=2`);
            const {data:ues} = await this.$http.get(`http://localhost:9003/countVenueApp?statusId=3`);
            if(res.code!= 200 & ues.code != 200) return this.$message.error("获取数据失败");
            this.unAuditNum = res.data;
            this.auditNum = ues.data;
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

        changeAppInfoType(){
            if(this.tabPosition==1){
                this.getVenueAppList(2);
                this.ifShowOperat = true;
            }else{
                this.getVenueAppList(3);
                this.ifShowOperat = false;
            }
        },
        // 打开审核通过窗口
        openApproval(code){
            this.$confirm('是否确认同意该场地申请信息?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'success'
            }).then(async () => {
            console.log(code);
            await this.$http.post(`http://localhost:9003/updateAppinfoStatus?code=${code}&statusId=${3}`);
            this.getVenueAppList(2);
            this.getVenueAppCount();
            // 成功，  发异步请求，外层函数加async
            this.$notify({
            title: '成功',
            message: '已同意该场地申请',
            type: 'success'
            });
        }).catch(() => {
            //点取消，什么也不做
        })
        },
        // 打开审核拒绝窗口
        openDelete(code){
            this.$confirm('是否确认拒绝该场地申请信息?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'info'
            }).then(async () => {
            console.log(code);
            await this.$http.post(`http://localhost:9003/updateAppinfoStatus?code=${code}&statusId=${4}`);
            this.getVenueAppList(2);
            this.getVenueAppCount();
            // 成功，  发异步请求，外层函数加async
            this.$notify({
            title: '成功',
            message: '已拒绝该场地申请',
            type: 'success'
            });
        }).catch(() => {
            //点取消，什么也不做
        })
        },
    }
    
}
</script>
<style lang="less" scoped>

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
    margin-left: 25%;
    color: #909399;
}
.title_right{
    margin-left: 50%;
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
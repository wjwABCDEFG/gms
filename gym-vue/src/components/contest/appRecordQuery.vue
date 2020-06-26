<template>
    <div>
        <!-- 表格 -->
        <el-table
        :data="appRecordList"
        style="width: 100%; font-size: 18px;"
        :default-sort = "{prop: 'id', order: 'ascending'}"
        :row-style="{height:'70px'}"
        >
            <el-table-column type="index" min-width='4%'></el-table-column>
            <el-table-column prop="id" min-width='1%' v-if="false"></el-table-column>
            <el-table-column prop="contestName" label="赛事名称" sortable min-width='20%' ></el-table-column>
            <el-table-column prop="" label="场地名称" sortable min-width='20%' ></el-table-column>
            <el-table-column prop="appRecordId" label="审核编号" sortable min-width='20%' ></el-table-column>
            <el-table-column prop="contestTime" label="比赛时间" min-width='20%' ></el-table-column>
            <el-table-column prop="appStatus" label="状态" min-width='20%' ></el-table-column>
            <el-table-column prop="status" label="操作" min-width='25%'>
                <template slot-scope="scope" v-if="scope.row.appStatus == 'WAIT_CONTEST' || scope.row.appStatus == 'ACCEPT_CONTEST'">
                    <el-button type="danger" round @click="cancelApp(scope.row.id, scope.row.venueId, scope.row.eqptId)">取消申请</el-button>
                    <el-button type="primary" round v-if="scope.row.appStatus == 'ACCEPT_CONTEST'" @click="openDialog(scope.row.id, scope.row.venueId, scope.row.eqptId)">上传照片</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 上传照片对话框 -->
        <el-dialog title="上传活动照片" :visible.sync="uploadFormVisible">
            <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="3"
            :on-exceed="handleExceed"
            :file-list="fileList">
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="upload()">确 定</el-button>
            </div>
        </el-dialog>
        <!-- 测试块 -->
        <!-- <div>
            {{appRecordList}}
        </div> -->
    </div>
</template>
<script>
export default {
    data() {
        return {
            appRecordList: [],
            user: '',
            userId: '',
            username: '',
            uploadFormVisible: false,
            fileList: [],
            curId: '',
        }
    },
    methods: {
        // 查找该学生的赛事申请
        async findCtstAppList(){
            const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/selectByUid', this.$qs.stringify({
                'userId': this.userId,
            }));
            this.appRecordList = res.data;
        },
        async cancelApp(contestId, venueId, eqptId){
            // console.log(contestId);
            // console.log(venueId);
            // console.log(eqptId);
            const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/cancelContestApplication', this.$qs.stringify({
                'id': contestId,
            }));
            if(res.code == '200'){
                this.findCtstAppList();
                this.$message.success(res.msg);
            }else{
                this.$message.error(res.msg);
            }
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${ file.name }？`);
        },
        openDialog(contestId, venueId, eqptId){
            this.curId = contestId;
            this.uploadFormVisible = true
        },
        async upload(){
            this.uploadFormVisible = false;
            const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/uploadPic', this.$qs.stringify({
                'id': this.curId,
            }));
            if(res.code == '200'){
                this.findCtstAppList();
                this.$message.success(res.msg);
            }else{
                this.$message.error(res.msg);
            }
        }
    },
    created() {
        this.user = JSON.parse(window.localStorage.getItem("user"));// 取出session里当前登录用户
        console.log(this.user);
        if(this.user != null){
            this.userId = this.user[0].user_id;
            this.username = this.user[0].username;
        }else{
            this.$message.error("未获取到当前登录用户，请重新登录");
        }
        // console.log(this.userId);
        // console.log(this.username);
        this.findCtstAppList();
    }
}
</script>

<style lang="less" scoped>
.container-class{
    margin: auto;
    width: 90%;
    height: 100%;
    /* background-color: lightgray; */
}
</style>
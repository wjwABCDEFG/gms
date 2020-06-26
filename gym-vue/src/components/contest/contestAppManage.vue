<template>
    <div class="container-class">
        <!-- 表格 -->
        <el-table
        :data="contestAppList"
        style="width: 100%; font-size: 18px;"
        :default-sort = "{prop: 'id', order: 'ascending'}"
        :row-style="{height:'70px'}"
        >
            <el-table-column type="index" min-width='4%'></el-table-column>
            <el-table-column prop="id" min-width='1%' v-if="false"></el-table-column>
            <el-table-column prop="contestId" label="赛事编号" sortable min-width='10%' ></el-table-column>
            <el-table-column prop="activityType" label="活动类型" sortable min-width='9%' ></el-table-column>
            <el-table-column prop="sport.sportName" label="赛事类型" sortable min-width='9%' ></el-table-column>
            <el-table-column prop="contestName" label="活动/赛事名称" min-width='15%' ></el-table-column>
            <el-table-column prop="contestTeam" label="比赛队伍" min-width='22%' ></el-table-column>
            <el-table-column prop="contestTime" label="比赛时间" sortable min-width='10%' ></el-table-column>
            <el-table-column prop="activityDescription" label="申请描述" min-width='20%' ></el-table-column>

            <el-table-column prop="status" label="审核" min-width='10%'>
                <template slot-scope="scope">
                    <el-button type="success" icon="el-icon-check" circle @click="checkApp(true, scope.row.id, scope.row.sportId)"></el-button>
                    <el-button type="danger" icon="el-icon-delete" circle @click="checkApp(false, scope.row.id, scope.row.sportId)"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 安排教练对话框表单 -->
        <el-dialog title="为这场赛事安排一个教练" :visible.sync="refereeFormVisible" width="400px">
            <el-form :model="refereeForm" ref="refereeFormRef">
                <el-form-item label="教练" :label-width="formLabelWidth" prop="refereeName">
                    <el-select v-model="refereeForm.refereeId" placeholder="请选择">
                        <!-- 用v-for从返回数据中生成 -->
                        <el-option
                            v-for="item in refereeList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="acceptApp(refereeForm.refereeId)">确 定</el-button>
            </div>
        </el-dialog>
        <!-- 添加按钮 -->
        <el-button type="primary" class="add-btn-class" @click="contestFormVisible = true" round>创建赛事</el-button>
        <!-- 管理员直接创建赛事对话框表单 -->
        <el-dialog title="管理员创建赛事无需审核" :visible.sync="contestFormVisible">
            <!-- 表单部分 -->
            <el-form label-position="left" :model="ctstApplication" :rules="rules" ref="contestAppForm" label-width="100px">
                <el-form-item label="申请编号" prop="contestId">
                    <span class="ctst-appid-class">{{ctstApplication.contestId}}</span>
                </el-form-item>
                <el-form-item label="申请人姓名">
                    <!-- <el-input v-model="ctstApplication.username"></el-input> -->
                    {{username}}
                </el-form-item>
                <el-form-item label="活动类型" prop="activityType">
                    <el-select v-model="ctstApplication.activityType" value-key placeholder="请选择" @change="changeType">
                        <el-option label="上课" value="上课"></el-option>
                        <el-option label="比赛" value="比赛"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item :label="isContest == false ? '活动时间' : '比赛时间'" prop="contestTime">
                    <el-col :span="11">
                        <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="ctstApplication.contestTime" style="width: 100%;"></el-date-picker>
                    </el-col>
                </el-form-item>
                <!-- 如果是比赛才显示 -->
                <el-form-item label="项目" prop="sportId">
                    <el-select v-model="ctstApplication.sportId" placeholder="请选择赛事项目">
                        <!-- 用v-for从返回数据中生成 -->
                        <el-option
                            v-for="item in sportList"
                            :key="item.id"
                            :label="item.sportName"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item :label="isContest == false ? '活动名称' : '赛事名称'" prop="contestName">
                    <el-input v-model="ctstApplication.contestName"></el-input>
                </el-form-item>
                <!-- 比赛时 -->
                <el-form-item label="比赛队伍" required v-show="isContest">
                    <el-col :span="11">
                        <el-form-item prop="contestTeam1">
                            <el-input v-model="ctstApplication.contestTeam1"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="2" style="text-align: center;">
                        vs
                    </el-col>
                    <el-col :span="11">
                        <el-form-item prop="contestTeam2">
                            <el-input v-model="ctstApplication.contestTeam2"></el-input>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <!-- 活动时 -->
                <el-form-item label="班级" required v-show="!isContest">
                    <el-form-item prop="contestTeam1">
                        <el-input v-model="ctstApplication.contestTeam1"></el-input>
                    </el-form-item>
                    <el-form-item prop="contestTeam2" v-show="false">
                        <el-input v-model="ctstApplication.contestTeam2"></el-input>
                    </el-form-item>
                </el-form-item>
                <el-form-item label="活动描述" prop="activityDescription">
                    <!-- <el-input></el-input> -->
                    <el-input type="textarea" v-model="ctstApplication.activityDescription"></el-input>
                </el-form-item>
            </el-form>
            <!-- 确认按钮部分 -->
            <div slot="footer" class="dialog-footer">
                <el-button @click="reset()">重 置</el-button>
                <el-button type="primary" @click="addContest(ctstApplication)">确 定</el-button>
            </div>
        </el-dialog>
        <!-- 测试块 -->
        <!-- <div>
            {{contestAppList}}
        </div> -->
    </div>
</template>
<script>
export default {
    data() {
        return {
            contestAppList: [],
            refereeList: [],
            sportList: [],
            isAccept: false,
            refereeFormVisible: false,
            refereeForm: {
                refereeId: '',
            },
            formLabelWidth: '80px',
            curId: '',
            contestFormVisible: false,
            ctstApplication: {
                contestId: 'GS' + (Math.round((Math.random()+1)*1000)),
                userId: this.userId,
                activityType: '',
                sportId: '',
                contestName: '',
                contestTeam: '',
                contestTeam1: '',
                contestTeam2: ' ',
                activityDescription: '',
                contestTime: ''
            },
            isContest: false,
            rules:{
                username: [
                    { required: true, message: '请输入申请人姓名', trigger: 'blur' },
                    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' },
                ],
                activityType: [
                    { required: true, message: '请选择活动类型', trigger: 'change' },
                ],
                contestTime: [
                    { type: 'string', required: true, message: '请选择日期', trigger: 'change' },
                ],
                sportId: [
                    { required: true, message: '请选择项目', trigger: 'change' },
                ],
                contestName: [
                    { required: true, message: '请填写赛事名称', trigger: 'blur' },
                ],
                contestTeam1: [
                    { required: true, message: '请填写比赛队伍', trigger: 'blur' },
                ],
                contestTeam2: [
                    { required: true, message: '请填写比赛队伍', trigger: 'blur' },
                ],
            },
            user: '',
            userId: '',
            username: '',
        }
    },
    methods: {
        // 查找未审批的赛事申请
        async findCtstAppList(){
            const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/selectByStatus', this.$qs.stringify({
                'status': 'WAIT_CONTEST',
            }));
            this.contestAppList = res.data;
        },
        // 查找某个项目的所有教练
        async findRefereeList(sportId){
            const {data :res} = await this.$http.post('http://localhost:9001/referee/selectBySportId', this.$qs.stringify({
                'sportId': sportId,
            }));
            this.refereeList = res.data;
        },
        // 审批操作
        async checkApp(data, id, sportId){
            // console.log(data);
            // console.log(id);
            // console.log(sportId);
            this.isAccept = data;
            this.curId = id;
            if(this.isAccept){
                // 弹框安排一个教练
                this.refereeFormVisible = true;
                this.findRefereeList(sportId);
            }else{
                // 发送拒绝请求
                const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/checkContestApplication', this.$qs.stringify({
                'id': id,
                'isAccept': this.isAccept,
                }));
                if(res.code == 200){
                    this.findCtstAppList();
                    this.$message.success("已拒绝该申请");
                }else{
                    this.$message.error("操作失败");
                }
            }
        },
        // 发送通过请求
        async acceptApp(refereeId){
            const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/checkContestApplication', this.$qs.stringify({
                'id': this.curId,
                'isAccept': this.isAccept,
                'refereeId': refereeId,
            }));
            if(res.code == 200){
                this.refereeFormVisible = false;
                this.refereeForm.refereeId = '';
                this.findCtstAppList();
                this.$message.success("已通过该申请");
            }else{
                this.$message.error("操作失败");
            }
        },
        // 管理员主动创建赛事
        async addContest(){
            if(this.ctstApplication.activityType == '上课'){
                this.ctstApplication.contestTeam = this.ctstApplication.contestTeam1;
            }else{
                this.ctstApplication.contestTeam = this.ctstApplication.contestTeam1 + ' vs ' + this.ctstApplication.contestTeam2;
            }
            this.ctstApplication.userId = this.userId;
            this.$refs.contestAppForm.validate(async valid => {
                if (!valid) {
                   return ;
                }
                //验证成功,发送请求
                console.log(this.ctstApplication);
                const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/saveContestApplication', this.$qs.stringify(this.ctstApplication));
                if(res.code == 200){
                    this.contestFormVisible = false;
                    this.$message.success("创建成功");
                }else{
                    this.$message.error("创建失败");
                }
            });
        },
        // 查找所有项目，管理员创建赛事的时候用
        async findSport(){
            const {data :res} = await this.$http.post('http://localhost:9001/sport/list', this.$qs.stringify({
                status: 1,
            }));
            this.sportList = res.data;
        },
        // 清空填写表单
        reset(){
            this.$refs.contestAppForm.resetFields();
        },
        changeType(value){
            if (value == '比赛'){
                this.isContest = true;
            }else{
                this.isContest = false;
            }
        },
    },
    created() {
        this.findCtstAppList();
        this.findSport();
        this.user = JSON.parse(window.localStorage.getItem("user"));// 取出session里当前登录用户
        console.log(this.user);
        if(this.user != null){
            this.userId = this.user[0].user_id;
            this.username = this.user[0].username;
        }else{
            this.$message.error("未获取到当前登录用户，请重新登录");
        }
    }
}
</script>

<style lang="less" scoped>
.container-class{
    margin: auto;
    width: 100%;
    /* height: 90%; */
    /* background-color: lightgray; */
}
.add-btn-class{
    position: fixed;
    top: 90%;
    left: 90%;
    z-index:9999
}
.ctst-appid-class{
    color: red;
    font-weight: bold;
}
</style>
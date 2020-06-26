<template>
    <div class="container-class">
        <!-- 进度条 -->
        <el-steps :active="active" finish-status="success" align-center class="loadingbar">
            <el-step title="填写赛事申请信息"></el-step>
            <el-step title="等待审核结果"></el-step>
            <el-step title="获得场地审批号"></el-step>
        </el-steps>
        <!-- 表单 -->
        <div class="form-class" v-show="active == 0">
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
        </div>
        <!-- 已提交，75%进度 -->
        <div v-show="active == 1" class="form-class progress-class">
            <el-progress type="circle" :percentage="75" :width="200"></el-progress>
            <div>
                <div class="succMsg-class">提交成功</div>
                <span>申请编号为{{ctstApplication.contestId}}的赛事申请已提交，待赛事管理员审批通过后即可申请场地和器材</span>
            </div>
        </div>
        <el-button style="margin-top: 12px; margin-left: center;" @click="submit()"  v-show="active == 0">提交申请</el-button>
        <el-button style="margin-top: 12px;" @click="back()" v-show="active == 1">再次申请</el-button>
        <!-- 测试用块 -->
        <!-- <div>
            {{sportList}}
        </div> -->
    </div>
</template>

<script>
export default {
    data() {
        return {
            active: 0,
            user: '',
            userId: '',
            username: '',
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
                contestTime: '',
                roleId: '',
            },
            sportList: [],
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
            isContest: false,
        }
    },
    methods: {
        submit() {
            if(this.ctstApplication.activityType == '上课'){
                this.ctstApplication.contestTeam = this.ctstApplication.contestTeam1;
            }else{
                this.ctstApplication.contestTeam = this.ctstApplication.contestTeam1 + ' vs ' + this.ctstApplication.contestTeam2;
            }
            this.ctstApplication.userId = this.userId;
            console.log(this.ctstApplication);
            this.$refs.contestAppForm.validate(async valid => {
                if (!valid) {
                   return ;
                }
                //验证成功,发送请求
                // console.log(this.user[0].role_id);
                this.ctstApplication.roleId = this.user[0].role_id;
                const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/saveContestApplication', this.$qs.stringify(this.ctstApplication));
                if (this.active++ > 2) this.active = 0;
            });
        },
        back(){
            this.active = 0;
            this.$refs.contestAppForm.resetFields();
            this.ctstApplication.contestId = 'GS' + (Math.round((Math.random()+1)*1000))
        },
        changeType(value){
            if (value == '比赛'){
                this.isContest = true;
            }else{
                this.isContest = false;
            }
        },
        async findSport(){
            const {data :res} = await this.$http.post('http://localhost:9001/sport/list', this.$qs.stringify({
                status: 1,
            }));
            this.sportList = res.data;
            // console.log("0sportList:", sportList);
        }
    },
    created() {
        //查询可用项目
        this.findSport();
        this.user = JSON.parse(window.localStorage.getItem("user"));// 取出session里当前登录用户
        // console.log(this.user);
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
    width: 90%;
    height: 100%;
    /* background-color: lightgray; */
}
.form-class{
    margin: auto;
    width: 50%;
    height: 80%;
    padding: 30px 10px;
    box-sizing: border-box;
    /* background-color: red; */
}
.ctst-appid-class{
    color: red;
    font-weight: bold;
}
.progress-class{
    text-align: center;
    size: 50px;
    padding-top: 10%;
}
.succMsg-class{
    padding-top: 20px;
    padding-bottom: 10px;
    font-size: 27px;
}
</style>
<template>
    <div class="container-class">
        <!-- 头部 -->
        <div class="header-class">
            <span>赛事类别：</span>
            <el-radio-group v-model="radio" @change="selectedSport">
                <el-radio :label="-1">全部</el-radio>  
                <el-radio :label="item.id" v-for="item in sportList" :key="item.id">{{item.sportName}}</el-radio>  
            </el-radio-group>
        </div>
        <!-- 裁判列表 -->
        <div>
            <div class="referee-card-class" v-for="item in refereeList" :key="item.id">
                <div class="in-kuang-class">
                    <div class="referee-pic-class">
                        <img src="../../assets/img/photo.jpeg" @click="showDetail(item.id)" v-if="item.sex == '男'" />
                        <img src="../../assets/img/photo2.jpeg" @click="showDetail(item.id)" v-if="item.sex == '女'" />
                    </div>
                    <span class="demonstration">{{item.name}}</span>
                    <div>
                        <el-switch
                            v-model="item.status"
                            :active-value="1"
                            :inactive-value="0"
                            active-color="#13ce66"
                            inactive-color="#ff4949"
                            @change="changeStatus(item.id, $event)">
                        </el-switch>
                    </div>
                </div>
            </div>
        </div>
        <!-- 细节对话框 -->
        <el-dialog title="细节信息" :visible.sync="dialogDetailFormVisible" width="500px">
            <el-form :model="detailInfo" ref="refereeDetailForm">
                <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="detailInfo.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
                    <el-select v-model="detailInfo.sex" placeholder="请选择">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
                    <el-input v-model="detailInfo.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目" :label-width="formLabelWidth" prop="sportId">
                    <el-select v-model="detailInfo.sportId" placeholder="请选择">
                        <!-- 用v-for从返回数据中生成 -->
                        <el-option
                            v-for="item in sportList"
                            :key="item.id"
                            :label="item.sportName"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="简介" :label-width="formLabelWidth" prop="description">
                    <el-input type="textarea" v-model="detailInfo.description"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="updateReferee(detailInfo)">修 改</el-button>
            </div>
        </el-dialog>
        <!-- 添加按钮 -->
        <el-button type="primary" class="add-btn-class" @click="dialogFormVisible = true" round>添加裁判</el-button>
        <!-- 添加对话框 -->
        <el-dialog title="裁判信息" :visible.sync="dialogFormVisible" width="500px">
            <el-form :model="form" ref="refereeForm">
                <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="性别" :label-width="formLabelWidth" prop="sex">
                    <el-select v-model="form.sex" placeholder="请选择">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话" :label-width="formLabelWidth" prop="phone">
                    <el-input v-model="form.phone" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目" :label-width="formLabelWidth" prop="sportId">
                    <el-select v-model="form.sportId" placeholder="请选择">
                        <!-- 用v-for从返回数据中生成 -->
                        <el-option
                            v-for="item in sportList"
                            :key="item.id"
                            :label="item.sportName"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="简介" :label-width="formLabelWidth" prop="description">
                    <el-input type="textarea" v-model="form.description"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="reset()">重 置</el-button>
                <el-button type="primary" @click="addReferee(form)">确 定</el-button>
            </div>
        </el-dialog>
        <!-- 测试块 -->
        <div>
            <!-- {{sportList}} -->
            <!-- {{refereeList}} -->
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            sportList: [],
            refereeList: [],
            radio: '',
            dialogFormVisible: false,
            dialogDetailFormVisible: false,
            formLabelWidth: '120px',
            form: {
                name: '',
                sex: '',
                phone: '',
                sportId: '',
                description: '',
            },
            detailInfo: {},
        }
    },
    methods: {
        // 查询所有可用项目
        async findSport(){
            const {data :res} = await this.$http.post('http://localhost:9001/sport/list', this.$qs.stringify({
                status: 1,
            }));
            this.sportList = res.data;
        },
        //查询裁判列表
        async findReferee(){
            const {data :res} = await this.$http.post('http://localhost:9001/referee/list');
            if(res.code == '200'){
                this.refereeList = res.data;
            }else{
                this.$message.error(res.msg);
            }
        },
        // 清空填写表单
        reset(){
            // console.log("1111");
            this.$refs.refereeForm.resetFields();
        },
        // 过滤条件
        async selectedSport(sportId){
            console.log(sportId);
            if(sportId == -1){
                return this.findReferee();
            }
            const {data :res} = await this.$http.post('http://localhost:9001/referee/selectBySportId', this.$qs.stringify({
                'sportId': sportId,
            }));
            if(res.code == '200'){
                this.refereeList = res.data;
            }else{
                this.$message.error(res.msg);
            }
        },
        //显示细节
        async showDetail(id){
            this.dialogDetailFormVisible = true;
            const {data :res} = await this.$http.post('http://localhost:9001/referee/selectById', this.$qs.stringify({
                'id': id,
            }));
            if(res.code == '200'){
                this.detailInfo = res.data;
            }
        },
        //添加裁判
        async addReferee(referee){
            // console.log(referee);
            const {data :res} = await this.$http.post('http://localhost:9001/referee/addReferee', this.$qs.stringify(referee));
            this.dialogFormVisible = false;
            //重新加载
            this.findReferee();
            this.$message.success(res.msg);
            this.$refs.refereeForm.resetFields();
        },
        // 修改裁判信息
        async updateReferee(referee){
            // console.log(referee);
            const refereeStored = referee;
            delete refereeStored.sport
            const {data :res} = await this.$http.post('http://localhost:9001/referee/updateReferee', this.$qs.stringify(referee));
            this.dialogDetailFormVisible = false;
            //重新加载
            this.findReferee();
            if(res.code == '200'){
                this.$message.success(res.msg);
            }else{
                this.$message.error(res.msg);
            }
        },
        // 修改状态
        async changeStatus(id, status){
            // console.log(id);
            // console.log(status);
            const {data :res} = await this.$http.post('http://localhost:9001/referee/updateRefereeStatus', this.$qs.stringify({
                'id': id,
                'status': status
            }));
            if(res.code == '200'){
                this.$message.success(res.msg);
            }else{
                this.$message.error(res.msg);
            }
        }
    },
    created() {
        this.findSport();
        this.findReferee();
    }
}
</script>

<style scoped>
.container-class{
    margin: auto;
    width: 90%;
    height: 100%;
    /* background-color: lightgray; */
}
.header-class{
    height: 50px;
    text-align: center;
}
.referee-card-class{
    float: left;
    text-align: center;
    width: 25%;
    height: 340px;
    /* background-color: lightgray; */
}
.in-kuang-class{
    width: 80%;
    margin: auto;
    margin-top: 15px;
    padding-top: 25px;
    padding-bottom: 25px;
    /* margin:10px 20px; */
    background-color: #f9f9f9;
}
.referee-pic-class{
    width: 200px;
    height: 200px;
    border-radius: 50%;
    overflow: hidden;
    margin: auto;
    margin-bottom: 20px;
    /* background-color: red; */
}
img{
    width: 100%;
    cursor: pointer;
}
.add-btn-class{
    position: fixed;
    top: 90%;
    left: 90%;
    z-index:9999
}
</style>
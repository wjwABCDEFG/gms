<template>
    <div class="container-class">
        <!-- 表格 -->
        <el-table
        :data="sportList"
        style="width: 100%; font-size: 20px;"
        :default-sort = "{prop: 'id', order: 'ascending'}"
        :row-style="{height:'70px'}"
        >
            <el-table-column type="index" min-width='20%'></el-table-column>
            <el-table-column prop="id" min-width='20%' v-if="false"></el-table-column>
            <el-table-column prop="sportName" label="运动项目" sortable min-width='90%' ></el-table-column>
            <el-table-column prop="status" label="状态" min-width='10%'>
                <template slot-scope="scope">
                    <el-switch
                        v-model="scope.row.status"
                        :active-value="1"
                        :inactive-value="0"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        @change="changeStatus(scope.row.id, $event)">
                    </el-switch>
                </template>
            </el-table-column>
        </el-table>
        <!-- 对话框表单 -->
        <el-dialog title="项目名称" :visible.sync="dialogFormVisible">
        <el-form :model="form">
            <el-form-item label="项目名称" :label-width="formLabelWidth">
            <el-input v-model="form.sportName" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSport(form.sportName)">确 定</el-button>
        </div>
        </el-dialog>
        <!-- 添加按钮 -->
        <el-button type="primary" class="add-btn-class" @click="dialogFormVisible = true" round>添加项目</el-button>
        <!-- 测试块 -->
        <!-- <div>
            {{sportList}}
        </div> -->
    </div>
</template>
<script>
export default {
    data() {
        return {
            sportList: [],
            value: true,
            dialogTableVisible: false,
            dialogFormVisible: false,
            form: {
                sportName: '',
            },
            formLabelWidth: '80px'
        }
    },
    methods: {
        async findSport(){
            const {data :res} = await this.$http.post('http://localhost:9001/sport/list');
            this.sportList = res.data;
        },
        async changeStatus(sportId, status){
            // console.log(sportId);
            // console.log(status);
            const {data :res} = await this.$http.post('http://localhost:9001/sport/updateSportStatus', this.$qs.stringify({
                'id': sportId,
                'status': status,
            }));
            this.$message.success(res.msg);
        },
        async addSport(sportName){
            // console.log(sportName);
            const {data :res} = await this.$http.post('http://localhost:9001/sport/addSport', this.$qs.stringify({
                'sportName': sportName,
            }));
            this.dialogFormVisible = false;
            //重新加载
            this.findSport();
            this.$message.success(res.msg);
        }
    },
    created() {
        this.findSport();
    }
}
</script>

<style scoped>
.container-class{
    margin: auto;
    width: 60%;
    /* height: 90%; */
    /* background-color: lightgray; */
}
.add-btn-class{
    position: fixed;
    top: 90%;
    left: 90%;
    z-index:9999
}
</style>
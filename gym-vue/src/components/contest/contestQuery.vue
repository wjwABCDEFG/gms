<template>
    <div>
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
            <el-table-column prop="activityType" label="活动类型" sortable min-width='10%' ></el-table-column>
            <el-table-column prop="sport.sportName" label="赛事类型" sortable min-width='10%' ></el-table-column>
            <el-table-column prop="contestName" label="活动/赛事名称" min-width='15%' ></el-table-column>
            <el-table-column prop="contestTeam" label="比赛队伍" min-width='20%' ></el-table-column>
            <el-table-column prop="contestTime" label="比赛时间" sortable min-width='10%' ></el-table-column>
            <el-table-column prop="activityDescription" label="赛事描述" min-width='20%' ></el-table-column>
        </el-table>
    </div>
</template>
<script>
export default {
    data() {
        return {
            contestAppList: [],
        }
    },
    methods: {
        // 查找已通过审批的赛事申请
        async findCtstAppList(){
            const {data :res} = await this.$http.post('http://localhost:9001/contestApplication/selectByStatus', this.$qs.stringify({
                'status': 'ACCEPT_CONTEST',
            }));
            this.contestAppList = res.data;
        },
    },
    created() {
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
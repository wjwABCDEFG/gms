<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>一级目录</el-breadcrumb-item>
        <el-breadcrumb-item>List表格</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 步骤条 -->
        <el-steps :active="3" :space="450" align-center style="margin-top:40px;margin-bottom:30px">
        <el-step title="填写相关信息" icon="el-icon-edit-outline"></el-step>
        <el-step title="确认转账信息" icon="el-icon-s-promotion"></el-step>
        <el-step title="完成" icon="el-icon-s-flag"></el-step>
        </el-steps>


        <div>
            <div class="success">
                <img src="../img/success.png" class="successIcon"/><br>
                <div style="font-size:20px;margin-top:5px">提交成功</div>
                <div style="margin-top:25px;color:#606266">审批号为：{{code}}的场地申请已提交，待场地管理员审批后即可完成场地申请</div>
            </div>
        </div>
        <div class="content">
            <div class="content_header">
                <span class="title">审批号：</span><span class="titleInfo">{{this.code}}</span>
                <span class="title">申请人：</span><span class="titleInfo">{{this.username}}</span>
                <!-- <span>申请日期：</span> -->
            </div>
            <div class="timeLine">
                <el-timeline>
                    <el-timeline-item
                    :key="1"
                    :type="primary"
                    :color="'#0bbd87'"
                    :size="large"
                    :timestamp="contestAppTime">
                    创建申请
                    </el-timeline-item>
                    <el-timeline-item
                    :key="1"
                    :type="primary"
                    :color="'#0bbd87'"
                    :size="large"
                    :timestamp="venueAppTime">
                    赛事审批
                    </el-timeline-item>
                    <el-timeline-item
                    :key="1"
                    :type="primary"
                    :color="'#0bbd87'"
                    :size="large">
                    场地审批
                    </el-timeline-item>
                    <el-timeline-item
                    :key="2"

                    :size="large">
                    完成申请
                    </el-timeline-item>
                </el-timeline>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            code:'',
            username:'',
            venueAppTime:"2020-06-19 20:46",
            contestAppTime: "2020-04-25 14:25"
        }
    },
    created() {
        this.code = window.sessionStorage.getItem('code');
        this.username = window.sessionStorage.getItem('username');
    },
    
}
</script>
<style lang="less" scoped>

.success{ 
    text-align: center;
    margin-right: 18%;
    margin-top: 50px;
}

.successIcon {
    width: 70px;
    height:70px;

}

.content_header {
    text-align: center;
    margin-right: 18%;
    margin-top: 50px;
}
.content{
    padding-top: 1px;
    margin-top:30px;
    margin-left:12%;
    width: 60%;
    background-color: #F5F5F5;
}

.timeLine{
    margin-left: 25%;
    margin-top:30px;
    // font-size: 30px;
}

.title{
    padding-top: 20px;
    font-size: 15px;
    font-weight: bold;
}

.titleInfo{
    margin-top: 20px;
    margin-right:60px;
    font-size:15px;
}

</style>
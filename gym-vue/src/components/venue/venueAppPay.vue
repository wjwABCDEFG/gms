<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>一级目录</el-breadcrumb-item>
        <el-breadcrumb-item>List表格</el-breadcrumb-item>
        </el-breadcrumb>

        <!-- 步骤条 -->
        <el-steps :active="2" :space="450" align-center style="margin-top:40px;margin-bottom:30px">
        <el-step title="填写相关信息" icon="el-icon-edit-outline"></el-step>
        <el-step title="确认转账信息" icon="el-icon-s-promotion"></el-step>
        <el-step title="完成" icon="el-icon-s-flag"></el-step>
        </el-steps>

        <el-alert
            title="确认支付后，资金将转入支付系统，无法退回"
            type="info"
            style="width:35%;height:40px;margin-left:22%"
            show-icon>
        </el-alert>

        <el-form 
        ref="payAppInfoRef" 
        :model="payAppInfo" 
        label-width="100px" 
        :rules="rules"  
        size="medium" 
        style="margin-left:20%;margin-top:20px" >

            <el-form-item label="审批号：" >
                <span  class="appRecordCode">{{payAppInfo.code}}</span>
            </el-form-item>
            <el-form-item label="支付人姓名: ">
                <span  class="appInfo">{{username}}</span>
            </el-form-item>
            <el-form-item label="需支付费用: ">
                <span  class="costInfo">{{cost}}￥</span>
            </el-form-item>
            <el-form-item label="请输入密码: " prop="password">
                <el-input v-model="payAppInfo.password" style="width:150px"></el-input>
            </el-form-item>
            <el-form-item>
            <el-button type="primary" @click="addVenueAppPayInfo()" style="margin-left:10%">下一步</el-button>
            </el-form-item>

        </el-form>
        <el-divider content-position="left"><i class="el-icon-collection-tag"></i> 说明</el-divider>
        <span class="payInstruct">1、转账前请确认好付款金额是否无误</span><br>
        <span class="payInstruct">2、密码项为用户登录系统使用密码</span>
    </div>
</template>
<script>
export default {
    data(){
        return{
            cost:'',
            // code:'',
            username:'',
            payAppInfo:{
                code:'',
                password:'',
            },
            rules: {
            password: [
                { required: true, message: '请输入密码', trigger: 'blur' },
                { min: 6, max:12, message: '密码长度为6-12位', trigger: 'blur' }
            ],
            
            },
        }
    },
     // 类似onload
    created() {
        this.cost = window.sessionStorage.getItem('cost');
        this.payAppInfo.code = window.sessionStorage.getItem('code');
        this.username = window.sessionStorage.getItem('username');
        // console.log(this.appInfo);
        
    },
    methods:{
        resetForm() {
        //  this.$refs[formName].resetFields();
        this.$refs.appInfoRef.resetFields();
        },
        // addVenueAppPayInfo(){
        //     console.log(this.payAppInfo);
            
        // },
        addVenueAppPayInfo() {
        this.$refs.payAppInfoRef.validateField('password', async (valid) => {
            if (valid) {
                this.$notify.error({
                title: '错误',
                message: '密码格式有误'
                });
            } else {
                const {data:res} = await this.$http.post(`http://localhost:9003/updateAppinfoPayStatus?code=${this.payAppInfo.code}&password=${this.payAppInfo.password}`);
                if(res.code == 200) {
                this.$notify({
                    title: '成功',
                    message: '支付成功',
                    type: 'success'
                });
                this.$router.push({ path: "/venueAppFinish"});
                }else{
                this.$notify.error({
                    title: '错误',
                    message: '密码错误'
                });
                }
                
            }
            });
        },
    }
}
</script>
<style lang="less" scoped>

.appInfo {
  font-size:16px;
  color:#606266;
  font-weight:bold;
  margin-top:2px;
  margin-left: 10px;
}

.appRecordCode {
  color:red;
  margin-left: 10px;
  font-size:23px;
  font-weight: bold;
}

.costInfo {
    font-size:25px;
    font-weight:bold;
    margin-left:10px;
}

.payInstruct {
    font-size: 15px;
    color: #606266;
    margin-left: 3%;

}

</style>
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>一级目录</el-breadcrumb-item>
      <el-breadcrumb-item>List表格</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 步骤条 -->
    <el-steps :active="0" :space="450" align-center style="margin-top:40px;margin-bottom:30px">
      <el-step title="填写相关信息" icon="el-icon-edit-outline"></el-step>
      <el-step title="确认转账信息" icon="el-icon-s-promotion"></el-step>
      <el-step title="完成" icon="el-icon-s-flag"></el-step>
    </el-steps>

    <!-- 表单 -->
    <el-form 
      ref="appInfoRef" 
      :model="appInfo" 
      label-width="110px" 
      :rules="rules"  
      size="medium" 
      style="margin-left:20%" 
      
    
    >
      <h5 v-show="!ifCheckCode" style="margin-left:7%;margin-bottom:0.5%;color:red;">（请先输入审批号以继续场地申请）</h5>
      <h5 v-show="ifCheckCode" style="margin-left:8%;margin-bottom:0.5%;color:green;">验证通过，请继续输入信息</h5>

      <el-form-item label="审批号：" prop="code">
        <el-input  v-model="appInfo.code" style="width:400px" v-show="!ifCheckCode" placeholder="请输入审批号"></el-input>
        <!-- 审批号 -->
        <span v-show="ifCheckCode" class="appRecordCode">{{contestAppInfo.appRecordCode}}</span>
        <el-button type="primary" @click="checkCode" v-show="!ifCheckCode" style="margin-left:10px">Check</el-button>
        <!-- <el-button type="primary" @click="resetCode" v-show="ifCheckCode" style="margin-left:40px">Reset</el-button> -->
        <el-button type="info" 
        @click="resetCode"
         v-show="ifCheckCode" 
         icon="el-icon-refresh-right" 
         style="margin-left:20px" circle></el-button>
      </el-form-item>

      <el-form-item label="申请时间：">
        <span v-show="ifCheckCode" class="appInfo">{{contestAppInfo.contestTimeStr}}</span>
      </el-form-item>
      <el-form-item label="申请人姓名：">
        <span v-show="ifCheckCode" class="appInfo">{{contestAppInfo.userName}}</span>
      </el-form-item>
      <el-form-item label="申请活动类型：">
        <span v-show="ifCheckCode" class="appInfo">{{contestAppInfo.activityType}}</span>
      </el-form-item>

      <!-- <el-form-item label="申请人姓名：" prop="name" >
        <el-input v-show="ifCheckCode" v-model="appInfo.name" style="width:400px" placeholder="请输入姓名" ></el-input>
      </el-form-item> -->
      <el-form-item label="场地类型：" prop="venueClass">
        <el-select v-show="ifCheckCode" 
        v-model="classId" 
        @change="classSelectTrigger(classId)"
        placeholder="请选择场地类型" 
        style="width:400px">
          <el-option
                v-for="item in venueClassList"
                :key="item.id"
                :label="item.venueClass"
                :value="item.id">
          </el-option>
        </el-select>
      </el-form-item> 
      <!-- @change="venueSelectTrigger" -->
      <el-form-item label="选择场地：" prop="venue">
        <el-select v-show="ifCheckCode" 
        v-model="appInfo.venueId" 
        placeholder="请选择场地" 
        style="width:400px" >
          <el-option
                v-for="(item,index) in venueInfoList"
                :key="index"
                @click.native="venueSelectTrigger(venueInfoList[index].cost)"
                :label="item.id+'--'+item.venueClass+'--'+item.period+'--'+item.position"
                :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      
      <el-form-item label="申请理由" prop="reason">
        <el-input v-show="ifCheckCode" type="textarea" v-model="appInfo.reason" style="width:400px"></el-input>
      </el-form-item>
      <el-form-item label="需支付费用">
        <span v-show="ifCheckCode" style="font-size:25px;font-weight:bold;"> ￥{{this.cost}} </span>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addVenueAppInfo()">下一步</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>
<script>
export default {
  data() {
    return {
         ifCheckCode:false,
         cost:'0',
         classId:'',
         appDate:"2020年5月30日",
         contestAppInfo:{},
         venueInfoList:[],
         venueClassList:[],

         appInfo: {
          code: '',
          venueId: '',
          reason: '',
      },
      // 验证规则
      rules: {
          reason: [
            { required: true, message: '请输入申请理由', trigger: 'blur' },
            { min: 20, message: '理由描述长至少需20个字', trigger: 'blur' }
          ],
          code: [
            { required: true, message: '请输入审批号', trigger: 'blur' },
            { min: 11, max: 11, message: '审批号长度为11位', trigger: 'blur' }
          ],
        },
    };
  },
  created() {
    this.getVenueClass();
  },
  methods: {
    checkCode() {
      this.$refs.appInfoRef.validateField('code', async (valid) => {
          if (valid) {
             this.$notify.error({
              title: '错误',
              message: '审批号格式有误'
            });
          } else {
            const {data:res} = await this.$http.post(`http://localhost:9003/getContestAppInfo?code=${this.appInfo.code}`);
            if(res.code == 200) {
              this.$notify({
                title: '成功',
                message: '审批号已通过验证',
                type: 'success'
              });
              this.ifCheckCode = true;
              this.contestAppInfo = res.data;
              // console.log(this.contestAppInfo);
              
            }else{
              // console.log("eeeeeeeeeeeeeee");
              
              this.$notify.error({
                title: '错误',
                message: '审批号无效或已使用过该审批号申请过场地'
              });
            }
            
          }
        });
    },
    // 查询所有场地类别
    async getVenueClass(){
      const {data:res} = await this.$http.get("http://localhost:9003/getVenueClass");
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueClassList = res.data;
      // console.log(this.venueClassList);
      
    },
    // 根据类型查询所有的场地信息
    async classSelectTrigger(classId){
      this.venueInfoList = [];
      const {data:res} =  await this.$http.post(`http://localhost:9003/getVenueInfoByClassId?classId=${classId}`);
      if(res.code != 200) return this.$message.error("获取数据失败");
      this.venueInfoList = res.data;
      // console.log(this.venueInfoList);
      
    },

    // 添加场地申请信息
    async addVenueAppInfo(){
      const {data:urs} = await this.$http.post(`http://localhost:9003/checkAppInfo?venueInfoId=${this.appInfo.venueId}&contestTime=${this.contestAppInfo.contestTimeStr}`)
      if(urs.code ==300 ){
        this.$notify.error({
                title: '错误',
                message: '该场地时间段已被申请'
              });
      }else if(urs.code == 400){
        this.$notify.error({
                title: '错误',
                message: '该场地时间段为校方单位使用'
              });
      }else{
        const {data:res} = await this.$http.post("http://localhost:9003/addVenueAppInfo", this.appInfo);
        this.$notify({
          title: '成功',
          message: '该场地可用，申请信息已完成存储',
          type: 'success'
        });
        window.sessionStorage.setItem('cost',this.cost);
        window,sessionStorage.setItem('code', this.appInfo.code);
        window,sessionStorage.setItem('username', this.contestAppInfo.userName);
        console.log(this.contestAppInfo.userName);
        
        this.$router.push({ path: "/venueAppPay"});
      }
    },


    //场地信息选择后设置场地费用
    venueSelectTrigger(cost){
      // console.log(cost);
      
      this.cost = cost;
    },

    checkIfVerification(ifCheckCode){
        if(ifCheckCode == false){
          alert("请先验证审批号");
        }
    },
    resetForm() {
      //  this.$refs[formName].resetFields();
       this.$refs.appInfoRef.resetFields();
    },
    resetCode(){
      this.ifCheckCode = false;
    }
  }
};
</script>
<style lang="less" scoped>

.active{
  color:red;
}
.appRecordCode {
  color:red;
  margin-left: 10px;
  font-size:23px;
  font-weight: bold;
}

.appInfo {
  font-size:16px;
  color:#606266;
  font-weight:bold;
  margin-top:2px;
  margin-left: 10px;
}

.arrow-text {
    font-size:14px;
}

</style>
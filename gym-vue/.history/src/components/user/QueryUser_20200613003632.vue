<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" class="arrow-text">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>一级目录</el-breadcrumb-item>
      <el-breadcrumb-item>List表格</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区 -->
    <el-card>
      <!-- <el-row>
        <el-button type="primary">+ 新 建</el-button>
        <el-button>批量操作</el-button>
        <el-input placeholder="请输入搜索内容" >
            <el-button slot="append" icon="el-icon-search" ></el-button>
        </el-input>
      </el-row> -->
      <el-row :gutter="35">
        <el-col :span="5">
          <el-button type="primary" @click="addDialogVisible = true">+ 新 建</el-button>
          <el-button>批量操作</el-button>
        </el-col>
        <el-col :span="7">
          <!-- 搜索添加 -->
          <el-input placeholder="请输入搜索内容" >
            <el-button slot="append" icon="el-icon-search" ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" >添加用户</el-button>
        </el-col>
      </el-row>

      <!-- 用户列表 -->
      <el-table  :data="venueInfoList" :default-sort ="{prop:'id',order:'ascending'}" style="width: 75%;text-align:center;font-size:15px">
        <el-table-column align="center" sortable label="编号" prop="id"></el-table-column>
        <el-table-column align="center" label="场地类别" prop="venueClass"></el-table-column>
        <el-table-column align="center" label="场地位置" prop="position"></el-table-column>
        <el-table-column align="center" label="场地可预约时间" prop="period"></el-table-column>
        <el-table-column align="center" label="场地情况" prop="">
          <template slot-scope="scope">
            <el-tag v-show="scope.row.status==1">运行中</el-tag>
            <el-tag v-show="scope.row.status==2" type="danger">已关闭</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="费用" prop="">
          <template slot-scope="scope">
            <span style="font-size:18px;font-weight:bold">￥{{scope.row.cost}}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="负责人" prop="">
          <template slot-scope="scope">
            <el-button type="text" @click="showCharge(scope.$index)" size="medium">{{scope.row.venueCharge.name}}</el-button>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">

            <el-button type="text" size="medium" @click="openDelete(scope.row.id)">删除</el-button>
            <el-button type="text" size="medium">修改</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <!-- <el-pagination style="margin-left:400px;margin-top:20px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[10, 20]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination> -->
    </el-card>
  </div>
</template>

<script>
export default {
  
};
</script>

<style lang="less" scoped>

</style>
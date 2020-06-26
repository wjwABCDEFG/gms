<template>
  <!--引入container布局-->
  <el-container class="home-container">
    <!--侧边栏-->
    <el-aside width="200px">
      <div class="ti_box">
        <img src="../assets/gdou2.png" />
        <li>gdou</li>
      </div>
      <el-menu
        background-color="rgb(0, 21, 41)"
        text-color="#fff"
        active-text-color="rgb(23,139,247)"
        :router="true"
        :default-active="activePath"
      >
        <el-menu-item
          :index="item.path"
          v-for="item in menuList"
          :key="item.id"
          @click="saveNavState(item.path)"
        >
          <i :class="iconsObject[item.id]"></i>
          <span slot="title">{{item.title}}</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <!--头部-->
      <el-header>
        <el-button type="primary" plain @click="logout()" style="float:right" icon="el-icon-circle-close" size="small"> 退出</el-button>
      </el-header>
      <!--主体内容-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      //菜单列表
      menuList: [],
      iconsObject: {
        "100": "el-icon-setting",
        "200": "el-icon-search",
        "300": "el-icon-tickets",
        "400": "el-icon-receiving",
        "500": "el-icon-s-release",
        "600": "el-icon-goods",
        "601": "el-icon-search",
        "602": "el-icon-search"
      },
      activePath: "/welcome" //默认路径
    };
  },
  //相当于onload
  created() {
    //查询menuList
    this.getMenuList();
    this.activePath = window.sessionStorage.getItem('activePath'); //取出session里的path 动态修改path
  },
  methods: {
    logout() {
      window.sessionStorage.clear(); //清除session
      this.$router.push("/Login"); //回到首页
    },
    // 获取导航菜单方法
    async getMenuList() {
      const { data: res } = await this.$http.get("http://localhost:9002/menus");
      console.log(res);
      this.menuList = res.menus;
    },
    saveNavState(activePath){
      window.sessionStorage.setItem('activePath',activePath); //存放在session里
      this.activePath = activePath;
    },
  }
};
</script>

<style lang='less' scoped>
//布局器样式
.home-container {
  height: 100%;
}
//头样式
.el-header {
  background-color: white;
  padding: 10px;
}
//侧边栏样式
.el-aside {
  background-color: rgb(0, 21, 41);
  .ti_box {
    height: 60px;
    img {
      width: 100%;
      height: 100%;
    }
    border-right: none;
  }
}
//主体样式
.el-main {
  background-color: rgb(250, 248, 248);
}
</style>
<template>
  <el-container class="home-container">
    <!--头部布局-->
    <el-header>
      <div>
        <img src="../assets/logo.jpg" alt class="logo_img" />
        <span>广东海洋大学体育馆管理系统</span>
      </div>
      <el-button type="info" @click="logout">安全退出</el-button>
    </el-header>

    <el-container>

      <!--侧边布局-->
      <el-aside>
        <!--侧边栏菜单区 unique-opened="true" 只保持一个菜单展开 router开启路由 active-text-color 颜色-->
        <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff" unique-opened :router="true" :default-active="activePath">
          <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
            <template slot="title">
              <span>{{item.title}}</span>
            </template>
              <el-menu-item :index="items.path" v-for="items in item.submenulist" :key="items.id" @click="saveNavState(items.path)">
                <template slot="title">
                  <span>{{items.title}}</span>
                </template>
              </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <!--主体布局-->
      <el-main>
          <!--路由占位符-->
          <router-view></router-view>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>
export default {
    data() {
        return {
            // 左侧菜单
            menuList: [],
            activePath:"/homeinfo",
        }
    },
  //类似onload
    created() {
      this.getMenuList();
      this.activePath = window.sessionStorage.getItem('activePath');// 取出session里的访问路径
      let user=JSON.parse(window.localStorage.getItem("user"));
    },
  methods: {
    logout() {
      window.localStorage.clear();
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    async getMenuList(){
      const {data:res} = await this.$http.get("menus");
      console.log(res.data);
      if( res.flag != 200) return this.$message.error("操作失败！！！");
      this.menuList = res.data;
    },
    // 保存二级菜单的路径
    saveNavState(activePath){
      window.sessionStorage.setItem('activePath',activePath);// 存放点击的二级菜单
      this.activePath = activePath;// 给点击的菜单添加高亮
    },
  }
}
    // // 获取所有的导航菜单
</script>



<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;// 左右贴边
  padding-left: 0%;// 左边界
  align-items: center;// 水平
  color: #fff;
  font-size: 20px;
  > div { //左侧div加布局
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
      font-size: 17px;
    }
  }
}
.el-aside {
  background-color: #333744;
  width:200px;
  .el-menu{
    border-right: none;// 对其右边框
  }
}
.el-main {
  background-color: #eaedf1;
}
.logo_img {
  width: 10%;
  height: 100%;
}
// .iconfont{
//   margin-right: 10px;
// }
// .toggle-button{
//   background-color:#4A5064;
//   font-size: 10px;
//   line-height: 24px;
//   color:#fff;
//   text-align: center;
//   letter-spacing: 0.2em;
//   cursor: pointer;// 显示鼠标指针为：小手
// }
</style>
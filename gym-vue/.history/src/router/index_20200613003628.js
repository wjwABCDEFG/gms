import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import NoPermission from '../components/nopermission.vue'
import Home from '../components/Home.vue'
import HomeInfo from '../components/HomeInfo.vue'
import UserList from '../components/user/UserList.vue'
import ChangeData from '../components/user/ChangeData.vue'
import RoleManage from '../components/user/RoleManage.vue'


Vue.use(VueRouter)

  const routes = [
    {
      path:'/',
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/home',
      component: Home,
      redirect:"/homeinfo",
      children:[
        {path: '/nopermission',component: NoPermission},
        {path:'/homeinfo',component:HomeInfo},
        {path:'/userlist',component:UserList},
        {path:'/changedata',component:ChangeData},
        {path:'/rolemanage',component:RoleManage},
      ]
    }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to,from,next)=>{
  if(to.path=='/login') return next();
  if(to.path=='/register') return next();
  const userFlag = window.localStorage.getItem("user");
  if(!userFlag) return next('/login');
  let user=JSON.parse(userFlag);

  if(to.path=='/rolemanage'){
    if(user.role_id!=1){
      return next('/nopermission');
    }
  }

  next();
})


export default router

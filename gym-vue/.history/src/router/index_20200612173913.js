import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import Home from '../components/Home.vue'
import HomeInfo from '../components/HomeInfo.vue'
import UserList from '../components/user/UserList.vue'
import NoPermission from '../components/nopermission.vue'


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
  console.log(typeof(user.role_id));

  // if(to.path='/userlist'){
  //   if(user.role_id!='2'){
  //     return next('/nopermission');
  //   }
  // }

  next();
})


export default router

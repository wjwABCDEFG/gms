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
import appRecordQuery from '../components/contest/appRecordQuery.vue'
import contestApplication from '../components/contest/contestApplication.vue'
import contestQuery from '../components/contest/contestQuery.vue'
import refereeQuery from '../components/contest/refereeQuery.vue'
import EqptRent from '../components/eqpt/student/EqptRent.vue'
import EqptRentCofirm from '../components/eqpt/student/EqptRentCofirm.vue'
import EqptRentEnd from '../components/eqpt/student/EqptRentEnd.vue'
import EqptRentQuery from '../components/eqpt/student/EqptRentQuery.vue'
import OrderQuery from '../components/eqpt/student/OrderQuery.vue'
import index from '../components/venue/index.vue'
import venueApp from '../components/venue/venueApp.vue'
import venueAppPay from '../components/venue/venueAppPay.vue'
import venueAppFinish from '../components/venue/venueAppFinish.vue'
import venueQuery from '../components/venue/venueQuery.vue'
// 赛事管理员
import contestAppManage from '../components/contest/contestAppManage.vue'
import refereeManage from '../components/contest/refereeManage.vue'
import sportManage from '../components/contest/sportManage.vue'
// 场地管理员
import venueInfoManage from '../components/venue/venueInfoManage.vue'
import venueManage from '../components/venue/venueManage.vue'
import venueAppManage from '../components/venue/venueAppManage.vue'
import specialVenueManage from '../components/venue/specialVenueManage.vue'
import appMissManage from '../components/venue/appMissManage.vue'
// 器材管理员
import EqptManage from '../components/eqpt/admin/EqptManage.vue'
import EqptQuery from '../components/eqpt/admin/EqptQuery.vue'
import EqptVerify from '../components/eqpt/admin/EqptVerify.vue'
import EqptRecycling from '../components/eqpt/admin/EqptRecycling.vue'
import EqptCompensation from '../components/eqpt/admin/EqptCompensation.vue'



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
        {path:'/appRecordQuery',component:appRecordQuery},
        {path:'/contestApplication',component:contestApplication},
        {path:'/contestAppManage',component:contestAppManage},
        {path:'/contestQuery',component:contestQuery},
        {path:'/sportManage',component:sportManage},
        {path:'/refereeQuery',component:refereeQuery},
        {path:'/refereeManage',component:refereeManage},
        {path:'/EqptManage',component:EqptManage},
        {path:'/EqptQuery',component:EqptQuery},
        {path:'/EqptVerify',component:EqptVerify},
        {path:'/EqptRent',component:EqptRent},
        {path:'/EqptRentCofirm',component:EqptRentCofirm},
        {path:'/EqptRentEnd',component:EqptRentEnd},
        {path:'/EqptRecycling',component:EqptRecycling},
        {path:'/EqptRentQuery',component:EqptRentQuery},
        {path:'/EqptCompensation',component:EqptCompensation},
        {path:'/OrderQuery',component:OrderQuery},
        {path:"/index", component:index},
        {path:"/venueInfoManage", component:venueInfoManage},
        {path:"/venueApp", component:venueApp,},
        {path:"/venueManage", component: venueManage},
        {path:"/venueAppPay", component: venueAppPay},
        {path:"/venueAppFinish", component: venueAppFinish},
        {path:"/venueAppManage", component: venueAppManage},
        {path:"/specialVenueManage", component: specialVenueManage},
        {path:"/appMissManage", component: appMissManage},
        {path:"/venueQuery", component: venueQuery},
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
    console.log(user[0].role_id);
    if(user[0].role_id!=1){
      return next('/nopermission');
    }
  }

  if(to.path=='/venueInfoManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=2){
      return next('/nopermission');
    }
  }
  if(to.path=='/venueManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=2){
      return next('/nopermission');
    }
  }
  if(to.path=='/venueAppManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=2){
      return next('/nopermission');
    }
  }
  if(to.path=='/specialVenueManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=2){
      return next('/nopermission');
    }
  }
  if(to.path=='/appMissManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=2){
      return next('/nopermission');
    }
  }

  if(to.path=='/contestAppManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=3){
      return next('/nopermission');
    }
  }
  if(to.path=='/refereeManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=3){
      return next('/nopermission');
    }
  }
  if(to.path=='/sportManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=3){
      return next('/nopermission');
    }
  }

  if(to.path=='/EqptManage'){
    console.log(user[0].role_id);
    if(user[0].role_id!=4){
      return next('/nopermission');
    }
  }
  if(to.path=='/EqptQuery'){
    console.log(user[0].role_id);
    if(user[0].role_id!=4){
      return next('/nopermission');
    }
  }
  if(to.path=='/EqptVerify'){
    console.log(user[0].role_id);
    if(user[0].role_id!=4){
      return next('/nopermission');
    }
  }
  if(to.path=='/EqptCompensation'){
    console.log(user[0].role_id);
    if(user[0].role_id!=4){
      return next('/nopermission');
    }
  }
  if(to.path=='/EqptRecycling'){
    console.log(user[0].role_id);
    if(user[0].role_id!=4){
      return next('/nopermission');
    }
  }
  next();
})


export default router

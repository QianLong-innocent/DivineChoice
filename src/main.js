import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import './assets/icon/iconfont.css'

// 导入 ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios';
import App from './App'
import store from './store/store.js'

// 1.用户第一次登录，调用后端登录接口，发送用户名和密码
// 2.后端验证用户名和密码是否正确，成功则返回token，
// 3.前端拿到token,将token存储在localStorge()和vuex中，并跳转到主页
// 4.前端每次跳转路由就判断localStorge中是否有token,没有就跳转到登录页
// 5.每次调用后端，都要在请求头部添加token
// 6.后端判断是否有token,有token，就验证token，验证成功就返回数据，验证失败，token过期，或没有就返回401
// 7.前端如果，拿到401就清除token并跳转到登录页，

// 引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts


// 安装路由
Vue.use(VueRouter);

// 安装 ElementUI
Vue.use(ElementUI);


new Vue({
	el: '#app',
	// 启用路由
	router,
	store,
	// 启用 ElementUI
	render: h => h(App)
});



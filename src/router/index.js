import Vue from 'vue'

import VueRouter from 'vue-router'
import Router from 'vue-router'
import store from '../store/store.js'
import StudentMain from '../views/student/studentMain.vue'
import Home from '../views/student/index/home.vue'

import TeacherMain from '../views/teacher/teacherMain.vue'
import LeaderMain from '../views/leader/leaderMain.vue'



import Login from '../views/login/login.vue'


Vue.use(Router);

const router = new VueRouter({
	mode: 'history',
	routes: [{
		// 这个表示的是根目录，即一进入的页面
		path: '/',
		// 设置页面一进来就显示的页面，即重定向到goods组件，这里写的内容是对应组将的component的值
		redirect: 'login'
	}, {
		// 登录页
		path: '/login',
		name: 'Login',
		component: Login
	}, {
		// 学生首页
		path: '/student',
		name: 'StudentMain',
		component: StudentMain,
		redirect:Home,
		children: [{
			path: '/student/home',
			name: 'Home',
			component: Home
		}],
		meta: {
			requireAuth: true
		}
		
	}, {
		// 老师首页
		path: '/teacher',
		name: 'TeacherMain',
		meta: {
			requireAuth: true
		},
		component: TeacherMain
	}, {
		// 主任首页
		path: '/leader',
		name: 'LeaderMain',
		meta: {
			requireAuth: true
		},
		component: LeaderMain
	}]
});

export default router;


router.beforeEach((to, from, next) => {


	// 遍历当前访问的url。里面的数据，是否为：true。
	// 是否需要登录
	if (to.matched.some(record => record.meta.requireAuth)) {
		// this route requires auth, check if logged in
		// if not, redirect to login page.
		// 这里面就开始判断，是否有过登录。如果没有登录，那么就重定向到login。反之正常进入

		if (to.matched[0].meta.requireAuth == true) {

			// 如果为true 说明都是必须要进行登陆的
			var uri = store.state.loginUser.substr(1, store.state.loginUser.length - 2)

			// 如果用户尝试跨域访问其他用户界面，自动返回到已有的登陆界面
			if (to.fullPath.indexOf(uri) == -1) {
				next({
					path: uri
				})
			}

			if (window.localStorage.token) {
				next()
			} else if (to.path !== '/login') {
				let token = window.localStorage.token;
				if (token === 'null' || token === '' || token === undefined) {
					next({
						path: '/login'
					})
				}
			} else {
				next()
			}
		}

	} else {
		next() // 确保一定要调用 next()
	}
})

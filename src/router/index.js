import Vue from 'vue'
import VueRouter from 'vue-router'
import Router from 'vue-router'

import Main from '../views/main/main.vue'
import Login from '../views/login/login.vue'





Vue.use(Router);

const router = new VueRouter({
	mode: 'history',
	routes: [{
			// 这个表示的是根目录，即一进入的页面
			path: '/',
			// 设置页面一进来就显示的页面，即重定向到goods组件，这里写的内容是对应组将的component的值
			redirect: 'login'
		},
		{
			// 首页
			path: '/main',
			name: 'Main',
			component: Main
		},
		{
			// 登录页
			path: '/login',
			name: 'Login',
			component: Login
		}
	]
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

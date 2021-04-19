import Vue from 'vue'

import VueRouter from 'vue-router'
import Router from 'vue-router'
import store from '../store/store.js'
import StudentMain from '../views/student/studentMain.vue'
import Home from '../views/student/index/home.vue'
import ChoiceTypeOne from '../views/student/choice/choiceTypeOne.vue'
import ChoiceTypeTwo from '../views/student/choice/choiceTypeTwo.vue'
import AlterMessage from '../views/student/alter/alterMessage.vue'
import AlterPassword from '../views/student/alter/alterPassword.vue'
import AlterVolunteer from '../views/student/alter/alterVolunteer.vue'


import TeacherMain from '../views/teacher/teacherMain.vue'
import HomeTeacher from '../views/teacher/index/home.vue'
import AlterMessageTeacher from '../views/teacher/alter/alterMessage.vue'
import AlterPasswordTeacher from '../views/teacher/alter/alterPassword.vue'
import AddProjectType from '../views/teacher/add/addProjectType.vue'
import AddProject from '../views/teacher/add/addProject.vue'
import MyProject from '../views/teacher/add/myProject.vue'





import LeaderMain from '../views/leader/leaderMain.vue'



import Login from '../views/login/login.vue'


Vue.use(Router);



// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
const originalReplace = VueRouter.prototype.replace;
Router.prototype.replace = function replace(location) {
	return originalReplace.call(this, location).catch(err => err);
};

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
		meta: {
			requireAuth: true
		},
		component: StudentMain,
		redirect: Home,
		children: [{
			path: '/student/home',
			name: 'Home',
			meta: {
				chineseName: '首页'
			},
			component: Home
		}, {
			path: '/student/choiceTypeOne',
			name: 'choiceTypeOne',
			meta: {
				chineseName: '毕业选题',
				chineseName1: '按老师选题'
			},
			component: ChoiceTypeOne
		}, {
			path: '/student/choiceTypeTwo',
			name: 'choiceTypeTwo',
			meta: {
				chineseName: '毕业选题',
				chineseName1: '按题型选题'
			},
			component: ChoiceTypeTwo
		}, {
			path: '/student/alterMessage',
			name: 'alterMessage',
			meta: {
				chineseName: '个人中心',
				chineseName1: '修改个人信息'
			},
			component: AlterMessage
		}, {
			path: '/student/alterPassword',
			name: 'alterPassword',
			meta: {
				chineseName: '个人中心',
				chineseName1: '修改密码'
			},
			component: AlterPassword
		},{
			path: '/student/alterVolunteer',
			name: 'alterVolunteer',
			meta: {
				chineseName: '个人中心',
				chineseName1: '查看志愿'
			},
			component: AlterVolunteer
		}]
	}, {
		// 老师首页
		path: '/teacher',
		name: 'TeacherMain',
		meta: {
			requireAuth: true
		},
		component: TeacherMain,
		redirect: HomeTeacher,
		children: [{
			path: '/teacher/home',
			name: 'HomeTeacher',
			meta: {
				chineseName: '首页'
			},
			component: HomeTeacher
		},{
			path: '/teacher/addProjectType',
			name: 'AddProjectType',
			meta: {
				chineseName: '项目管理',
				chineseName1: '添加项目类型'
			},
			component: AddProjectType
		},{
			path: '/teacher/myProject',
			name: 'MyProject',
			meta: {
				chineseName: '项目管理',
				chineseName1: '我的项目'
			},
			component: MyProject
		},{
			path: '/teacher/addProject',
			name: 'AddProject',
			meta: {
				chineseName: '项目管理',
				chineseName1: '添加项目'
			},
			component: AddProject
		},{
			path: '/teacher/alterMessage',
			name: 'AlterMessageTeacher',
			meta: {
				chineseName: '个人中心',
				chineseName1: '修改个人信息'
			},
			component: AlterMessageTeacher
		},{
			path: '/teacher/alterPassword',
			name: 'AlterPasswordTeacher',
			meta: {
				chineseName: '个人中心',
				chineseName1: '修改密码'
			},
			component: AlterPasswordTeacher
		}]
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

	if(to.path === '/login' && store.state.loginUser !== ""){
		var uri = store.state.loginUser.substr(1, store.state.loginUser.length - 2)
		next({
			path: uri
		})
	}
		
	// console.log(to.path)
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

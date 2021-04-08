<template id="studentMain">

	<el-container style="height: 754px; border: 1px solid #eee">
		<!-- 左侧导航栏 -->
		<studentAside></studentAside>


		<el-container>
			<!-- 面包屑导航 + 用户个人中心 + 退出登录 -->
			<studentHeader></studentHeader>

			<!-- 这里必须改成组件控制 -->
			<el-main style="padding: 0px;overflow: hidden;">

				<router-view></router-view>

			</el-main>

			<el-footer>
				Copyright &copy; 2021.03 - 2021.09 萧兮 . All Rights Reserved . 萧兮制作 版权所有
			</el-footer>

		</el-container>

	</el-container>

</template>

<script>
	import StudentAside from './components/studentAside.vue'
	import StudentHeader from './components/studentHeader.vue'

	export default {
		name: "StudentMain",
		data() {
			return {
				user: JSON.parse(this.$store.state.token)
			}
		},
		methods: {
			b() {
				console.log(this.user)
			}
		},
		components: {
			StudentAside,
			StudentHeader
		},
		// 刷新页面，就自动跳转到首页
		created() {
			// this.$router.replace('/student')
		}


	}
</script>

<style>
	.el-header {
		background-color: #B3C0D1;
		color: #333;
		line-height: 60px;
	}

	.el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	.el-aside {

		color: #333;

	}

	.el-menu-item.is-active {

		background-color: rgb(236, 245, 255) !important;
	}

	.el-main {
		background-color: #E9EEF3;
		color: #333;
	}

	* {
		margin: 0px;
	}
</style>

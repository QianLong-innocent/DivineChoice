<template id="leaderMain">

	<el-container style="height: 754px; border: 1px solid #eee">
		<!-- 左侧导航栏 -->
		<leaderAside></leaderAside>


		<el-container>
			<!-- 面包屑导航 + 用户个人中心 + 退出登录 -->
			<leaderHeader></leaderHeader>

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
	import LeaderAside from './components/leaderAside.vue'
	import LeaderHeader from './components/leaderHeader.vue'

	export default {
		name: "LeaderMain",
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
			LeaderAside,
			LeaderHeader
		},
		// 刷新页面，就自动跳转到首页
		created() {
			// this.$router.replace('/teacher')
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

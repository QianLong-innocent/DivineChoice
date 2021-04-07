<template id="studentHeader">
	<div class="studentHeader">
		<!-- 这里我需要一个面包屑导航 -->
		<el-header style="text-align: right; font-size: 12px">
			
			
			<!-- 面包屑导航 -->
			<breadCrumb style="float: left;"></breadCrumb>
			
			<!-- 右侧个人信息 -->
			<el-dropdown trigger="click" style="cursor: pointer;">
				<i class="el-icon-setting" style="margin-right: 15px;font-size: 20px;"></i>
				<el-dropdown-menu slot="dropdown">
					<el-dropdown-item>个人中心</el-dropdown-item>
					<el-dropdown-item @click.native="a()">退出登陆</el-dropdown-item>
				</el-dropdown-menu>
			</el-dropdown>
			<span style="font-size: x-large;">
				{{user.name}}
			</span>
		</el-header>
	</div>
</template>

<script>
	import BreadCrumb from './breadCrumb.vue'
	export default {
		data() {
			return {
				user: JSON.parse(this.$store.state.token)
			}
		},
		methods: {
			a() {
				this.$store.commit('$_removeStorage', window.localStorage.token);
				this.$router.push("/login")
			}
		},
		components: {
			BreadCrumb
		}
	}
</script>

<style>
</style>

<template id="breadCrumb">
	<div class="breadCrumb">
		<el-breadcrumb separator-class="el-icon-arrow-right">

			<el-breadcrumb-item v-for="name in levelList[1].meta" :key="name"
			 style="margin-top: 16px;font-size: large;">
				{{ name }}
			</el-breadcrumb-item>

		</el-breadcrumb>

	</div>
</template>

<script>
	export default {
		data() {
			return {
				levelList: null
			}
		},
		watch: {
			$route() {
				this.getBreadcrumb()
			}
		},
		methods: {
			getBreadcrumb() {
				//$route.matched一个数组 包含当前路由的所有嵌套路径片段的路由记录
				let matched = this.$route.matched.filter(item => item.name)
				this.levelList = matched
			}
		},
		created() {
			this.getBreadcrumb()
			
		}
	}
</script>

<style>
	
</style>
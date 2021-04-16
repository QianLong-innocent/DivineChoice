<template id="teacherAside">
	<div class="teacherAside">
		<el-aside style="background-color: rgb(238, 241, 246);width: 230px;">
			<el-menu :default-active=select>

				<img src="../../../assets/images/logo.png" height="200px" width="200px" />
				<router-link to="/teacher/home">
					<el-menu-item index="1">
						<i class="iconfont iconshouye" style="margin-left: 4px;"></i>&emsp;首页
					</el-menu-item>
				</router-link>
				<el-submenu index="2">
					<template slot="title"><i class="iconfont iconshezhi"
							style="margin-left: 4px;"></i>&emsp;设置</template>
					<router-link to="/teacher/alterMessage">
						<el-menu-item index="2-1">&emsp;&emsp;修改个人信息
						</el-menu-item>
					</router-link>
					<router-link to="/teacher/alterPassword">
						<el-menu-item index="2-2">&emsp;&emsp;修改密码
						</el-menu-item>
					</router-link>
				</el-submenu>


			</el-menu>

		</el-aside>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				select: "1"
			}
		},
		methods: {

		}
	}
</script>

<style>
	.router-link-active {
		text-decoration: none;
		color: #409EFF;
	}

	a {
		text-decoration: none;
		color: #000;
	}
</style>

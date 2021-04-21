<template id="leaderAside">
	<div class="leaderAside">
		<el-aside style="background-color: rgb(238, 241, 246);width: 230px;">
			<el-menu :default-active=select>

				<img src="../../../assets/images/logo.png" height="200px" width="200px" />
				<router-link to="/leader/home">
					<el-menu-item index="1">
						<i class="iconfont iconshouye" style="margin-left: 4px;"></i>&emsp;首页
					</el-menu-item>
				</router-link>
				<!-- 功能2 为查询 -->

				<el-submenu index="2">
					<template slot="title"><i class="iconfont iconxiangmuguanli"
							style="margin-left: 4px;"></i>&emsp;人员管理</template>
					<router-link to="/leader/registerUser">
						<el-menu-item index="2-1">&emsp;&emsp;注册用户
						</el-menu-item>
					</router-link>
					<router-link to="/leader/messageStu">
						<el-menu-item index="2-2">&emsp;&emsp;学生信息
						</el-menu-item>
					</router-link>
					<router-link to="/leader/messageTea">
						<el-menu-item index="2-3">&emsp;&emsp;教师信息
						</el-menu-item>
					</router-link>
				</el-submenu>



				<el-submenu index="3">
					<template slot="title"><i class="iconfont iconshezhi"
							style="margin-left: 4px;"></i>&emsp;设置</template>
					<router-link to="/leader/alterMessage">
						<el-menu-item index="3-1">&emsp;&emsp;修改个人信息
						</el-menu-item>
					</router-link>
					<router-link to="/leader/alterPassword">
						<el-menu-item index="3-2">&emsp;&emsp;修改密码
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

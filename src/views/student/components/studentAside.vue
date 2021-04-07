<template id="studentAside">
	<div class="studentAside">
		<el-aside style="background-color: rgb(238, 241, 246);width: 230px;">
			<el-menu default-active="1" @select="handleSelect">

				<img src="../../../assets/images/logo.png" height="200px" width="200px" />
				<router-link to="/student/home">
					<el-menu-item index="1">
						<i class="iconfont iconshouye" style="margin-left: 4px;"></i>&emsp;首页
					</el-menu-item>
				</router-link>
				<el-submenu index="2">

					<template slot="title"><i class="iconfont iconxuanti-pingtai"
							style="margin-left: 4px;"></i>&emsp;毕业选题</template>
					<router-link to="/student/choiceTypeOne">
						<el-menu-item index="2-1">&emsp;&emsp;按老师选题
						</el-menu-item>
					</router-link>
					<router-link to="/student/choiceTypeTwo">
						<el-menu-item index="2-2">&emsp;&emsp;按题型选题</el-menu-item>
					</router-link>
				</el-submenu>

			</el-menu>

		</el-aside>
	</div>
</template>

<script>
	export default {
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath)
			}
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

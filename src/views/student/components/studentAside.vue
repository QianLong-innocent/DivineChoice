<template>
	<div class="studentAside">
		<el-aside style="background-color: rgb(238, 241, 246);width: 230px;">
			<el-menu default-active="1" @select="handleSelect">

				<img src="../../../assets/images/logo.png" height="200px" width="200px" />

				<el-menu-item index="1">
					<i class="iconfont iconshouye" style="margin-left: 4px;"></i>&emsp;
					<router-link to="/student/home">首页</router-link>
				</el-menu-item>
				
				<el-submenu index="2">
					

					<template slot="title"><i class="iconfont iconxuanti-pingtai"
							style="margin-left: 4px;"></i>&emsp;毕业选题</template>

					<el-menu-item index="2-1">&emsp;&emsp;按老师选题</el-menu-item>

					<el-menu-item index="2-2">&emsp;&emsp;按题型选题</el-menu-item>

				</el-submenu>

			</el-menu>
			
		</el-aside>
	</div>
</template>

<script>
	export default {
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			}
		}
	}
</script>

<style>
</style>

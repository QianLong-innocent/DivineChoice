<template>
	<div>
		首页
		<button @click="a()">退出登陆</button>
	</div>
</template>

<script>
	export default {
		name: "StudentMain",
		methods: {
			a() {
				this.$store.commit('$_removeStorage', window.localStorage.token);
				this.$router.push("/login")
			}
		}
	}
</script>

<style scoped>




</style>

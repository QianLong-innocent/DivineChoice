<template>
	<div>
		首页
		<button @click="b()">token</button>
		<button @click="a()">退出登陆</button>
	</div>
</template>

<script>
	export default {
		name: "StudentMain",
		methods: {
			b() {
				console.log(this.$store.state.token)
				console.log(this.$store.state)
			},
			a() {
				this.$store.commit('$_removeStorage', window.localStorage.token);
				this.$router.push("/login")
			}
		}
	}
</script>

<style scoped>




</style>

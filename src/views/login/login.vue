<template id="login">

	<div class="backgroun-img">
		<el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box" status-icon>
			<h3 class="login-title">毕业设计选题管理系统</h3>
			<el-form-item label="学/工号" prop="username">
				<el-input type="text" placeholder="请输入账号" v-model="form.username" prefix-icon="el-icon-user"
					@keyup.enter.native="onSubmit('loginForm')" />
			</el-form-item>
			<el-form-item label="密码" prop="password">
				<el-input type="password" placeholder="请输入密码" v-model="form.password" prefix-icon="el-icon-lock"
					show-password @keyup.enter.native="onSubmit('loginForm')" />
			</el-form-item>
			<el-form-item label="用户类型" prop="type">
				<el-select placeholder="请选择用户类型" style="width: 100%;" v-model="form.type">
					<i slot="prefix" class="iconfont iconyonghuleixing" style="margin-left: 4px;"></i>
					<el-option label="学生" value="student"></el-option>
					<el-option label="教师" value="teacher"></el-option>
					<el-option label="主任" value="director"></el-option>
				</el-select>
			</el-form-item>
			<!-- <el-checkbox v-model="rememberPw" checked class="remember">记住密码</el-checkbox> -->
			<el-form-item label="验证码" prop="verifycode">
				<el-input v-model="form.verifycode" placeholder="请输入验证码" style="width: 150px;"
					@keyup.enter.native="onSubmit('loginForm')">
					<i slot="prefix" class="iconfont iconyanzhengma" style="margin-left: 4px;"></i>
				</el-input>

				<div @click="refreshCode" style="float: right;">
					<s-identify :identifyCode="identifyCode"></s-identify>
				</div>
			</el-form-item>

			<el-form-item>
				<el-button type="primary" round @click="onSubmit('loginForm')" style="width: 250px;" :plain="true">登录
				</el-button>
			</el-form-item>
			<el-tag type="danger">忘记密码，请联系管理员：10086</el-tag>

		</el-form>
	</div>

</template>

<script>
	import axios from 'axios';

	import SIdentify from './verification.vue'


	export default {
		name: "Login",
		data() {
				
			
				
			var checkID = (rule, value, callback) => {

				if (value === '') {
					return callback(new Error('学/工号不能为空'));
				} else if (value.length < 7) {
					return callback(new Error('学/工号不能低于七位数'));
				} else if (value.length > 10) {
					return callback(new Error('学/工号不能高于十位数'))
				}
				callback();

			};
			var checkPass = (rule, value, callback) => {
				if (value === '') {
					return callback(new Error('密码不能为空'));
				}
				callback();
			};
			// 验证码自定义验证规则
			var validateVerifycode = (rule, value, callback) => {
				var identifyCode = this.identifyCode.toLowerCase()
				var value = value.toLowerCase()
				if (value === '') {
					callback(new Error('请输入验证码'))
				} else if (value !== identifyCode) {
					this.identifyCode = ''
					this.makeCode(this.identifyCodes, 4)
					callback(new Error('验证码不正确!'))
				} else {
					callback()
				}
			};

			return {
				
				identifyCodes: '1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz',
				identifyCode: '',
				form: {
					username: '',
					password: '',
					verifycode: '',
					type: '',

				},

				// 表单验证，需要在 el-form-item 元素中增加 prop 属性
				rules: {
					username: [{
						required: true,
						validator: checkID,
						trigger: 'blur'
					}],
					password: [{
						required: true,
						validator: checkPass,
						trigger: 'blur'
					}],
					verifycode: [{
						required: true,
						trigger: 'blur',
						validator: validateVerifycode
					}],
					type: [{
						required: true,
						message: '请输入用户类型',
						trigger: 'change'
					}]
				}

			}
		},
		components: {
			SIdentify
		},
		mounted() {
			// 验证码初始化
			this.identifyCode = ''
			this.makeCode(this.identifyCodes, 4)
		},
		methods: {
			peoplePass(url) {
				// 开始登录验证
				let that = this
				
				axios.post(url, {
						username: this.form.username,
						password: this.form.password
					})
					.then(function(response) {
						
						// 解决方法：转换为json格式进行存取
						// 通过 接口url进入不同的界面
						if (url.indexOf('student') != -1) {
							that.$message({
								message: '登陆成功',
								type:'success',
								duration:'1000',
								center: true
							});
							that.timer = setTimeout(() => { //设置延迟执行
								that.$store.commit('$_setUserType', JSON.stringify('/student'))
								that.$store.commit('$_setToken', JSON.stringify(response.data));
								that.$router.push('/student')
							}, 1000);
							
						} else if (url.indexOf('teacher') != -1) {
							that.$message({
								message: '登陆成功',
								type:'success',
								duration:'1000',
								center: true
							});
							that.timer = setTimeout(() => { //设置延迟执行
								that.$store.commit('$_setUserType', JSON.stringify('/teacher'))
								that.$store.commit('$_setToken', JSON.stringify(response.data));
								that.$router.push('/teacher')
							}, 1000);
							
						} else if (url.indexOf('leader') != -1) {
							that.$message({
								message: '登陆成功',
								type:'success',
								duration:'1000',
								center: true
							});
							that.timer = setTimeout(() => { //设置延迟执行
								that.$store.commit('$_setUserType', JSON.stringify('/leader'))
								that.$store.commit('$_setToken', JSON.stringify(response.data));
								that.$router.push('/leader')
							}, 1000);
							
						}

					})
					.catch(function(error) {
						
						// 编写登陆失败以及反馈信息给用户，明白自己哪里错误了。这里会返回404
						that.loginFailed()
						
					});
			},
			loginFailed() {
				this.$message.error('用户名或密码输入错误!');
				this.identifyCode = ''
				this.makeCode(this.identifyCodes, 4)
				this.form.verifycode = ''
				this.form.password = ''
			},
			onSubmit(formName) {

				// 为表单绑定验证功能
				this.$refs[formName].validate((valid) => {

					// 当用户，没有按照规则填写登录信息，就会是false
					if (valid) {
						if (this.form.type === 'student') {
							this.peoplePass(
								'http://localhost:8080/api/studentInfos/loginByPrimaryKeyWithPassword');
						} else if (this.form.type === 'teacher') {
							this.peoplePass(
								'http://localhost:8080/api/teacherInfos/loginByPrimaryKeyWithPassword');
						} else {
							this.peoplePass(
								'http://localhost:8080/api/leaderInfos/loginByPrimaryKeyWithPassword');
						}
					}

				});
			},
			// 生成随机数
			randomNum(min, max) {
				return Math.floor(Math.random() * (max - min) + min)
			},
			// 切换验证码
			refreshCode() {
				this.identifyCode = ''
				this.makeCode(this.identifyCodes, 4)
			},
			// 生成四位随机验证码
			makeCode(o, l) {
				for (let i = 0; i < l; i++) {
					this.identifyCode += this.identifyCodes[
						this.randomNum(0, this.identifyCodes.length)
					]
				}
				console.log(this.identifyCode)
			}

		}
	}
</script>

<style lang="scss" scoped>
	.backgroun-img {
		background: url("../../assets/images/background-login.jpg");
		background-size: 100% 100%;
		height: 100%;
		width: 100%;
		position: fixed;
	}

	.login-box {
		border: 1px solid #DCDFE6;
		width: 350px;
		position: absolute;
		left: 550px;
		top: 15%;
		padding: 35px 35px 15px 15px;
		border-radius: 5px;
		-webkit-border-radius: 5px;
		-moz-border-radius: 5px;
		box-shadow: 0 0 25px #909399;
		background-color: #FFFFFF;
	}

	.login-title {
		text-align: center;
		margin: 0 auto 40px auto;
		margin-left: 50px;
		color: #303133;
	}
</style>

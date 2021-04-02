<template>

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


			<el-form-item style="margin: 20px;">
				<el-button type="primary" round @click="onSubmit('loginForm')">登录</el-button>
				&emsp;&emsp;
				<el-button type="warning" round @click="registerForm('loginForm')">注册</el-button>
			</el-form-item>

		</el-form>

		<el-dialog title="温馨提示" :visible.sync="dialogVisible" width="25%">
			<span>该用户不存在或者密码输入错误</span>
			<span slot="footer" class="dialog-footer">
				<el-button type="primary" @click="dialogVisible = false">确 定</el-button>
			</span>
		</el-dialog>
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
				} else if (value.length < 8) {
					return callback(new Error('学/工号不能低于八位数'));
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
				this.identifyCode = this.identifyCode.toLowerCase()
				value = value.toLowerCase()
				if (value === '') {
					callback(new Error('请输入验证码'))
				} else if (value !== this.identifyCode) {
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
					keywords: '*&^&*%^&%^&$%^'
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
				},

				// 对话框显示和隐藏
				dialogVisible: false

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

			onSubmit(formName) {

				// 为表单绑定验证功能
				this.$refs[formName].validate((valid) => {

					// 当用户，没有按照规则填写登录信息，就会是false
					if (valid) {
						// 开始登录验证
						let that = this
						axios.get(
								'http://musicapi.leanapp.cn/search', {
									params: {
										keywords: this.form.keywords
									}
									// password: this.form.password
								})
							.then(function(response) {
								// 前后端一个约定。如何如何，是一个规定。如果怎么怎么样，就登陆失败
								if (response.data.code === '404') {
									that.dialogVisible = true
								} else {
									console.log(response)
									var userInfo = response.data.data;
									that.$store.commit('$_setToken', userInfo);
									that.$router.push('/main')
								}
							})
							.catch(function(error) {
								console.log(error);
							});
					}
				});
			},
			registerForm(formName) {
				console.log(this.$refs[formName])
				this.$refs[formName].validate((valid) => {
					console.log(valid)
				})
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
		margin-top: -10px;
		margin-left: -9px;
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

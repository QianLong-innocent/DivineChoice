<template>
	<div style="margin-top: 100px;margin-left: 50px;">
		<el-form label-width="80px" :model="form" status-icon :rules="rules" ref="alterPassword">
			<el-form-item label="原密码" prop="originalPassword">
				<el-input v-model="form.originalPassword" style="width: 200px;" prefix-icon="el-icon-lock" show-password
					placeholder="请输入原密码"></el-input>
			</el-form-item>
			<el-form-item label="修改密码" prop="alterPassword">
				<el-input v-model="form.alterPassword" style="width: 200px;" prefix-icon="el-icon-lock" show-password
					placeholder="请输入修改之后密码" @keyup.enter.native="onSubmit('alterPassword')">
				</el-input>
			</el-form-item>
			<el-form-item label="确认密码" prop="confirmPassword">
				<el-input v-model="form.confirmPassword" style="width: 200px;" prefix-icon="el-icon-lock" show-password
					placeholder="请确认修改之后密码" @keyup.enter.native="onSubmit('alterPassword')">
				</el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" round style="width: 100px;" @click="onSubmit('alterPassword')" :plain="true">
					确认
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	import axios from 'axios';
	import {
		Message
	} from 'element-ui';
	export default {
		name: 'AlterPassword',
		data() {

			var checkPassword = (rule, value, callback) => {

				if (value === '') {
					return callback(new Error('原密码不能为空'))
				} else if (value !== JSON.parse(this.$store.state.token).password) {
					return callback(new Error('与原密码不一致'))
				}
				callback()

			}

			var checkalterPass = (rule, value, callback) => {

				if (value === '') {
					return callback(new Error('修改密码不能为空'))
				} else if (this.form.confirmPassword !== '') {
					if (value !== this.form.confirmPassword) {
						return callback(new Error('两次密码不一致'))
					}
				}
				callback()

			}

			var checkConfirmPass = (rule, value, callback) => {
				if (value === '') {
					return callback(new Error('确认密码不能为空'))
				} else if (this.form.alterPassword !== '') {
					if (value !== this.form.alterPassword) {
						return callback(new Error('两次密码不一致'))
					}
				}
				callback()
			}


			return {
				form: {
					originalPassword: '',
					alterPassword: '',
					confirmPassword: ''
				},
				// 表单验证，需要在 el-form-item 元素中增加 prop 属性
				rules: {
					originalPassword: [{
						required: true,
						validator: checkPassword,
						trigger: 'blur'
					}],
					alterPassword: [{
						required: true,
						validator: checkalterPass,
						trigger: 'blur'
					}],
					confirmPassword: [{
						required: true,
						validator: checkConfirmPass,
						trigger: 'blur'
					}]

				}
			}
		},
		methods: {
			onSubmit(form) {

				// 为表单绑定验证功能
				this.$refs[form].validate((valid) => {

					// 当用户，没有按照规则填写登录信息，就会是false
					if (valid) {

						this.$confirm('此操作将修改密码, 是否继续?', '提示', {
							confirmButtonText: '确定',
							cancelButtonText: '取消',
							type: 'warning'
						}).then(() => {
							
							// 开始登录验证
							let that = this
							axios.put("http://localhost:8080/api/teacherInfos/updateByPrimaryKeyChangePassword", {
								teacher_id: JSON.parse(this.$store.state.token).teacher_id,
								passwordBefore: this.form.originalPassword,
								password: this.form.alterPassword
							}).then(function(response) {
								Message.success("更新密码成功")
							
								var flag = JSON.parse(that.$store.state.token)
								flag.password = that.form.alterPassword
							
								that.$store.commit('$_setToken', JSON.stringify(flag));
							
								// console.log(JSON.parse(that.$store.state.token))
								that.form.originalPassword = ''
								that.form.alterPassword = ''
								that.form.confirmPassword = ''
							
							}).catch(function(error) {
								Message.error("原密码错误")
							})
							
						}).catch(() => {
							this.$message({
								type: 'error',
								message: '已取消修改密码'
							});
						});
						
					}

				});
			}
		}
	}
</script>

<style>
</style>

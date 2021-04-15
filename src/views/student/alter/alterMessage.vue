<template>
	<div style="margin-left: 30%;margin-top: 20px;">
		<el-form label-width="80px" :model="userMessageform" ref="userMessageform" :rules="rules">
			<!-- <div style="float: left;"> -->
			<el-form-item label="学号">
				<el-input v-model="userMessageform.user.student_id" :disabled="true" prefix-icon="el-icon-user">
				</el-input>
			</el-form-item>
			<el-form-item label="姓名">
				<el-input v-model="userMessageform.user.name" :disabled="true">
					<i slot="prefix" class="iconfont iconziwopingjia" style="margin-left: 4px;"></i>
				</el-input>
			</el-form-item>
			<el-form-item label="身份证号" prop="ID_Card">
				<el-input v-model="userMessageform.ID_Card" placeholder="身份证号">
					<i slot="prefix" class="iconfont iconshenfenzheng"></i>
				</el-input>
			</el-form-item>
			<el-form-item label="性别" prop="sex">

				<el-select placeholder="性别" style="width: 200px;" v-model="userMessageform.sex">
					<i slot="prefix" class="iconfont iconxingbie" style="margin-left: 4px;"></i>
					<el-option label="男" value="男"></el-option>
					<el-option label="女" value="女"></el-option>
				</el-select>
			</el-form-item>
			<!-- </div> -->
			<!-- <div style="float: right;margin-right: 15%;"> -->
			<el-form-item label="出生日期" prop="birthday">
				<el-date-picker type="date" placeholder="选择日期" v-model="userMessageform.birthday"
					value-format="yyyy-MM-dd"></el-date-picker>
			</el-form-item>
			<el-form-item label="电话" prop="phone">
				<el-input v-model="userMessageform.phone" placeholder="电话">
					<i slot="prefix" class="iconfont icondianhua"></i>
				</el-input>
			</el-form-item>
			<el-form-item label="邮箱" prop="email">
				<el-input v-model="userMessageform.email" placeholder="邮箱">
					<i slot="prefix" class="iconfont iconyouxiang"></i>
				</el-input>
			</el-form-item>
			<el-form-item label="自我评价" prop="remark">
				<el-input v-model="userMessageform.remark" type="textarea" placeholder="自我评价" style="width: 400px;"
					rows="5">
				</el-input>
			</el-form-item>
			<!-- </div> -->
			<!-- <div style="margin-left: 200px;"> -->
			<el-form-item style="margin-top: -14px;">
				<el-button type="primary" round style="width: 150px;" @click="onSubmit('userMessageform')"
					:plain="true">
					修改个人信息
				</el-button>
			</el-form-item>
			<!-- </div> -->
		</el-form>
	</div>
</template>

<script>
	import axios from 'axios';
	import {
		Message
	} from 'element-ui';


	function insertStr(soure, firstPosition, firstStr, secondPosition, secondStr) {
		soure = soure.slice(0, firstPosition) + firstStr + soure.slice(firstPosition)
		return soure.slice(0, secondPosition) + secondStr + soure.slice(secondPosition)
	}

	export default {
		name: 'AlterMessage',
		data() {
			var checkID_Card = (rule, value, callback) => {
				if (value.length !== 18) {
					callback(new Error('身份证号长度不正确'))
				} else {
					if (!/^\d{17}(\d|x)$/i.test(value)) {
						callback(new Error('身份证号格式不正确'))
					} else {
						// console.log(value.substring(6,14))
						if (!/^(19|20)\d{2}(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])$/i.test(value.substring(6, 14))) {
							callback(new Error('请检查生日'))
						}
						var arr = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]
						var arr2 = value.split('');
						var total = 0;
						for (var i = 0; i < arr.length; i++) {
							total += ~~arr[i] * ~~arr2[i]
						}
						// 校验码
						// console.log(total % 11)
						//最后一位的值
						var answer = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2][total % 11]
						if (answer === Number(arr2[17])) {
							if (arr2[16] % 2 === 1) {
								this.userMessageform.sex = '男'
							} else {
								this.userMessageform.sex = '女'
							}

							var birthday = this.userMessageform.ID_Card.substring(6, 14)
							var newBirthday = insertStr(birthday, 4, '-', 7, '-')
							this.userMessageform.birthday = newBirthday


							callback()
						} else {
							callback(new Error('身份证号输入错误'))
						}
					}
				}
			}

			var checkSex = (rule, value, callback) => {

				if (value === '男') {
					value = 1
				} else {
					value = 0
				}
				var arr = this.userMessageform.ID_Card.split('');
				if (value !== Number(arr[16] % 2)) {
					callback(new Error('与身份证号不符合'))
				}
				callback()
			}

			var checkBirthday = (rule, value, callback) => {
				// console.log(value)

				var birthday = this.userMessageform.ID_Card.substring(6, 14)
				var newBirthday = insertStr(birthday, 4, '-', 7, '-')
				// console.log(newBirthday)
				if (value !== newBirthday) {
					callback(new Error('与身份证号不符合'))
				}
				callback()
			}

			var checkPhone = (rule, value, callback) => {
				if (!/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(value)) {
					callback(new Error('手机号有误，请重填'))
				}
				callback()
			}


			var checkEmail = (rule, value, callback) => {
				var patten =
					/^([a-zA-Z0-9]+[_|_|\-|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				if (!patten.test(value)) {
					callback(new Error('邮箱有误，请重填'))
				}
				callback()
			}

			var checkRemark = (rule, value, callback) => {
				callback()
			}



			return {
				userMessageform: {
					user: JSON.parse(this.$store.state.token),
					ID_Card: '',
					sex: '',
					birthday: '',
					phone: '',
					email: '',
					remark: ''
				},
				// 为表单验证
				rules: {
					ID_Card: [{
						validator: checkID_Card,
						trigger: 'blur'
					}],
					sex: [{
						validator: checkSex,
						trigger: 'change'
					}],
					birthday: [{
						validator: checkBirthday,
						trigger: 'blur'
					}],
					phone: [{
						validator: checkPhone,
						trigger: 'blur'
					}],
					email: [{
						validator: checkEmail,
						trigger: 'blur'
					}],
					remark: [{
						validator: checkRemark,
						trigger: 'blur'
					}]
				}
			}
		},
		methods: {
			onSubmit(userMessageform) {
				// 为表单绑定验证功能
				this.$refs[userMessageform].validate((valid) => {

					// 当用户，没有按照规则填写登录信息，就会是false
					if (valid) {

						this.$confirm('此操作将修改个人信息, 是否继续?', '提示', {
							confirmButtonText: '确定',
							cancelButtonText: '取消',
							type: 'warning'
						}).then(() => {
							// 开始获取个人信息
							let that = this
							axios.put(
								"http://localhost:8080/api/studentInfos/updateByPrimaryKeyChangeMessage", {
									student_id: JSON.parse(this.$store.state.token).student_id,
									password: JSON.parse(this.$store.state.token).password,
									name: JSON.parse(this.$store.state.token).name,
									id_card: this.userMessageform.ID_Card,
									sex: this.userMessageform.sex,
									birthday: this.userMessageform.birthday,
									phone: this.userMessageform.phone,
									email: this.userMessageform.email,
									remark: this.userMessageform.remark
								}).then(function(response) {

								Message.success("更新成功")
								// 随之更新token
								that.$store.commit('$_setToken', JSON.stringify(response.data));


							}).catch(function(error) {
								Message.error("请检查网络")
							})

						}).catch(() => {
							this.$message({
								type: 'error',
								message: '已取消修改'
							});
						});

					}

				});
			}
		},
		mounted() {
			// 自动填充 
			this.userMessageform.ID_Card = JSON.parse(this.$store.state.token).id_card
			this.userMessageform.sex = JSON.parse(this.$store.state.token).sex
			this.userMessageform.birthday = JSON.parse(this.$store.state.token).birthday
			this.userMessageform.phone = JSON.parse(this.$store.state.token).phone
			this.userMessageform.email = JSON.parse(this.$store.state.token).email
			this.userMessageform.remark = JSON.parse(this.$store.state.token).remark
		}
	}
</script>

<style scoped="scoped">
	.el-input {
		width: 200px;
	}
</style>

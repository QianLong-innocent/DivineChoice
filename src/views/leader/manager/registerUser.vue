<template>
	<div style="margin-top: 100px;">
		<div style="float: left;margin-left: 200px;">
			<el-form label-width="80px" :model="registerStudentUser" ref="registerStudentUser" :rules="rulesOne">
				<el-form-item label="学号" prop="student_id">
					<el-input v-model="registerStudentUser.student_id" :disabled="true" prefix-icon="el-icon-user"
						style="width: 200px;">
					</el-input>
				</el-form-item>
				<el-form-item label="姓名" prop="name">
					<el-input v-model="registerStudentUser.name" style="width: 200px;">
						<i slot="prefix" class="iconfont iconziwopingjia" style="margin-left: 4px;"></i>
					</el-input>
				</el-form-item>
				<el-form-item label="院系" prop="type">
					<el-input v-model="registerStudentUser.department" :disabled="true" prefix-icon="el-icon-user"
						style="width: 200px;">
					</el-input>
				</el-form-item>

				<el-form-item label="专业" prop="professional">
					<el-select placeholder="专业" style="width: 200px;" v-model="registerStudentUser.professional">
						<i slot="prefix" class="iconfont iconzhuanye" style="margin-left: 4px;"></i>
						<el-option label="计算机科学与技术" value="计科"></el-option>
						<el-option label="软件工程" value="软工"></el-option>
					</el-select>
				</el-form-item>

				<el-form-item style="">
					<el-button type="primary" round style="width: 150px;"
						@click="onSubmitStudent('registerStudentUser')" :plain="true">
						确认注册该学生
					</el-button>
				</el-form-item>
			</el-form>
		</div>

		<div style="float: right;margin-right: 400px;">
			<el-form label-width="80px" :model="registerTeacherUser" ref="registerTeacherUser" :rules="rulesTwo">
				<el-form-item label="工号" prop="teacher_id">
					<el-input v-model="registerTeacherUser.teacher_id" :disabled="true" prefix-icon="el-icon-user"
						style="width: 200px;">
					</el-input>
				</el-form-item>
				<el-form-item label="姓名" prop="name">
					<el-input v-model="registerTeacherUser.name" style="width: 200px;">
						<i slot="prefix" class="iconfont iconziwopingjia" style="margin-left: 4px;"></i>
					</el-input>
				</el-form-item>
				<el-form-item style="">
					<el-button type="primary" round style="width: 150px;"
						@click="onSubmitTeacher('registerTeacherUser')" :plain="true">
						确认注册该教师
					</el-button>
				</el-form-item>
			</el-form>
		</div>


	</div>
</template>

<script>
	import axios from 'axios';
	import {
		Message
	} from 'element-ui';
	export default {
		name: "RegisterUser",
		data() {
			var checkStudentName = (rule, value, callback) => {

				if (value === '') {
					this.registerStudentUser.student_id = ''
					callback(new Error('姓名不能为空'))
				} else {
					var time = new Date();
					var t = this.randomNum(3, 5)
					var s = time.getTime().toString()
					this.registerStudentUser.student_id = s.substring(t, s.length - 1)
					callback()
				}
			}
			var checkTeacherName = (rule, value, callback) => {
				if (value === '') {
					callback(new Error('姓名不能为空'))
				} else {
					var time = new Date();
					var t = this.randomNum(3, 5)
					var s = time.getTime().toString()
					this.registerTeacherUser.teacher_id = s.substring(t, s.length - 1)
					callback()
				}
			}
			return {
				registerStudentUser: {
					student_id: '',
					name: '',
					department: '电气信息学院',
					professional: ''
				},
				registerTeacherUser: {
					teacher_id: '',
					name: ''
				},
				rulesOne: {
					name: [{
						required: true,
						validator: checkStudentName,
						trigger: 'blur'
					}],
					professional: [{
						required: true,
						message: '请选择学生专业',
						trigger: 'change'
					}]
				},
				rulesTwo: {
					name: [{
						required: true,
						validator: checkTeacherName,
						trigger: 'blur'
					}],

				}
			}
		},
		methods: {
			randomNum(minNum, maxNum) {
				switch (arguments.length) {
					case 1:
						return parseInt(Math.random() * minNum + 1, 10);
						break;
					case 2:
						return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
						break;
					default:
						return 0;
						break;
				}
			},
			onSubmitStudent(registerStudentUser) {
				// 为表单绑定验证功能
				this.$refs[registerStudentUser].validate((valid) => {
					if (valid) {

						this.$confirm('确认注册该学生用户？', '提示', {
							confirmButtonText: '确定',
							cancelButtonText: '取消',
							type: 'warning'
						}).then(() => {
							// console.log("你好")
							// 开始获取个人信息
							let that = this
							axios.post("http://localhost:8080/api/studentInfos/insertStudentInfo", {
								student_id: this.registerStudentUser.student_id,
								name: this.registerStudentUser.name,
								professional: this.registerStudentUser.professional
							}).then(function(response) {

								Message.success(response.data)

							}).catch(function(error) {
								Message.error(error.response.data)
							})

						}).catch(() => {
							this.$message({
								type: 'error',
								message: '已取消修改'
							});
						});



					}
				});
			},
			onSubmitTeacher(registerTeacherUser) {
				// 为表单绑定验证功能
				this.$refs[registerTeacherUser].validate((valid) => {
					if (valid) {
						this.$confirm('确认注册该教师用户？', '提示', {
							confirmButtonText: '确定',
							cancelButtonText: '取消',
							type: 'warning'
						}).then(() => {
							// console.log("你好")
							// 开始获取个人信息
							let that = this
							axios.post("http://localhost:8080/api/teacherInfos/insertTeacherInfo", {
								teacher_id: this.registerTeacherUser.teacher_id,
								name: this.registerTeacherUser.name
							}).then(function(response) {
						
								Message.success(response.data)
					
							}).catch(function(error) {
								Message.error(error.response.data)
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
		}
	}
</script>

<style>
</style>

<template>
	<div style="margin-left: 30%;margin-top: 20px;">
		<el-form label-width="80px" :model="userMessageform" ref="userMessageform">
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
				<el-input v-model="userMessageform.sex" placeholder="性别">
					<i slot="prefix" class="iconfont iconshenfenzheng"></i>
				</el-input>
			</el-form-item>
			<!-- </div> -->
			<!-- <div style="float: right;margin-right: 15%;"> -->
			<el-form-item label="出生日期" prop="birthday">
				<el-date-picker type="date" placeholder="选择日期" v-model="userMessageform.birthday"></el-date-picker>
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
				<el-button type="primary" round style="width: 150px;" @click="onSubmit('userMessageform')" :plain="true">
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
	export default {
		name: 'AlterMessage',
		data() {
			return {
				userMessageform: {
					user: JSON.parse(this.$store.state.token),
					ID_Card: '',
					sex: '',
					birthday: '',
					phone: '',
					email: '',
					remark: ''
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
							// 开始登录验证
							let that = this
							axios.put("http://localhost:8080/api/studentInfos/updateByPrimaryKeyChangeMessage", {
								student_id: JSON.parse(this.$store.state.token).student_id,
								password:JSON.parse(this.$store.state.token).password,
								name: JSON.parse(this.$store.state.token).name,
								id_card: this.userMessageform.ID_Card,
								sex: this.userMessageform.sex,
								birthday:this.userMessageform.birthday,
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

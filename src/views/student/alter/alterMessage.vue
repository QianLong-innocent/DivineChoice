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
				<el-button type="primary" round style="width: 150px;" @click="onSubmit()" :plain="true">
					修改个人信息
				</el-button>
			</el-form-item>
			<!-- </div> -->
		</el-form>
	</div>
</template>

<script>
	import axios from 'axios';
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
			onSubmit() {
				console.log('submit!');
			}
		}
	}
</script>

<style scoped="scoped">
	.el-input {
		width: 200px;
	}
</style>

<template>
	<div style="margin-left: 30%;margin-top: 100px;">
		<el-form label-width="80px" :model="teacherProjectTable" ref="teacherProjectTable" :rules="rules">

			<el-form-item label="项目名" prop="project_name">
				<el-input v-model="teacherProjectTable.project_name" placeholder="项目名"
					style="width: 400px;">
					<i slot="prefix" class="iconfont iconxiangmu" style="margin-left: 4px;"></i>
				</el-input>
			</el-form-item>

			<el-form-item label="所属专业" prop="professional">
				<el-select placeholder="专业" style="width: 400px;" v-model="teacherProjectTable.professional">
					<i slot="prefix" class="iconfont iconzhuanye" style="margin-left: 4px;"></i>
					<el-option label="计科" value="计科"></el-option>
					<el-option label="软工" value="软工"></el-option>
				</el-select>
			</el-form-item>

			<el-form-item label="项目类型" prop="project_type">

				<el-select placeholder="项目类型" style="width: 400px;" v-model="teacherProjectTable.project_type">
					<!-- 获取获取所有题型 -->
					<i slot="prefix" class="iconfont iconleixing" style="margin-left: 4px;"></i>
					<el-option v-for="item in projectTypeArray" :label="item" :value="item" :key="item"></el-option>
				</el-select>

			</el-form-item>

			<el-form-item label="项目描述" prop="project_content">
				<el-input v-model="teacherProjectTable.project_content" type="textarea" placeholder="项目描述"
					style="width: 400px;" rows="5">
				</el-input>
			</el-form-item>

			<el-form-item>

				<el-button type="primary" round style="width: 100px;margin-left: 120px;"
					@click="onSubmit('teacherProjectTable')" :plain="true">
					确定添加
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
		name: 'AddProject',
		data() {
			return {
				projectTypeArray: '',
				teacherProjectTable: {
					project_name: '',
					project_content: '',
					project_type: '',
					professional: ''
				},
				// 为表单验证
				rules: {
					project_name: [{
						required: true,
						message: '请填写项目名',
						trigger: 'blur'
					}],
					project_content: [{
						required: true,
						message: '请填写项目描述',
						trigger: 'change'
					}],
					project_type: [{
						required: true,
						message: '请填写项目类型',
						trigger: 'change'
					}],
					professional: [{
						required: true,
						message: '请选择所属专业',
						trigger: 'change'
					}]
				}
			}
		},
		methods: {
			onSubmit(form) {
				
				// 为表单绑定验证功能
				this.$refs[form].validate((valid) => {

					if (valid) {

						let that = this
						axios.post("http://localhost:8080/api/teacherProjects/insertProject/" + this.teacherProjectTable.project_type, {
								name:JSON.parse(that.$store.state.token).name,
								project_name:this.teacherProjectTable.project_name,
								project_content:this.teacherProjectTable.project_content,
								professional:this.teacherProjectTable.professional
							})
							.then(function(response) {
								Message.success("添加成功")
							})
							.catch(function(error) {
								Message.error(error.response.data)
							})
						
					}
				});
			}
		},
		mounted() {
			let that = this
			axios.get("http://localhost:8080/api/projectTypes/selectProjectType")
				.then(function(response) {
					that.projectTypeArray = response.data
				})
				.catch(function(error) {
					Message.error("请检查网络")
				})
		}
	}
</script>

<style>
</style>

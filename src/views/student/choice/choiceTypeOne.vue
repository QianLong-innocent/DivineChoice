<template id="choiceTypeOne">
	<div style="background-color: #FFFFFF;">
		<!-- 上半部分 -->
		<div style="height: 60px;">
			<div style="padding: 12px;">
				&nbsp;
				老师姓名
				&emsp;
				<el-select placeholder="" v-model="teacherName">
					<!-- 获取获取所有老师 -->
					<el-option v-for="item in teacherNameArray" :label="item" :value="item" :key="item"></el-option>
				</el-select>
				&emsp;
				<el-button type="primary" @click="searchTeacherProject()">搜索</el-button>
			</div>
		</div>
		<!-- 下半部分 -->
		<div style="height: 570px;">
			<div style="padding: 12px;">
				<!-- 搜索按钮触发之后刷新tableData -->
				<el-table :data="tableData.slice((currentPage-1)*10,currentPage*10)" style="width: 100%" height="520px"
					stripe>
					<el-table-column label="#" :index="indexMethod" type="index" align="center"></el-table-column>
					<el-table-column prop="project_id" label="项目编号" align="center" />
					<el-table-column prop="project_name" label="项目名称" align="center" />
					<el-table-column label="操作" width="300" align="center"></el-table-column>
				</el-table>
				<el-pagination @current-change="handleCurrentChange" :current-page.sync="currentPage" :page-size="10"
					layout="total, prev, pager, next" :total='tableData.length' id="paging">
				</el-pagination>
			</div>
		</div>


	</div>
</template>

<script>
	import axios from 'axios';
	import {
		Message
	} from 'element-ui';
	export default {
		name: 'ChoiceTypeOne',
		data() {
			return {
				teacherName: '',
				teacherNameArray: '',
				tableData: [],
				currentPage: 1
			}
		},
		methods: {
			handleCurrentChange(val) {
				this.currentPage = val;
			},
			indexMethod(index) {
				return (this.currentPage - 1) * 10 + index + 1
			},
			searchTeacherProject() {
				let that = this
				axios.get("http://localhost:8080/api/teacherProjects/selectTeacherProject/" + that.teacherName, {
						params: {
							id: JSON.parse(that.$store.state.token).student_id,
						}
					})
					.then(function(response) {
						that.tableData = response.data
						Message.success("查询成功")
						document.getElementById('paging').style.display = 'block'
					})
					.catch(function(error) {
						console.log(error)
						Message.error("请检查网络")
					})
			}
		},
		mounted() {
			let that = this
			axios.get("http://localhost:8080/api/teacherProjects/selectTeacherName")
				.then(function(response) {
					
					that.teacherNameArray = response.data
				})
				.catch(function(error) {
					Message.error("请检查网络")
				})
		}
	}
</script>

<style>
	#paging {
		display: none;
		float: right;
	}
</style>

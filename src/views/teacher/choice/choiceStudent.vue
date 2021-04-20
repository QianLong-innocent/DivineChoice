<template>
	<div style="background-color: #FFFFFF;">
		<!-- 上半部分 -->
		<div style="height: 60px;">
			<div style="padding: 12px;">
				&nbsp;
				学生专业
				&emsp;
				<el-select placeholder="" v-model="professional">
					<el-option label="计科" value="selectAllTeacherProjectForComputerForChoice"></el-option>
					<el-option label="软工" value="selectAllTeacherProjectForSoftForChoice"></el-option>
				</el-select>
				&emsp;
				<el-button type="primary" @click="searchTeacherProject()">
					<i class="iconfont iconxingtaiduICON_sousuo---copy"></i>
					搜索
				</el-button>
			</div>
		</div>


		<!-- 下半部分 -->
		<div style="height: 570px;">
			<div style="padding: 12px;">

				<el-table :data="tableData.slice((currentPage-1)*10,currentPage*10)" border
					:span-method="objectSpanMethod" style="width: 100%" height="520px" v-loading="loading">
					<el-table-column prop="teacherProject.project_id" label="项目编号" width="80px" align="center">
					</el-table-column>
					<el-table-column prop="teacherProject.project_name" label="项目名" align="center" width="400px">
					</el-table-column>
					<el-table-column prop="studentInfo.name" label="学生姓名" align="center">
					</el-table-column>
					<el-table-column prop="volunteer" label="志愿" align="center">
					</el-table-column>
					<el-table-column prop="choice" label="状态" align="center">
					</el-table-column>
					<el-table-column label="操作" width="300" align="center">
						<template slot-scope="scope">
							<el-button type="primary" size="mini" @click="clickRowHandle(scope.$index, scope.row)">
								<i class="iconfont iconxiangqing"></i>
								查看详情
							</el-button>
							<el-button type="warning" size="mini" @click="handleChoice(scope.$index, scope.row)">
								<i class="iconfont iconbianji"></i>
								选择
							</el-button>
							<el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">
								<i class="iconfont iconshanchu"></i>
								退选
							</el-button>
						</template>
					</el-table-column>

				</el-table>

				<el-pagination @current-change="handleCurrentChange" :current-page.sync="currentPage" :page-size="10"
					layout="total, prev, pager, next" :total='tableData.length' id="paging">
				</el-pagination>
			</div>
		</div>

		<!-- 选题嵌套表单 -->
		<el-dialog title="查看" :visible.sync="dialogFormVisible" style="width: 1300px;margin-left: 10%;margin-top: 5%;">
			<el-form :model="studentInfo">
				<el-form-item label="电话">
					&emsp;
					<el-input v-model="studentInfo.phone" style="width: 500px;" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="个人介绍">
					<el-input v-model="studentInfo.remark" type="textarea" placeholder="学生个人介绍" style="width: 500px;"
						rows="5" :disabled="true">
					</el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancelEdit()">关 闭</el-button>
				<el-button type="primary" @click="confirmEdit()">确 定</el-button>
			</div>
		</el-dialog>


	</div>
</template>

<script>
	import axios from 'axios';
	import {
		Message
	} from 'element-ui';
	export default {
		name: "ChoiceStudent",
		data() {
			return {
				dialogFormVisible: false,
				loading: false,
				currentPage: 1,
				professional: '',
				tableData: [],
				studentInfo: {
					remark: '',
					phone: ''
				}
			}
		},
		methods: {
			handleDelete(index, row) {
				if (row.studentInfo === null) {
					Message.warning("该项目还未被选择")
					return;
				}
				this.$confirm('此操作将退选该学生, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {

					let that = this
					axios.put("http://localhost:8080/api/studentChoices/deleteChoiceProject", {
							id: row.studentInfo.student_id,
							volunteer: row.volunteer
						})
						.then(function(response) {
							// that.deleteF5()
							that.$message({
								message: '退选成功',
								type: 'success',
								duration: '1000',
								center: true
							});
							that.loading = true
							that.timer = setTimeout(() => { //设置延迟执行
								that.loading = false
								that.searchTeacherProject()
							}, 1000);


						})
						.catch(function(error) {
							Message.error(error.response.data)
						})



				}).catch(() => {
					this.$message({
						type: 'error',
						message: '已取消'
					});
				});
			},
			handleChoice(index, row) {
				if (row.studentInfo === null) {
					Message.warning("该项目还未被选择")
					return;
				}
				
				this.$confirm('此操作将锁定彼此, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let that = this
					axios.post("http://localhost:8080/api/teacherProjects/choiceStudent", {
							studentInfo:row.studentInfo,
							teacherProject:row.teacherProject,
							volunteer:row.volunteer,
							choice:row.choice
						})
						.then(function(response) {
							// that.deleteF5()
							that.$message({
								message: '选择成功',
								type: 'success',
								duration: '1000',
								center: true
							});
							that.loading = true
							that.timer = setTimeout(() => { //设置延迟执行
								that.loading = false
								that.searchTeacherProject()
							}, 1000);
					
					
						})
						.catch(function(error) {
							Message.error(error.response.data)
						})
				}).catch(() => {
					this.$message({
						type: 'error',
						message: '已取消'
					});
				});
			},
			cancelEdit() {
				this.dialogFormVisible = false
			},
			confirmEdit() {
				this.dialogFormVisible = false
			},
			clickRowHandle(index, row) {
				if (row.studentInfo === null) {
					Message.warning("该项目还未被选择")
				} else {
					this.studentInfo.remark = row.studentInfo.remark
					this.studentInfo.phone = row.studentInfo.phone
					this.dialogFormVisible = true
				}

			},
			handleCurrentChange(val) {
				this.currentPage = val;
			},
			searchTeacherProject() {
				let that = this
				axios.get("http://localhost:8080/api/teacherProjects/" + this.professional, {
						params: {
							teacherName: JSON.parse(that.$store.state.token).name
						}
					})
					.then(function(response) {
						that.tableData = response.data
						Message.success("查询成功")
						// console.log(that.tableData)
						document.getElementById('paging').style.display = 'block'
					})
					.catch(function(error) {
						// console.log(error)
						Message.error("请检查网络")
					})
			},
			objectSpanMethod({
				row,
				column,
				rowIndex,
				columnIndex
			}) {
				if (columnIndex === 0) {
					if (rowIndex % 2 === 0) {
						return {
							rowspan: 2,
							colspan: 1
						};
					} else {
						return {
							rowspan: 0,
							colspan: 0
						};
					}
				}
			}
		},
		mounted() {

		}
	}
</script>

<style>
</style>

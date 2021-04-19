<template>
	<div style="background-color: #FFFFFF;">

		<!-- 上半部分 -->
		<div style="height: 60px;">
			<div style="padding: 12px;">
				&nbsp;
				学生专业
				&emsp;
				<el-select placeholder="" v-model="professional">
					<el-option label="计科" value="selectAllTeacherProjectForComputer"></el-option>
					<el-option label="软工" value="selectAllTeacherProjectForSoft"></el-option>
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
				<el-table :data="tableData.slice((currentPage-1)*10,currentPage*10)" style="width: 100%" height="520px"
					ref="evtTable" v-loading="loading">
					<el-table-column label="#" :index="indexMethod" type="index" align="center"></el-table-column>
					<el-table-column prop="project_id" label="项目编号" align="center" />
					<el-table-column prop="project_name" label="项目名称" align="center" />
					<el-table-column label="操作" width="300" align="center">
						<template slot-scope="scope">
							<el-button type="primary" size="mini" @click="clickRowHandle(scope.$index, scope.row)">
								<i class="iconfont iconxiangqing"></i>
								查看详情
							</el-button>
							<el-button type="warning" size="mini" @click="handleEdit(scope.$index, scope.row)">
								<i class="iconfont iconbianji"></i>
								编辑
							</el-button>
							<el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">
								<i class="iconfont iconshanchu"></i>
								删除
							</el-button>

						</template>
					</el-table-column>
					<!-- 展开行 -->
					<el-table-column type="expand" width="1">
						<template slot-scope="props">
							&emsp;&emsp;{{props.row.project_content}}
						</template>
					</el-table-column>
				</el-table>

				<el-pagination @current-change="handleCurrentChange" :current-page.sync="currentPage" :page-size="10"
					layout="total, prev, pager, next" :total='tableData.length' id="paging">
				</el-pagination>
			</div>
		</div>


		<!-- 选题嵌套表单 -->
		<el-dialog title="编辑" :visible.sync="dialogFormVisible" style="width: 1300px;margin-left: 10%;margin-top: 5%;">
			<el-form :model="form">
				<el-form-item label="项目名">
					&emsp;
					<el-input v-model="form.project_newName" style="width: 500px;"></el-input>
				</el-form-item>
				<el-form-item label="项目描述">
					<el-input v-model="form.project_content" type="textarea" placeholder="项目描述" style="width: 500px;"
						rows="5">
					</el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancelEdit()">取 消</el-button>
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
		name: "MyProject",
		data() {
			return {
				professional: '',
				tableData: [],
				currentPage: 1,
				loading: false,
				dialogFormVisible: false,
				form: {
					volunteer: '',
					project_name: '',
					project_newName: '',
					project_content: ''
				}
			}
		},
		methods: {
			cancelEdit() {
				this.dialogFormVisible = false
				Message.error("取消编辑")
			},
			confirmEdit() {

				this.dialogFormVisible = false
				let that = this
				axios.put("http://localhost:8080/api/teacherProjects/updateProjectNameAndContent", {
						project_name: this.form.project_name,
						project_newName: this.form.project_newName,
						project_content: this.form.project_content
					})
					.then(function(response) {

						that.dialogFormVisible = false

						that.$message({
							message: '编辑成功',
							type:'success',
							duration:'1000',
							center: true
						});
						that.loading = true
						that.timer = setTimeout(() => { //设置延迟执行
							that.loading = false
							that.searchTeacherProject()
						}, 1000);

					})
					.catch(function(error) {

						that.dialogFormVisible = false

						Message.error(error.response.data)
					})

			},
			clickRowHandle(index, row) {
				this.$refs.evtTable.toggleRowExpansion(row);
			},
			handleEdit(index, row) {

				// console.log(row)
				this.dialogFormVisible = true
				this.form.project_name = row.project_name
				this.form.project_newName = row.project_name
				this.form.project_content = row.project_content
			},
			handleDelete(index, row) {

				this.$confirm('此操作也会删除被选学生, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {

					Message.success("删除成功")
					

				}).catch(() => {
					this.$message({
						type: 'error',
						message: '已取消'
					});
				});



			},
			handleCurrentChange(val) {
				this.currentPage = val;
			},
			indexMethod(index) {
				return (this.currentPage - 1) * 10 + index + 1
			},
			searchTeacherProject() {
				let that = this

				axios.get("http://localhost:8080/api/teacherProjects/" + this.professional)
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
		}
	}
</script>

<style>
	#paging {
		display: none;
		float: right;
	}
</style>

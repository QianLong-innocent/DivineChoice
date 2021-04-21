<template>
	<div style="background-color: #FFFFFF;">
		<!-- 上半部分 -->
		<div style="height: 60px;">
			<div style="padding: 12px;">
				&nbsp;
				教师姓名
				&emsp;
				<el-input placeholder="教师姓名" v-model="teacherName" style="width: 200px;">
				</el-input>
				&emsp;
				<el-button type="primary" @click="searchTeacher()">
					<i class="iconfont iconxingtaiduICON_sousuo---copy"></i>
					搜索
				</el-button>
				<el-button type="primary" @click="exportExcel('教师表')">
					<i class="iconfont iconxingtaiduICON_sousuo---copy"></i>
					导出表格
				</el-button>
			</div>
		</div>


		<!-- 下半部分 -->
		<div style="height: 570px;">
			<div style="padding: 12px;">

				<el-table :data="tableData.slice((currentPage-1)*10,currentPage*10)" style="width: 100%" height="520px"
					v-loading="loading" ref="evtTable">
					<el-table-column label="#" :index="indexMethod" type="index" align="center"></el-table-column>
					<el-table-column prop="teacher_id" label="教师工号" align="center">
					</el-table-column>
					<el-table-column prop="name" label="教师姓名" align="center">
					</el-table-column>
					<el-table-column prop="password" label="教师密码" align="center">
					</el-table-column>
					<el-table-column label="操作" width="300" align="center">
						<template slot-scope="scope">
							<el-button type="primary" size="mini" @click="clickRowHandle(scope.$index, scope.row)">
								<i class="iconfont iconxiangqing"></i>
								查看详情
							</el-button>
							<el-button type="warning" size="mini" @click="handleEdit(scope.$index, scope.row)">
								<i class="iconfont iconxuanze"></i>
								编辑
							</el-button>
							<el-button type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">
								<i class="iconfont iconxuanze"></i>
								删除
							</el-button>
						</template>
					</el-table-column>

					<!-- 展开行 -->
					<el-table-column type="expand" width="1">
						<template slot-scope="props">
							<el-form label-position="left" inline class="demo-table-expand">
								<el-form-item label="身份证号">
									<span>{{ props.row.id_card }}</span>
								</el-form-item>
								<br />
								<el-form-item label="性别">
									<span>{{ props.row.sex }}</span>
								</el-form-item>
								<br />
								<el-form-item label="生日">
									<span>{{ props.row.birthday }}</span>
								</el-form-item>
								<br />
								<el-form-item label="手机号">
									<span>{{ props.row.phone }}</span>
								</el-form-item>
								<br />
								<el-form-item label="邮箱">
									<span>{{ props.row.email }}</span>
								</el-form-item>
								<br />
								<el-form-item label="自我介绍">
									<span>{{ props.row.remark }}</span>
								</el-form-item>
							</el-form>
						</template>
					</el-table-column>


				</el-table>

				<el-pagination @current-change="handleCurrentChange" :current-page.sync="currentPage" :page-size="10"
					layout="total, prev, pager, next" :total='tableData.length' id="paging">
				</el-pagination>
			</div>
		</div>
		
		
		<!-- 选题嵌套表单 -->
		<el-dialog title="选题" :visible.sync="dialogFormVisible" style="width: 600px;margin-left: 30%;margin-top: 5%;">
			<el-form :model="form">
				<el-form-item label="姓名">
					<el-input v-model="form.teacherName" placeholder="学生姓名" style="width: 200px;">
					</el-input>
				</el-form-item>
				<el-form-item label="密码">
					<el-input v-model="form.password" placeholder="学生密码" style="width: 200px;">
					</el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancelChoice()">取 消</el-button>
				<el-button type="primary" @click="confirmChoice()">确 定</el-button>
			</div>
		</el-dialog>
		
		
	</div>
</template>

<script>
	import FileSaver from 'file-saver'
	import XLSX from 'xlsx'
	import axios from 'axios';
	import {
		Message
	} from 'element-ui';
	export default {
		name: "MessageTea",
		data() {
			return {
				dialogFormVisible: false,
				loading: false,
				teacherName: '',
				tableData: [],
				currentPage: 1,
				form: {
					teacherID:'',
					teacherName: '',
					password: ''
				}
			}
		},
		methods: {
			confirmChoice(){
				this.dialogFormVisible = false
				this.$confirm('此操作将修改教师基本信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					// console.log(this.form)
					let that = this
					axios.post("http://localhost:8080/api/teacherInfos/updateTeacherInfo", {
							teacher_id:this.form.teacherID,
							password:this.form.password,
							name:this.form.teacherName
						})
						.then(function(response) {
							// console.log(that.form)
							that.dialogFormVisible = false
					
							that.$message({
								message: '编辑成功',
								type: 'success',
								duration: '1000',
								center: true
							});
							that.loading = true
							that.timer = setTimeout(() => { //设置延迟执行
								that.loading = false
								that.init()
							}, 1000);
					
						})
						.catch(function(error) {
					
							that.dialogFormVisible = false
					
							Message.error(error.response.data)
						})
				}).catch(() => {
					this.$message({
						type: 'error',
						message: '已取消'
					});
				});
				
			},
			cancelChoice() {
				this.dialogFormVisible = false
				Message.error("已取消")
			},
			clickRowHandle(index, row) {
				this.$refs.evtTable.toggleRowExpansion(row);
			},
			handleEdit(index, row) {
				this.form.teacherID = row.teacher_id
				this.form.teacherName = row.name
				this.form.password = row.password
				this.dialogFormVisible = true
			},
			handleDelete(index, row) {
				Message.success("删除成功")
			},
			handleCurrentChange(val) {
				this.currentPage = val;
			},
			indexMethod(index) {
				return (this.currentPage - 1) * 10 + index + 1
			},
			searchTeacher() {

				let that = this
				axios.get("http://localhost:8080/api/teacherInfos/selectTeacherByName", {
						params: {
							teacherName: this.teacherName
						}
					})
					.then(function(response) {

						that.$message({
							message: '查询成功',
							type: 'success',
							duration: '1000',
							center: true
						});
						that.loading = true
						that.timer = setTimeout(() => { //设置延迟执行
							that.loading = false
							that.tableData = response.data
						}, 1000);
						document.getElementById('paging').style.display = 'block'
					})
					.catch(function(error) {
						// console.log(error)
						Message.error("请检查网络")
					})
			},
			exportExcel(excelName) {
				//excelName --设置导出的excel名称
				//report-table --对应的要导出的el-table的ref名称
				try {
					const $e = this.$refs['evtTable'].$el;
					// 如果表格加了fixed属性，则导出的文件会生产两份一样的数据，所以可在这里判断一下
					let $table = $e.querySelector('.el-table__fixed');
					if (!$table) {
						$table = $e;
					}
					// 为了返回单元格原始字符串，设置{ raw: true }
					const wb = XLSX.utils.table_to_book($table, {
						raw: true
					});
					const wbout = XLSX.write(wb, {
						bookType: 'xlsx',
						bookSST: true,
						type: 'array'
					});
					FileSaver.saveAs(
						new Blob([wbout], {
							type: 'application/octet-stream'
						}),
						`${excelName}.xlsx`,
					);
				} catch (e) {
					if (typeof console !== 'undefined') console.error(e);
				}
			},
			init(){
				let that = this
				axios.get("http://localhost:8080/api/teacherInfos/selectAllTeacher")
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
			}
		},
		mounted() {
			this.init()
		}
	}
</script>

<style>
</style>

<template id="choiceTypeOne">
	<div style="background-color: #FFFFFF;">
		<!-- 上半部分 -->
		<div style="height: 60px;">
			<div style="padding: 12px;">
				&nbsp;
				题型名称
				&emsp;
				<el-select placeholder="" v-model="projectType">
					<!-- 获取获取所有题型 -->
					<el-option v-for="item in projectTypeArray" :label="item" :value="item" :key="item"></el-option>
				</el-select>
				&emsp;
				<el-button type="primary" @click="searchProjectType()">
					<i class="iconfont iconxingtaiduICON_sousuo---copy"></i>
					搜索
				</el-button>
			</div>
		</div>
		<!-- 下半部分 -->
		<div style="height: 570px;">
			<div style="padding: 12px;">
				<!-- 搜索按钮触发之后刷新tableData -->
				<el-table :data="tableData.slice((currentPage-1)*10,currentPage*10)" style="width: 100%" height="520px"
					stripe ref="evtTable" v-loading="loading">
					<el-table-column label="#" :index="indexMethod" type="index" align="center"></el-table-column>
					<el-table-column prop="id" label="项目编号" align="center" />
					<el-table-column prop="project_name" label="项目名称" align="center" />
					<el-table-column label="操作" width="300" align="center">
						<template slot-scope="scope">
							<el-button type="primary" size="mini" @click="clickRowHandle(scope.$index, scope.row)">
								<i class="iconfont iconxiangqing"></i>
								查看详情
							</el-button>
							<el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row)">
								<i class="iconfont iconxuanze"></i>
								选题
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
		<el-dialog title="选题" :visible.sync="dialogFormVisible" style="width: 600px;margin-left: 30%;margin-top: 5%;">
			<el-form :model="form">
				<el-form-item label="志愿">
					<el-select v-model="form.volunteer" placeholder="请选择志愿" style="width: 200px;">
						<el-option label="第一志愿" value="1"></el-option>
						<el-option label="第二志愿" value="2"></el-option>
					</el-select>
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
	import axios from 'axios';
	import {
		Message
	} from 'element-ui';
	export default {
		name: 'ChoiceTypeOne',
		data() {
			return {
				projectType: '',
				projectTypeArray: '',
				tableData: [],
				currentPage: 1,
				loading:false,
				dialogFormVisible: false,
				form: {
					volunteer: '',
					project_name: ''
				}
			}
		},
		methods: {
			cancelChoice() {
				this.dialogFormVisible = false
				Message.error("取消选题")
			},
			confirmChoice() {

				let that = this
				axios.put("http://localhost:8080/api/studentChoices/updateChoiceProject", {
						student_id: JSON.parse(that.$store.state.token).student_id,
						name: that.form.project_name,
						volunteer: that.form.volunteer
					})
					.then(function(response) {

						that.dialogFormVisible = false
						// console.log(this.form.project_id)
						that.$message({
							message: '选题成功',
							type:'success',
							duration:'1000',
							center: true
						});
						that.loading = true
						that.timer = setTimeout(() => { //设置延迟执行
							that.loading = false
							that.searchProjectType()
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

				// 跳二级标题。
				this.dialogFormVisible = true
				this.form.project_name = row.project_name
				// console.log(index, row);

			},
			handleCurrentChange(val) {
				this.currentPage = val;
			},
			indexMethod(index) {
				return (this.currentPage - 1) * 10 + index + 1
			},
			searchProjectType() {
				let that = this
				axios.get("http://localhost:8080/api/projectTypes/selectProjectType/" + that.projectType, {
						params: {
							id: JSON.parse(that.$store.state.token).student_id,
						}
					})
					.then(function(response) {
						if (response.data.length === 0) {
							Message.warning("老师们，正在加班加点添加该类型")
						} else {
							Message.success("查询成功")
							that.tableData = response.data
							// console.log(that.tableData)
							document.getElementById('paging').style.display = 'block'
						}
					})
					.catch(function(error) {
						console.log(error)
						Message.error("请检查网络")
					})
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
	#paging {
		display: none;
		float: right;
	}
</style>

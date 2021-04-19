<template>
	<div style="background-color: #FFFFFF;">

		<!-- 上半部分 -->
		<div style="height: 60px;">
			<div style="padding: 12px;float: right;margin-right: 20px;">
				<el-button type="primary" @click="dialogFormVisible = true">
					<i class="iconfont icon-tainjia"></i>
					添加项目类型</el-button>
			</div>
		</div>


		<!-- 下半部分 -->
		<div style="height: 570px;">
			<div style="padding: 12px;">
				<el-table :data="tableData.slice((currentPage-1)*10,currentPage*10)" style="width: 100%" height="520px">
					<el-table-column label="#" :index="indexMethod" type="index" align="center"></el-table-column>
					<el-table-column prop="id" label="项目类型编号" align="center" />
					<el-table-column prop="project_type" label="项目类型" align="center" />
				</el-table>

				<el-pagination @current-change="handleCurrentChange" :current-page.sync="currentPage" :page-size="10"
					layout="total, prev, pager, next" :total='tableData.length' style="float: right;">
				</el-pagination>
			</div>
		</div>

		<!-- 添加项目类型嵌套表单 -->
		<el-dialog title="添加" :visible.sync="dialogFormVisible" style="width: 700px;margin-left: 30%;margin-top: 5%;">
			<el-form :model="form">
				<el-form-item label="项目类型">
					<el-input v-model="form.project_type" style="width: 200px;">

					</el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="cancelAdd()">取 消</el-button>
				<el-button type="primary" @click="confirmAdd()">确 定</el-button>
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
		name: "AddProjectType",
		data() {
			return {
				tableData: [],
				currentPage: 1,
				dialogFormVisible: false,

				form: {
					project_type: ''
				}
			}
		},
		methods: {
			cancelAdd() {
				this.dialogFormVisible = false
				Message.error("已取消")
			},
			confirmAdd() {
				this.dialogFormVisible = false
				this.connectMySql()

			},
			connectMySql() {
				let that = this
				axios.post("http://localhost:8080/api/projectTypes/insertProjectType", {
						project_type: this.form.project_type
					})
					.then(function(response) {

						that.dialogFormVisible = false
						if (response.data === true) {
							that.$_update()
							Message.success("选题成功")

						} else {
							Message.warning("类型重复")
						}


					})
					.catch(function(error) {

						that.dialogFormVisible = false

						Message.error(error.response.data)
					})

			},
			handleCurrentChange(val) {
				this.currentPage = val;
			},
			indexMethod(index) {
				return (this.currentPage - 1) * 10 + index + 1
			},
			$_update() {
				let that = this
				axios.get("http://localhost:8080/api/projectTypes/selectAllProjectType")
					.then(function(response) {
						that.tableData = response.data
					})
					.catch(function(error) {
						Message.error("请检查网络")
					})
			}
		},
		mounted() {
			this.$_update()
		}
	}
</script>

<style>

</style>

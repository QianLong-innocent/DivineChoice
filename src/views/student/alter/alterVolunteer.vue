<template>
	<div style="background-color: #FFFFFF;">
		<div style="height: 650px;">
			<div style="padding: 12px;">
				<el-table :data="tablesData" ref="evtTable" style="width: 100%" height="520px" stripe>
					<el-table-column label="#" prop="index" align="center"></el-table-column>
					<el-table-column prop="project_id" label="项目编号" align="center"></el-table-column>
					<el-table-column width="400" prop="project_name" label="项目名称" align="center"></el-table-column>
					<el-table-column label="志愿详情" width="300" prop="volunteer" align="center"></el-table-column>
					<el-table-column label="状态" prop="state" align="center"></el-table-column>
					<el-table-column label="操作" width="300" align="center">
						<template slot-scope="scope">
							<el-button type="primary" size="mini" @click="clickRowHandle(scope.$index, scope.row)">
								<i class="iconfont iconxiangqing"></i>
								查看详情
							</el-button>
							<el-button type="primary" size="mini" @click="handleEdit(scope.$index, scope.row)">
								<i class="iconfont iconxuanze"></i>
								退选
							</el-button>
						</template>
					</el-table-column>
					<!-- 展开行 -->
					<el-table-column type="expand" width="1">
						<template slot-scope="props">
							<el-form label-position="left" inline class="demo-table-expand">
								<el-form-item label="老师姓名">
									<span>{{ props.row.teacherName }}</span>
								</el-form-item>
								<br />
								<el-form-item label="老师手机号">
									<span>{{ props.row.phone }}</span>
								</el-form-item>
								<br />
								<el-form-item label="项目内容">
									<span>{{ props.row.project_content }}</span>
								</el-form-item>
							</el-form>
						</template>
					</el-table-column>
				</el-table>
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
		name: 'AlterVolunteer',
		data() {
			return {
				tablesData: []
			}
		},
		methods: {
			deleteF5() {
				let that = this
				axios.get("http://localhost:8080/api/studentChoices/selectChoiceStateAndTeacherInfo", {
						params: {
							id: JSON.parse(that.$store.state.token).student_id
						}
					})
					.then(function(response) {
						// console.log(response.data.length)
						if (response.data.length === 0) {
							Message.success("还未选题")

						} else {
							that.tablesData = response.data

							if (that.tablesData.length === 1) {
								that.tablesData[0].index = 1
							}
							// console.log(that.tablesData)
							Message.success("刷新成功")
						}

					})
					.catch(function(error) {
						Message.error("请检查网络")
					})
			},
			clickRowHandle(index, row) {
				this.$refs.evtTable.toggleRowExpansion(row);
			},
			handleEdit(index, row) {
				this.$confirm('此操作不可逆, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {


					let that = this
					axios.put("http://localhost:8080/api/studentChoices/deleteChoiceProject", {
							id: JSON.parse(that.$store.state.token).student_id,
							volunteer: row.volunteer
						})
						.then(function(response) {
							// that.deleteF5()
							Message.success("退选成功")

							that.timer = setTimeout(() => { //设置延迟执行
								that.$router.go(0)
							}, 1000);

							

						})
						.catch(function(error) {
							Message.error("网络繁忙")
						})

				}).catch(() => {
					this.$message({
						type: 'error',
						message: '已取消'
					});
				});
			}
		},
		mounted() {
			let that = this
			axios.get("http://localhost:8080/api/studentChoices/selectChoiceStateAndTeacherInfo", {
					params: {
						id: JSON.parse(that.$store.state.token).student_id
					}
				})
				.then(function(response) {
					// console.log(response.data.length)
					if (response.data.length === 0) {
						Message.success("还未选题")

					} else {
						that.tablesData = response.data

						if (that.tablesData.length === 1) {
							that.tablesData[0].index = 1
						}
						// console.log(that.tablesData)
						Message.success("查询成功")
					}

				})
				.catch(function(error) {
					Message.error("请检查网络")
				})
		}
	}
</script>

<style>
</style>

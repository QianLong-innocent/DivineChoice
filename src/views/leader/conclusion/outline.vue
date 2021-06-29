<template>
	<div>
		<div id="pieReport" style="width: 1000px;height: 500px;margin-left: 10%;margin-top: 50px;"></div>
		
		<!-- <div id="pieReportV1.0" style="width: 400px;height: 300px;float: right;"></div> -->
	</div>
</template>

<script>
	import axios from 'axios';
	import {
		Message
	} from 'element-ui';
	var echarts = require('echarts');
	export default {
		name: "Outline",
		data() {
			return {
				charts: "",
				opinion: ["未达成人数", "达成人数", "未选题人数"],
				opinionData: [{
					value: '',
					name: "未达成人数",
				}, {
					value: '',
					name: "达成人数",
				}, {
					value: '',
					name: "未选题人数",
				}]
			}
		},
		methods: {
			getChoiceData() {
				let that = this
				axios.get("http://localhost:8080/api/studentProjects/selectAllStudentProject")
					.then(function(response) {
						
						var data = response.data
						that.opinionData[0].value = data[0]
						that.opinionData[1].value = data[1]
						that.opinionData[2].value = data[2]
						that.drawPie("pieReport");
						// that.drawPie("pieReportV1.0")
					}).catch(function(error) {
						Message.error("请检查网络")
					})
			},
			drawPie(id) {
				this.charts = echarts.init(document.getElementById(id));
				this.charts.setOption({
					tooltip: {
						trigger: "item",
						formatter: "{a}<br/>{b}:{c} ({d}%)"
					},
					legend: {
						bottom: 0,
						left: "center",
						data: this.opinion
					},
					series: [{
						name: "状态",
						type: "pie",
						radius: "90%",
						center: ["50%", "50%"],
						avoidLabelOverlap: false,
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: "rgba(0, 0, 0, 0.5)"
							},
							color: function(params) {
								//自定义颜色
								var colorList = ["#1ab394", "#79d2c0", "#79dfff"];
								return colorList[params.dataIndex];
							}
						},
						data: this.opinionData
					}]
				});
			}

		},
		mounted() {
			this.getChoiceData()
		}

	}
</script>

<style>
</style>

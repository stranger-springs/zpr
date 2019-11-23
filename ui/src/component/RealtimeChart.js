import React, { Component } from 'react';
import CanvasJSReact from '../lib/canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;
var CanvasJS = CanvasJSReact.CanvasJS;

var dataPoints = [];
var updateInterval = 5000;
class RealtimeChart extends Component {

	state = {
		points: []
	};

	componentDidMount() {
			this.fetchData();
			setInterval(this.updateChart, updateInterval);
	}

	updateChart = () => {
		var chart = this.chart;
		fetch('http://localhost:8081/bitcoin/last')
			.then(res => res.json())
			.then(data => {
					var dateStr = data.timestamp;
					var date = new Date(dateStr);
					dataPoints.push({
						x: date,
						y: data.price
					});
				dataPoints.splice(0,1);
				if(dataPoints.length > 150){
					dataPoints.splice(0, dataPoints.length - 150);
				}
				chart.render();
			});
	}

	fetchData = () => {
		var chart = this.chart;
		fetch('http://localhost:8081/bitcoin')
			.then(res => res.json())
			.then(data => {
				for( var i = 0 ; i < data.length; ++i){
					var dateStr = data[i].timestamp;
					var date = new Date(dateStr);
					dataPoints.push({
						x: date,
						y: data[i].price
					});
				}
				if(dataPoints.length > 150){
					dataPoints.splice(0, dataPoints.length - 150);
				}
				chart.render();
			});

	}
	render() {
		const options = {
			theme: "light2",
			animationEnabled: true,
			exportEnabled: true,
			zoomEnabled: true,
			title:{
				text: "Bitcoin Price"
			},
			axisY:{
				prefix: "$",
				includeZero: false
			},
			axisX: {
				title: "chart updates every 5 secs"
			},
			data: [{
				type: "line",
				xValueFormatString: "DD MMM",
				markerSize: 5,
				dataPoints: dataPoints
			}]
		}
		return (
			<div>
				<CanvasJSChart options = {options}
							   onRef={ref => this.chart = ref}
				/>
			</div>
		);
	}
}
export default RealtimeChart;
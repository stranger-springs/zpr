import React from 'react';
import CanvasJSReact from '../lib/canvasjs.react';

import _ from 'lodash'

var CanvasJSChart = CanvasJSReact.CanvasJSChart;
var CanvasJS = CanvasJSReact.CanvasJS;
var updateInterval = 5000;

class RealtimeChart extends React.Component {
	
	constructor(props) {
		super(props);
		this.state = {
			points: []
		}
	}

	componentDidMount() {
		this.fetchData();
		setInterval(this.updateChart, updateInterval);
	}

	updateState = (points) => {
		this.setState({
			points: points
		})
	}

	updateChart = () => {
		fetch('http://localhost:8081/bitcoin/last')
			.then(res => res.json())
			.then(currency => {
				var points = this.state.points;
				points.splice(0 , 1);
				points.push(this.getMappedPoint(currency));

				this.updateState(points);

				this.chart.render();
			});
	}

	fetchData = () => {
		fetch('http://localhost:8081/bitcoin')
			.then(res => res.json())
			.then(currencies => {
				const tempPoints = currencies.length > 150 ? currencies.slice(currencies.length - 150, currencies.length) : currencies;

				this.updateState(_.map(tempPoints, this.getMappedPoint));

				this.chart.render();
			});

	}

	getMappedPoint = (item) => {
		return { x: new Date(item.timestamp), y: item.price}
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
				dataPoints: this.state.points
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
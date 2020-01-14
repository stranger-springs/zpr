import React from "react"
import CanvasJSReact from "../../lib/canvasjs.react"
import _ from "lodash"

var CanvasJSChart = CanvasJSReact.CanvasJSChart
const updateInterval = 5000
const maxPoints = 150

class RealtimeChart extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      points: []
    }
  }

  componentDidMount() {
    this.mounted = true // cannot update the state when an async task is terminated
    this.fetchData()
    setInterval(this.updateChart, updateInterval)
  }

  componentWillUnmount() {
    this.mounted = false
  }

  updateState = (points) => {
    this.setState({
      points: points
    })
  }

  updateChart = () => {
    fetch(this.props.apiLast)
        .then(res => res.json())
        .then(currency => {
          if (this.mounted) {
            var points = this.state.points

            if (points.length >= maxPoints) {
              points.splice(0, 1)
            }
            points.push(this.getMappedPoint(currency))
            this.updateState(points)
            this.chart.render()
          }
        })
  }

  fetchData = () => {
    fetch(this.props.api)
        .then(res => res.json())
        .then(currencies => {
          const tempPoints = currencies.length > maxPoints ? currencies.slice(currencies.length - maxPoints, currencies.length) : currencies
          this.updateState(_.map(tempPoints, this.getMappedPoint))
          this.chart.render()
        })
  }

  getMappedPoint = (item) => {
    return {x: new Date(item.timestamp), y: this.props.getValue(item)}
  }

  render() {
    const options = {
      theme: "light2",
      animationEnabled: true,
      exportEnabled: true,
      zoomEnabled: true,
      title: {
        text: this.props.title
      },
      axisY: {
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
        dataPoints: this.state.points,
        toolTipContent: "Date: {x} <br/> Value: {y}"
      }]
    }
    return (
        <div className="chart">
          <CanvasJSChart options={options} onRef={ref => this.chart = ref}/>
        </div>
    )
  }
}

export default RealtimeChart

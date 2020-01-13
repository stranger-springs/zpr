import React from "react"
import CanvasJSReact from "../../lib/canvasjs.react"
import _ from "lodash"

var CanvasJSChart = CanvasJSReact.CanvasJSChart

class Chart extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      points: []
    }
  }

  componentDidMount() {
    this.fetchData()
  }

  fetchData = () => {
    fetch(this.props.api)
        .then(res => res.json())
        .then(data => {
          this.updateState(_.map(data, this.getMappedPoint))
        })
    console.log(this.state.points)
    this.chart.render()
  }

  getMappedPoint = (item) => {
    return {x: new Date(item.timestamp), y: item.value}
  }

  updateState = (points) => {
    this.setState({points: points})
  }

  render() {
    const {currency, aggregationType} = this.props
    const options = {
      theme: "light2",
      animationEnabled: true,
      exportEnabled: true,
      zoomEnabled: true,
      title: {
        text: "Historic Chart for " + currency + " with " + aggregationType + " aggregation"
      },
      axisY: {
        prefix: "$",
        includeZero: false
      },
      data: [{
        type: "line",
        xValueFormatString: "DD MMM",
        markerSize: 5,
        dataPoints: this.state.points
      }]
    }
    return (
        <div className="chart">
          <CanvasJSChart options={options} onRef={ref => this.chart = ref}/>
        </div>
    )
  }
}

export default Chart

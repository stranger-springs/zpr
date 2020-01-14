import React from "react"
import CanvasJSReact from "../../lib/canvasjs.react"

var CanvasJSChart = CanvasJSReact.CanvasJSChart

class HistoricChart extends React.Component {

  render() {
    const {currency, aggregationType, points} = this.props
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
        type: "candlestick",
        xValueFormatString: "DD MMM YY HH:mm",
        markerSize: 5,
        dataPoints: points,
        toolTipContent: "Date: {x} <br/> Average: {y[0]}$ <br/> Max: {y[1]}$<br/> Min: {y[2]}$"
      }]
    }
    return (
        <div className="chart">
          <CanvasJSChart options={options} onRef={ref => this.chart = ref}/>
        </div>
    )
  }
}

export default HistoricChart

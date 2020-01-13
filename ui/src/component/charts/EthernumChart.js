import React from "react"
import RealtimeChart from "./RealtimeChart"


class EthernumChart extends React.Component {

  render() {
    const {api, apiLast, title} = this.props
    return (
        <RealtimeChart
            api={api}
            apiLast={apiLast}
            title={title}
        />
    )
  }
}

export default EthernumChart

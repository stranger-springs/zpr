import React from "react"
import Select from "react-select"
import _ from "lodash"
import Header from "../Header"
import NavigationBar from "../NavigationBar"
import {options} from "../CurrencyOptions"
import RealtimeChart from "../charts/RealtimeChart";


class CurrencyPage extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      selectedOption: _.head(options)
    }
    this.chart = React.createRef()
  }

  handleChange = (opt) => {
    this.setState({selectedOption: opt}, () => this.chart.current.fetchData())
  }

  shouldComponentUpdate(nextProps, nextState, nextContext) {
    return !_.isEqual(this.state.selectedOption, nextState.selectedOption)
  }

  getChart = (name, getValue) => ({
    "bitcoin": <RealtimeChart api={"http://localhost:8080/currency/bitcoin"}
                              apiLast={"http://localhost:8080/currency/bitcoin/last"}
                              title={"Bitcoin Price"}
                              ref={this.chart}
                              getValue={getValue}/>,
    "ethernum": <RealtimeChart api={"http://localhost:8080/currency/ethernum"}
                               apiLast={"http://localhost:8080/currency/ethernum/last"}
                               title={"Ethernum Price"}
                               ref={this.chart}
                               getValue={getValue}/>,
    "litecoin": <RealtimeChart api={"http://localhost:8080/currency/litecoin"}
                               apiLast={"http://localhost:8080/currency/litecoin/last"}
                               title={"Litecoin Price"}
                               ref={this.chart}
                               getValue={getValue}/>,
    "zcash": <RealtimeChart api={"http://localhost:8080/currency/zcash"}
                            apiLast={"http://localhost:8080/currency/zcash/last"}
                            title={"ZCash Price"}
                            ref={this.chart}
                            getValue={getValue}/>
  })[name]

  render() {
    return (
        <div className="app">
          <Header/>
          <NavigationBar/>
          <Select
              className="select"
              isSearchable="true"
              placeholder="Select cryptocurrency"
              options={options}
              value={this.state.selectedOption}
              onChange={this.handleChange}
          />
          {this.getChart(this.state.selectedOption.value, (dto) => _.get(dto, "price"))}
        </div>
    )
  }
}

export default CurrencyPage


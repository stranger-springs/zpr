import React from "react"
import Select from "react-select"
import _ from "lodash"
import Header from "./Header"
import EthernumChart from "./charts/EthernumChart"
import BitcoinChart from "./charts/BitcoinChart"
import NavigationBar from "./NavigationBar"
import LitecoinChart from "./charts/LitecoinChart"
import ZCashChart from "./charts/ZCashChart"
import {options} from "./CurrencyOptions"


class App extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      selectedOption: _.head(options)
    }
  }

  handleChange = (opt) => {
    this.setState({selectedOption: opt})
  }

  shouldComponentUpdate(nextProps, nextState, nextContext) {
    return !_.isEqual(this.state.selectedOption, nextState.selectedOption)
  }

  getChart = (name) => ({
    "bitcoin": <BitcoinChart api={"http://localhost:8080/currency/bitcoin"}
                             apiLast={"http://localhost:8080/currency/bitcoin/last"}
                             title={"Bitcoin Price"}/>,
    "ethernum": <EthernumChart api={"http://localhost:8080/currency/ethernum"}
                               apiLast={"http://localhost:8080/currency/ethernum/last"}
                               title={"Ethernum Price"}/>,
    "litecoin": <LitecoinChart api={"http://localhost:8080/currency/ethernum"}
                               apiLast={"http://localhost:8080/currency/ethernum/last"}
                               title={"Litecoin Price"}/>,
    "zcash": <ZCashChart api={"http://localhost:8080/currency/ethernum"}
                         apiLast={"http://localhost:8080/currency/ethernum/last"}
                         title={"ZCash Price"}/>
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
          {this.getChart(this.state.selectedOption.value)}
        </div>
    )
  }
}

export default App


import React from "react"
import NavigationBar from "../NavigationBar"
import Header from "../Header"
import _ from "lodash"
import Select from "react-select"
import {options} from "../CurrencyOptions"
import RealtimeChart from "../charts/RealtimeChart";

const optionsIndices = [
  {value: "ema", label: "Exponential Moving Average (EMA)"},
  {value: "sma", label: "Simple Moving Average (SMA)"},
  {value: "rsi", label: "Relative Strength Index (RSI)"}
]

const optionsRange = [
  {value: "5", label: "5"},
  {value: "9", label: "9"},
  {value: "14", label: "14"}
]

class IndicesPage extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      selectedOption: _.head(options),
      selectedIndex: _.head(optionsIndices),
      selectedRange: _.head(optionsRange),
      apiLink: ""
    }
    this.chart = React.createRef()
  }

  shouldComponentUpdate(nextProps, nextState, nextContext) {
    return (!_.isEqual(this.state.selectedOption, nextState.selectedOption)) ||
        (!_.isEqual(this.state.selectedIndex, nextState.selectedIndex)) ||
        (!_.isEqual(this.state.selectedRange, nextState.selectedRange))
  }

  handleChange = (opt) => {
    this.setState({selectedOption: opt}, () => this.chart.current.fetchData())
  }

  handleChangeIndices = (opt) => {
    this.setState({selectedIndex: opt}, () => this.chart.current.fetchData())
  }

  handleChangeRange = (opt) => {
    this.setState({selectedRange: opt}, () => this.chart.current.fetchData())
  }

  render() {
    const {selectedOption, selectedIndex, selectedRange} = this.state
    const api = "http://localhost:8080/index/" + selectedOption.value + "/" + selectedIndex.value + selectedRange.label
    const apiLast = api + "/last"
    const title = selectedIndex.label + " for " + selectedOption.value + " with range " + selectedRange.label

    return (
        <div className="app">
          <Header/>
          <NavigationBar/>
          <div className="select-indices-block">
            <Select
                className="select-indices"
                isSearchable="true"
                placeholder="Select cryptocurrency"
                options={options}
                value={selectedOption}
                onChange={this.handleChange}
            />
            <Select
                className="select-indices"
                defaultValue={optionsIndices[0]}
                isSearchable="true"
                placeholder="Select type of index"
                options={optionsIndices}
                value={selectedIndex}
                onChange={this.handleChangeIndices}
            />
            <Select
                className="select-indices"
                isSearchable="true"
                placeholder="Select range"
                options={optionsRange}
                value={selectedRange}
                onChange={this.handleChangeRange}
            />
          </div>
          <RealtimeChart
              api={api}
              ref={this.chart}
              apiLast={apiLast}
              title={title}
              getValue={(dto) => _.get(dto, "value")}
          />
        </div>
    )
  }
}

export default IndicesPage

import React from "react"
import NavigationBar from "../NavigationBar"
import Header from "../Header"
import _ from "lodash"
import Select from "react-select"
import TimestampPicker from "../TimestampPicker"
import HistoricChart from "../charts/HistoricChart"
import {options} from "../CurrencyOptions"

const optionsAggregation = [
  {value: "minute", label: "Minute"},
  {value: "hourly", label: "Hourly"},
  {value: "daily", label: "Daily"},
  {value: "monthly", label: "Monthly"}
]

class HistoricPage extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      selectedOption: _.head(options),
      beginTimestamp: new Date(),
      endTimestamp: new Date(),
      aggregationType: _.head(optionsAggregation),
      api: "",
      points: []
    }
  }

  handleChange = (opt) => {
    this.setState({selectedOption: opt})
  }

  shouldComponentUpdate(nextProps, nextState, nextContext) {
    return (!_.isEqual(this.state.selectedOption, nextState.selectedOption)) ||
        (!_.isEqual(this.state.aggregationType, nextState.aggregationType)) ||
        (!_.isEqual(this.state.beginTimestamp, nextState.beginTimestamp)) ||
        (!_.isEqual(this.state.endTimestamp, nextState.endTimestamp)) ||
        (!_.isEqual(this.state.points, nextState.points))
  }

  saveDateRange = (event) => {
    const startDate = this.formatDate(this.state.beginTimestamp)
    const startTime = this.formatTime(this.state.beginTimestamp)
    const endDate = this.formatDate(this.state.endTimestamp)
    const endTime = this.formatTime(this.state.endTimestamp)
    const api = "http://localhost:8080/historical/" + this.state.aggregationType.value + "/" + this.state.selectedOption.value
        + "?start=" + startDate + " " + startTime + "&end=" + endDate + " " + endTime
    this.setState({api: api}, () => this.fetchData())
  }

  formatDate = (date) => {
    var d = new Date(date),
        month =  (d.getMonth() + 1),
        day = d.getDate(),
        year = d.getFullYear();

    month = this.checkTime(month)
    day = this.checkTime(day)
    return day + "." + month + "." + year
  }

  checkTime = (i) => {
    if (i < 10) {
      i = "0" + i;
    }
    return i;
  }

  formatTime = (date) => {
    var d = new Date(date);
    var h = d.getHours();
    var m = d.getMinutes();
    var s = d.getSeconds();
    // add a zero in front of numbers<10
    h = this.checkTime(h);
    m = this.checkTime(m);
    s = this.checkTime(s);
    return h + ":" + m + ":" + s
  }

  handleChangeBegin = (date) => {
    this.setState({beginTimestamp: date})
  }

  handleChangeEnd = (date) => {
    this.setState({endTimestamp: date})
  }

  handleChangeAggregation = (type) => {
    this.setState({aggregationType: type})
  }

  fetchData = () => {
    fetch(this.state.api)
        .then(res => res.json())
        .then(data => {
          this.updateState(_.map(data, this.getMappedPoint))
        })
  }

  getMappedPoint = (item) => {
    return {x: new Date(item.startTime), y: [item.value, item.max, item.min, item.value]}
  }

  updateState = (points) => {
    this.setState({points: points})
  }

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
          <Select
              className="select"
              isSearchable="true"
              placeholder="Select aggregation type"
              options={optionsAggregation}
              value={this.state.aggregationType}
              onChange={this.handleChangeAggregation}
          />
          <div className="datetimepicker-block">
            <TimestampPicker callbackChangeBegin={this.handleChangeBegin} callbackChangeEnd={this.handleChangeEnd}/>
            <button onClick={this.saveDateRange}>Load</button>
          </div>
          <HistoricChart
              currency={this.state.selectedOption.value}
              points={this.state.points}
              aggregationType={this.state.aggregationType.value}
          />

        </div>
    )
  }
}

export default HistoricPage
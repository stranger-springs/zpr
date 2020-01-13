import React from 'react';
import NavigationBar from "../NavigationBar";
import Header from "../Header";
import _ from "lodash";
import Select from 'react-select'
import TimestampPicker from "../TimestampPicker";
import Chart from "./Chart";
import {options} from "../CurrencyOptions";

const optionsAggregation = [
  {value: 'minute', label: 'Minute'},
  {value: 'hourly', label: 'Hourly'},
  {value: 'daily', label: 'Daily'},
  {value: 'monthly', label: 'Monthly'},
];

class HistoricChart extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      selectedOption: _.head(options),
      beginTimestamp: new Date(),
      endTimestamp: new Date(),
      aggregationType: _.head(optionsAggregation),
      api: ''
    };
  }

  handleChange = (opt) => {
    this.setState({selectedOption: opt});
  }

  shouldComponentUpdate(nextProps, nextState, nextContext) {
    return !_.isEqual(this.state.selectedOption, nextState.selectedOption);
  }

  saveDateRange = (event) => {
    const startDate = this.state.beginTimestamp.toISOString().slice(0, -2);
    const endDate = this.state.endTimestamp.toISOString().slice(0, -2);
    const api = 'http://localhost:8080/historical/' + this.state.aggregationType.value + "/" + this.state.selectedOption.value  + '?start=' + startDate + "&end=" + endDate;
    this.setState({api: api})
  }

  handleChangeBegin = (date) => {
    this.setState({beginTimestamp: date});
  }

  handleChangeEnd = (date) => {
    this.setState({endTimestamp: date});
  }

  handleChangeAggregation = (type) => {
    this.setState({aggregationType: type})
  }

  onApiChange = (newApi) => {
    this.setState({api: newApi});
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
          <Chart
              currency={this.state.selectedOption.value}
              api={this.state.api}
              aggregationType={this.state.aggregationType.value}
              onApiChange={this.onApiChange}
          />
        </div>
    )
  }
}

export default HistoricChart;
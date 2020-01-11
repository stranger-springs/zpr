import React from 'react';
import NavigationBar from "./NavigationBar";
import Header from "./Header";
import _ from "lodash";
import Select from 'react-select'
import IndicesChart from "./charts/IndicesChart";
import bitcoinImg from '/home/aleksandra/ZPR/ui/src/images/bitcoin.svg';
import zcashImg from '/home/aleksandra/ZPR/ui/src/images/zcash.svg';
import ethernumImg from '/home/aleksandra/ZPR/ui/src/images/ethernum.svg';
import litecoinImg from '/home/aleksandra/ZPR/ui/src/images/litecoin.svg';

const options = [
  { value: 'bitcoin', label: <div>Bitcoin (BTC) <img src={bitcoinImg} height="20px" width="20px"/></div> },
  { value: 'zcash', label: <div>ZCash (ZEC) <img src={zcashImg} height="20px" width="20px"/></div>  },
  { value: 'litecoin', label: <div>Litecoin (LTC) <img src={litecoinImg} height="20px" width="20px"/></div> },
  { value: 'ethernum', label: <div>Ethernum (ETC) <img src={ethernumImg} height="20px" width="20px"/></div> }
];

const optionsIndices = [
  { value: 'ema', label: 'Exponential Moving Average (EMA)' },
  { value: 'sma', label: 'Simple Moving Average (SMA)' },
  { value: 'rsi', label: 'Relative Strength Index (RSI)' }
];

const optionsRange = [
  { value: '5', label: '5' },
  { value: '9', label: '9' },
  { value: '14', label: '14' }
];

class IndicesPage extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      selectedOption: _.head(options),
      selectedIndex: _.head(optionsIndices),
      selectedRange: _.head(optionsRange),
      title: '',
      api: '',
      apiLast: ''
    };
  }

  handleChange = (opt) => {
    this.setState({selectedOption: opt});
  }

  handleChangeIndices = (opt) => {
    this.setState({selectedIndex: opt});
  }

  handleChangeRange = (opt) => {
    this.setState({selectedRange: opt});
  }

  loadData = (event) => {
    const apiLink = 'http://localhost:8080/index/'+ this.state.selectedOption.value + '/' + this.state.selectedIndex.value + this.state.selectedRange.label;
    const apiLinkLast  = apiLink + '/last';
    const title = this.state.selectedIndex.label + " for " + this.state.selectedOption.value + " with range " + this.state.selectedRange.label;
    this.setState({title: title, api: apiLink, apiLast: apiLinkLast});
  }

  render() {
    return(
        <div className="app">
          <Header/>
          <NavigationBar/>
          <div className="select-indices-block">
            <Select className="select-indices"
                    isSearchable="true"
                    placeholder="Select cryptocurrency"
                    options={options}
                    value = {this.state.selectedOption}
                    onChange={this.handleChange}
            />
            <Select className="select-indices"
                    defaultValue = {optionsIndices[0]}
                    isSearchable="true"
                    placeholder="Select type of index"
                    options={optionsIndices}
                    value = {this.state.selectedIndex}
                    onChange={this.handleChangeIndices}
            />
            <Select className="select-indices"
                    isSearchable="true"
                    placeholder="Select range"
                    options={optionsRange}
                    value = {this.state.selectedRange}
                    onChange={this.handleChangeRange}
            />
          </div>
          <button onClick={this.loadData}>Load</button>
          <IndicesChart api={this.state.api}
                          apiLast={this.state.apiLast}
                          title = {this.state.title}/>
        </div>
    )
  }
}

export default IndicesPage;
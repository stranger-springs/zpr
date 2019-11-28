import React from 'react';
import Select from 'react-select'
import _ from "lodash";
import Header from './component/Header';
import EthernumChart from "./component/EthernumChart";
import BitcoinChart from "./component/BitcoinChart";
import NavigationBar from "./component/NavigationBar";
import LitecoinChart from "./component/LitecoinChart";
import ZCashChart from "./component/ZCashChart";

const options = [
  { value: 'bitcoin', label: 'Bitcoin (BTC)' },
  { value: 'zcash', label: 'ZCash (ZEC)' },
  { value: 'litecoin', label: 'Litecoin (LTC)' },
  { value: 'ethernum', label: 'Ethernum (ETH)' },
];

class App extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      selectedOption: _.head(options)

    };
  }

  handleChange = (opt) => {
    this.setState({selectedOption: opt});
  }

  shouldComponentUpdate(nextProps, nextState, nextContext) {
     return !_.isEqual(this.state.selectedOption, nextState.selectedOption);
  }

  getChart = (name) => ({
    "bitcoin":  <BitcoinChart api={'http://localhost:8081/bitcoin'}
                              apiLast={'http://localhost:8081/bitcoin/last'}
                              title={"Bitcoin Price"} />,
    "ethernum": <EthernumChart api={'http://localhost:8081/ethernum'}
                              apiLast={'http://localhost:8081/ethernum/last'}
                              title={"Ethernum Price"} />,
    "litecoin": <LitecoinChart api={'http://localhost:8081/ethernum'}
                               apiLast={'http://localhost:8081/ethernum/last'}
                               title={"Litecoin Price"} />,
    "zcash": <ZCashChart api={'http://localhost:8081/ethernum'}
                               apiLast={'http://localhost:8081/ethernum/last'}
                               title={"ZCash Price"} />

  })[name]

	render() {

		return (
		<div className="app">
      <Header />
      <NavigationBar/>
      <Select className="select"
               isSearchable="true"
               placeholder="Select cryptocurrency"
               options={options}
               value = {this.state.selectedOption}
               onChange={this.handleChange}
      />
      {this.getChart(this.state.selectedOption.value)}
		</div>
		);
  }
}

export default App;
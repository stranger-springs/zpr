import React from 'react';
import RealtimeChart from "./RealtimeChart";

class BitcoinChart extends React.Component {

  render() {
  const  {api, apiLast, title} = this.props;
    return (
       <RealtimeChart api={api}
                      apiLast={apiLast}
                      title = {title}/>
    );
  }
}
export default BitcoinChart;
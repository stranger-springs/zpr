import React, { Component } from 'react';
import RealtimeChart from './RealtimeChart';
import IndicesChart from './IndicesChart';


class ChartSelector extends Component {

   render() {

    if(this.props.chartType === "ceny"){
        return(
            <div>
                <RealtimeChart />
            </div>
        );
    }
    else {
        return(
            <div>
                <IndicesChart />
            </div>
        );
    }
   }
}


export default ChartSelector;
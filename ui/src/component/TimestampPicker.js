import React from 'react';
import DatePicker from "react-datepicker"
import "react-datepicker/dist/react-datepicker.css"

class TimestampPicker extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      beginTimestamp: new Date(),
      endTimestamp: new Date()
    };
  }

  handleChangeBegin = date => {
    this.setState({beginTimestamp: date}, () => {
      this.props.callbackChangeBegin(this.state.beginTimestamp)});
  }

  handleChangeEnd = date => {
    this.setState({endTimestamp: date}, () => {
      this.props.callbackChangeEnd(this.state.endTimestamp)});
  }

  render() {
    return(
       <div className="datetime-picker">
         <DatePicker className="picker"
                     showTimeSelect
                     selected={this.state.beginTimestamp}
                     timeFormat="HH:mm"
                     timeIntervals={10}
                     todayButton="Today"
                     timeCaption="Time"
                     dateFormat="dd/MM/yyyy HH:mm"
                     maxDate ={new Date()}
                     onChange={this.handleChangeBegin}
         />
         <DatePicker className="picker"
                     showTimeSelect
                     selected={this.state.endTimestamp}
                     todayButton="Today"
                     timeFormat="HH:mm"
                     timeIntervals={10}
                     timeCaption="Time"
                     maxDate ={new Date()}
                     dateFormat="dd/MM/yyyy HH:mm"
                     onChange={this.handleChangeEnd}
         />
       </div>
    )
  }
}

export default TimestampPicker
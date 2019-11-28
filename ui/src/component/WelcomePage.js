import React from 'react';
import Header from "./Header";

class WelcomePage extends React.Component {

  start = (event) => {
    this.props.history.push(`/realtime`);
  }

  render(){
    return(
        <div classname="welcome">
          <Header/>
          <h1>WelcomePage</h1>
          <button onClick={this.start}>Start</button>
        </div>
    );
  }

}

export default WelcomePage;
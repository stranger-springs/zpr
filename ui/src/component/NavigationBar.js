import React from 'react';
import {withRouter} from 'react-router-dom';

class NavigationBar extends React.Component {

  switchHistoric = (event) => {
    this.props.history.push(`/historic`)
  }

  switchRealtime = (event) => {
    this.props.history.push(`/realtime`)
  }

  switchIndices = (event) => {
    this.props.history.push(`/indices`)
  }

  switchWelcome = (event) => {
    this.props.history.push(`/`)
  }

  render() {
    return(
        <div className="navigation-bar">
          <button className="navigation-button" onClick={this.switchHistoric}>Historic Chart</button>
          <button className="navigation-button" onClick={this.switchRealtime}>Realtime Chart</button>
          <button className="navigation-button" onClick={this.switchIndices}>Indices Chart</button>
          <button className="navigation-button" onClick={this.switchWelcome}>Welcome site</button>
        </div>
    );
  }
}

export default withRouter(NavigationBar);
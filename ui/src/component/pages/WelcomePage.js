import React from "react"
import Header from "../Header"

class WelcomePage extends React.Component {

  start = (event) => {
    this.props.history.push(`/realtime`)
  }

  render() {
    return (
        <div className="app">
          <Header/>
          <h1 className="welcome">Welcome in the CryptoCurrencyApp!</h1>
          <button onClick={this.start}>Start</button>
        </div>
    )
  }
}

export default WelcomePage
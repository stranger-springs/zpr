import React from "react"
import {BrowserRouter, Route, Switch} from "react-router-dom"
import WelcomePage from "./WelcomePage"
import CurrencyPage from "./CurrencyPage"
import HistoricPage from "./HistoricPage"
import IndicesPage from "./IndicesPage"

const RouterCharts = () => (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={WelcomePage}/>
        <Route path="/realtime" component={CurrencyPage}/>
        <Route path="/historic" component={HistoricPage}/>
        <Route path="/indices" component={IndicesPage}/>
      </Switch>
    </BrowserRouter>
)

export default RouterCharts

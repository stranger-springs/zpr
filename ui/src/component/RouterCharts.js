import React from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import WelcomePage from "./WelcomePage";
import App from "./App";
import HistoricChart from "./charts/HistoricChart";
import IndicesPage from "./IndicesPage";

const RouterCharts = () => (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={WelcomePage}/>
        <Route path="/realtime" component={App}/>
        <Route path="/historic" component={HistoricChart}/>
        <Route path="/indices" component={IndicesPage}/>
      </Switch>
    </BrowserRouter>
);

export default RouterCharts;

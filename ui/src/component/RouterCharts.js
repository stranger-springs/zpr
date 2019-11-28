import React from 'react';
import {BrowserRouter, Route} from 'react-router-dom';
import WelcomePage from "./WelcomePage";
import App from "../App";

const RouterCharts = () => (
    <BrowserRouter>
      <Route exact path="/" component={WelcomePage}/>
      <Route path="/realtime" component={App}/>
    </BrowserRouter>
);

export default RouterCharts;

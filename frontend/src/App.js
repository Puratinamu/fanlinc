import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { createStore } from './store'
import Root from './routes/';

import "./styles.scss";

class App extends React.Component {

    render() {
        return (
          <Router>
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
            <Switch>
              <Route path="/" component={Root} />
            </Switch>
          </Router>
        );
    }
}

export default createStore(App);


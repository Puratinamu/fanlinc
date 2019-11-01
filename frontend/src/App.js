import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from "./routes/Home/Home"
import Signup from './routes/Signup/Signup'
import Login from './routes/Login/Login';
import ViewProfile from "./routes/ViewProfile/";


class App extends React.Component {

  render() {
    return (

      <Router>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
        <Switch>
          <Route path="/viewprofile">
            <ViewProfile />
          </Route>
          <Route path="/signup">
            <Signup />
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </Router>
    );
  }
}
export default App

import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Home from "./routes/Home/Home"
import Signup from "./routes/Signup/Signup"
import JoinFandom from "./routes/JoinFandom/";
import "./styles.scss";


class App extends React.Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path="/joinfandom">
            <JoinFandom />
          </Route>
          <Route path="/signup">
            <Signup />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </Router>
    );
  }
}

export default App;


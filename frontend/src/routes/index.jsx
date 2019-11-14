import React from 'react';
import {withStore} from '../store';
import { Switch, Route } from 'react-router-dom';
import Box from '@material-ui/core/Box';
import Main from './Main/';
import Signup from './Signup/Signup'
import Login from "./Login/Login";

class Root extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoggedIn: false,
            currentRoute: "viewprofile",
            routes: null
        };
    }

    componentDidMount() {
        // Determine routes based on whether the user is logged in or not
        let isLoggedIn = this.props.store.get("isLoggedIn");
        this.setState({
            isLoggedIn: isLoggedIn,
            routes: (isLoggedIn ? loggedInRoutes(this.props) : nonLoggedInRoutes())
        });

        if (isLoggedIn) {
            // Default to main if no sub route is provided
            this.props.history.push(this.props.history.location.pathname === '/' ? '/main' : this.props.history.location.pathname);
        } else if (this.props.history.location.pathname === "/signup") {
            this.props.history.push('/signup');
        } else {
            this.props.history.push('/login');
        }
    }

    render() {
        return (
            <Box height="100%" width="100%">
              {this.state.routes}
            </Box>
        );
    }

}

const loggedInRoutes = (props) => {
    return (
      <Switch>
        <Route path="/signup" component={Signup} />
        <Route path="/login" component={Login} />
        <Route path="/main">
          <Main store={props.store} />
        </Route>
        <Route path="*">
            INVALID ROUTE
        </Route>
      </Switch>
    );
}

const nonLoggedInRoutes = () => {
    return (
      <Switch>
        <Route path="/signup" component={Signup} />
        <Route path="/login" component={Login} />
      </Switch>
    );
}

export default withStore(Root);


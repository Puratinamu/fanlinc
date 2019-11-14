import React from 'react';
import {withStore} from '../store';
import { Switch, Route } from 'react-router-dom';
import Box from '@material-ui/core/Box';
import Home from './Home/';
import NewFandom from './NewFandom/';
import Signup from './Signup/Signup'
import Login from "./Login/Login";
import JoinFandom from './JoinFandom/';
import ViewProfilePage from './ViewProfilePage/';


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
        let isLoggedIn = this.props.store.get("isLoggedIn");
        this.setState({
            isLoggedIn: isLoggedIn,
            routes: (isLoggedIn ? loggedInRoutes() : nonLoggedInRoutes())
        });

        if (isLoggedIn) {
            this.props.history.push(this.props.history.location.pathname);
        } else if (this.props.history.location.pathname === "/signup") {
            this.props.history.push('/signup');
        } else {
            this.props.history.push('/login');
        }
    }

    componentDidUpdate() {
        console.log(this.state.isLoggedIn);
    }

    render() {
        return (
            <Box height="100%" width="100%">
              {this.state.routes}
            </Box>
        );
    }

}

const loggedInRoutes = () => {
    return (
      <Switch>
        <Route path="/signup" component={Signup} />
        <Route path="/login" component={Login} />
        <Route path="/viewprofile" component={ViewProfilePage} />
        <Route path="/joinfandom" component={JoinFandom} />
        <Route path="/makefandom" component={NewFandom} />
        <Route path="/main" component={Home} />
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


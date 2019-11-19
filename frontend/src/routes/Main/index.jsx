import React from 'react';
import { Switch, Route, withRouter } from 'react-router-dom';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import Navbar from '../../components/NavBar/';
import LoginManager from '../../components/core/LoginManager/';
import JoinFandom from './JoinFandom/';
import ViewProfilePage from './ViewProfilePage/';
import NewFandom from './NewFandom/';
import ViewContacts from './ViewContacts/ViewContacts'
import NewPost from './NewPost/';

import Home from './Home';

import "./styles.scss";

class Main extends React.Component {

    componentDidMount() {
        // Here we assume the user is logged in
        let nextRoute = '/main',
            requestedRoute = this.props.history.location.pathname;

        if (!requestedRoute.match(/^\/main\/*$/)) {
            nextRoute = requestedRoute;
        }

        // Go to the next route
        this.props.history.push(nextRoute + (this.props.history.location.search ? this.props.history.location.search : ""));
    }

    routeTo(newRoute) {
        // Here we assume the paths are valid since it comes from the
        // navigation bar
        this.props.history.push(newRoute);
    }

    render() {
        return (
          <Box className="cldi-home-page">
            <LoginManager />
              <Switch>
                <Route path="/main/viewprofile" component={ViewProfilePage} />
                <Route path="/main/joinfandom" component={JoinFandom} />
                <Route path="/main/newfandom" component={NewFandom} />
                <Route path="/main/contacts" component={ViewContacts} />
                <Route path="/main/newpost" component={NewPost} />
                <Route exact path="/main" component={Home} />
                <Route path="*"><Typography align="center">INVALID PATH</Typography></Route>
              </Switch>
              <Navbar callback={this.routeTo.bind(this)} />
          </Box>
        )
    }
}

export default withRouter(Main);

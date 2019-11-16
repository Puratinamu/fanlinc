import React from 'react';
import { Switch, Route, withRouter } from 'react-router-dom';
import Box from '@material-ui/core/Box';
import Navbar from '../../components/NavBar/';
import LoginManager from '../../components/core/LoginManager/';
import JoinFandom from './JoinFandom/';
import ViewProfilePage from './ViewProfilePage/';
import NewFandom from './NewFandom/';

import "./styles.scss";

class Main extends React.Component {

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
                <Route path="/main/addfandom" component={NewFandom} />
              </Switch>
              <Navbar callback={this.routeTo.bind(this)} />
          </Box>
        )
    }
}

export default withRouter(Main);

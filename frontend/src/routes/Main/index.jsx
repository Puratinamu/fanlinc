import React from 'react';
import { withStore } from '../../store';
import { Switch, Route } from 'react-router-dom';
import Box from '@material-ui/core/Box';
import Navbar from '../../components/NavBar/';
import LoginManager from '../../components/core/LoginManager/';
import JoinFandom from './JoinFandom/';
import ViewProfilePage from './ViewProfilePage/';
import NewFandom from './NewFandom/';

import "./styles.scss";

class Main extends React.Component {

    render() {
        return (
            <Box className="cldi-home-page">
                <LoginManager />
                <Switch>
                  <Route path="/main/viewprofile" component={ViewProfilePage} />
                  <Route path="/main/joinfandom" component={JoinFandom} />
                  <Route path="/main/makefandom" component={NewFandom} />
                </Switch>
                <Navbar />
            </Box>
        )
    }
}

export default Main;

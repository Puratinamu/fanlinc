import React from 'react';
import { withStore } from '../../store';
import Box from '@material-ui/core/Box';
import Navbar from '../../components/NavBar/';
import LoginManager from '../../components/core/LoginManager/';

import "./styles.scss";

class Home extends React.Component {

    render() {
        return (
            <Box className="cldi-home-page">
                <LoginManager />
                <Navbar />
            </Box>
        )
    }
}

export default withStore(Home);

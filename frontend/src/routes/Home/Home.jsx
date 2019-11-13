import React from 'react';
import { withStore } from '../../store';
import LoginManager from '../../components/core/LoginManager'

class Home extends React.Component {

    render() {
        return (
            <div>
                <LoginManager />
                This is place holder for the home page
            </div>
        )
    }
}

export default withStore(Home);

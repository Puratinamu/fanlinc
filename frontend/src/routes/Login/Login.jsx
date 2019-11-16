import React from 'react';
import LoginPage from '../../components/LoginPage/LoginPage'
import { withStore } from '../../store';

class Login extends React.Component {

    render() {
        return <LoginPage className="cldi-login-page" store={this.props.store}/>
    }

}

export default withStore(Login);

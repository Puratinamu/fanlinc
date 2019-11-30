import React from 'react';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { Box } from '@material-ui/core';
import cookieManager from '../../../cookieManager'
import redirectManger from '../../../redirectManager'
import Link from '@material-ui/core/Link';
import './styles.scss';

class LoginManager extends React.Component {

    constructor(props) {
        super(props)
        this.handleLogOut = this.handleLogOut.bind(this);
        this.handleLogin = this.handleLogin.bind(this);

        this.state = {
            isLoggedIn: false,
        }

    }
    componentDidMount() {
        this.setState({
            isLoggedIn: this.props.store.get("isLoggedIn"),
            authenticatedUser: this.props.store.get("authenticatedUserEmail")
        })

    }
    handleLogOut() {
        cookieManager.setCookie("authenticatedUserEmail", "")
        cookieManager.setCookie("sessionToken", "")
        cookieManager.setCookie("authenticatedOidUser", "")
        redirectManger.reloadPage()

    }
    handleLogin() {

    }
    renderLoggedInUser() {
        if (this.state.isLoggedIn) {
            return (
                <Box display="flex">
                   <a href="/">
                       <img alt="logo" style={{width: "150px", height:"auto"}} src={require('../../../assets/logo-main.png')}/>
                   </a>

                </Box>
            )
        } else {
            return (
                <Typography variant="h6" gutterBottom>
                    Currently Not Logged In
                </Typography>
            )
        }


    }
    renderLoginOption() {
        if (!this.state.isLoggedIn) {
            return <SignInButton onClick={this.handleLogin} />
        } else {
            return <SignOutButton onClick={this.handleLogOut} />
        }
    }
    render() {
        return (
            <Box boxShadow={2} className="login-manager-holder" display='flex'>
                {this.renderLoggedInUser()}
                {this.renderLoginOption()}
            </Box>
        )
    }
}

function SignInButton(props) {
    return (
        <Button
            variant="contained"
            color="primary"
            className="sign-in-button"
            onClick={props.onClick}
            href="/login"
        >
            Sign In
        </Button>
    )
}

function SignOutButton(props) {
    return (
        <Button
            variant="contained"
            color="primary"
            className="sign-in-button"
            onClick={props.onClick}
        >
            Sign Out
        </Button>
    )
}
export default LoginManager;

import React from 'react';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { withStore } from '../../../store'
import { Box } from '@material-ui/core';
import cookieManager from '../../../cookieManager'
import redirectManger from '../../../redirectManager'
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
        redirectManger.reloadPage()

    }
    handleLogin() {

    }
    renderLoggedInUser() {
        if (this.state.isLoggedIn) {
            return (
                <Box display="flex">
                    <Typography variant="h6" gutterBottom display="block" >
                        Currently Logged in as:
                    </Typography>
                    <Typography className="user-name" variant="h6" color="primary" >
                        {this.state.authenticatedUser}
                    </Typography>
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
            <Box className="login-manager-holder" display='flex'>
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
export default withStore(LoginManager);

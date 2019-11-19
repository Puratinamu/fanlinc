import React from 'react';
import Container from '@material-ui/core/Container';
import Card from '@material-ui/core/Card';
import TextField from '@material-ui/core/TextField';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import { withStore } from '../../store'
import Button from '@material-ui/core/Button';
import Link from '@material-ui/core/Link';
import CircularProgress from '@material-ui/core/CircularProgress';
import redirectManager from '../../redirectManager'; import userRequests from '../../requests/userRequests'

require('./LoginPage.scss')

class LoginPage extends React.Component {
    constructor() {
        super()
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleLoginAttempt = this.handleLoginAttempt.bind(this);
        this.validateLoginInput = this.validateLoginInput.bind(this);

        this.state = {
            email: "",
            password: "",
            emailError: false,
            passwordError: false,
            loginInProgress: false,
            loginFailUnauthorizedError: false,
            loginFailInternalServerError: false,
            loginFailBadRequestError: false,
            loginSuccess: false
        }
    }
    componentDidUpdate(){
        this.checkLoginSuccess()
    }
    render() {
        return (
            <Container className="login-page" maxWidth="sm">
                <Card className="login-card">
                    <Typography variant="h6" align="center" gutterBottom>
                        Sign In
                    </Typography>
                    <Box className="login-form-textfields-container">
                        <EmailField onInput={this.handleEmailChange} error={this.state.emailError} />
                        <PasswordField onInput={this.handlePasswordChange} error={this.state.passwordError} />
                        {this.renderLoginFailErrorText()}
                        <LoginButton onClick={this.handleLoginAttempt} />
                        <Typography className="login-page-sign-up-link">
                            Don't have an account?&nbsp; 
                            <Link href="/signup">Click here to sign up.</Link>
                        </Typography>
                        {this.renderLoginLoading()}
                    </Box>
                </Card>
            </Container>
        )
    }
 
    handleEmailChange(newEmail) {
        this.setState({ email: newEmail.target.value, emailError: false })
    }
    handlePasswordChange(newPassword) {
        this.setState({ password: newPassword.target.value, passwordError: false })
    }

    validateLoginInput() {
        let promise = new Promise((resolve, reject) => {
            this.setState({ passwordError: this.state.password === "", emailError: this.state.email === "" })
            resolve()
        })

        return promise
    }
    renderLoginLoading() {
        if (this.state.loginInProgress) {
            return <CircularLoading />
        }
    }
    renderUnauthorized() {
        if (this.state.loginFailUnauthorizedError) {
            return (
                <Typography color="error" variant="subtitle2">
                    Wrong Password!
            </Typography>
            )
        }
    }
    renderInternalServerError() {
        if (this.state.loginFailInternalServerError) {
            return (
                <Typography color="error" variant="subtitle2">
                    Internal Server Error!
            </Typography>
            )
        }
    }
    renderBadRequest() {
        if (this.state.loginFailBadRequestError) {
            return (
                <Typography color="error" variant="subtitle2">
                    Wrong Username / Password!
            </Typography>
            )
        }
    }
    renderLoginFailErrorText() {
        return (
            <div>
                {this.renderUnauthorized()}
                {this.renderInternalServerError()}
                {this.renderBadRequest()}
            </div>
        )
    }
    handleLoginAttempt() {
        this.validateLoginInput()
            .then(
                () => {
                    if (!this.state.passwordError && !this.state.emailError) {
                        this.setState({ loginInProgress: true, loginFailUnauthorizedError: false, loginFailBadRequestError: false, loginFailInternalServerError: false })
                        userRequests.loginUserRequest(this.state.email, this.state.password).then(
                            (loginResponse) => {
                                if (loginResponse.status === 200) {
                                    this.setState({ loginSuccess: true })
                                    /// After we have completed the login sucessfully, we update the global store to have the authenticated user, to be used throughout the app
                                    this.props.store.set('authenticatedUserEmail', this.state.email)
                                    this.props.store.set('isLoggedIn', true)
                                    this.props.store.set('authenticatedOidUser', loginResponse.data.oidUser)
                                    
                                    
                                } else if (loginResponse.status === 401) {
                                    this.setState({ loginFailUnauthorizedError: true })
                                } else if (loginResponse.status === 400) {
                                    this.setState({ loginFailBadRequestError: true })
                                } else if (loginResponse.status === 500) {
                                    this.setState({ loginFailInternalServerError: true })
                                }
                                this.setState({ loginInProgress: false })
                                
                            }
                        )
                    }
                }
            )
    }
    checkLoginSuccess() {
        if (this.state.loginSuccess) {
            redirectManager.handleRedirect()
        }
    }
   
}
function LoginButton(props) {
    return (
        <Button
            variant="contained"
            color="primary"
            className="login-button"
            onClick={props.onClick}
        >
            Sign In
        </Button>
    )
}


function EmailField(props) {

    return (<TextField
        required
        error={props.error}
        label="Email"
        placeholder="somebody@email.com"
        className="login-text-field"
        margin="normal"
        variant="outlined"
        onInput={props.onInput}

    />)
}

function PasswordField(props) {

    return (<TextField
        required
        onInput={props.onInput}
        error={props.error}
        label="Password"
        className="login-text-field"
        margin="normal"
        type="password"
        variant="outlined"

    />)
}

function CircularLoading() {
    const [progress, setProgress] = React.useState(0);

    React.useEffect(() => {
        function tick() {
            // reset when reaching 100%
            setProgress(oldProgress => (oldProgress >= 100 ? 0 : oldProgress + 1));
        }

        const timer = setInterval(tick, 20);
        return () => {
            clearInterval(timer);
        };
    }, []);

    return (
        <Box display="flex" className="login-progress-holder">
            <CircularProgress size={30} variant="determinate" value={progress} />
        </Box>
    );
}
export default withStore(LoginPage)

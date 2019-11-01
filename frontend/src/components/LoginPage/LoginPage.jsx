import React from 'react';
import Container from '@material-ui/core/Container';
import Card from '@material-ui/core/Card';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import CircularProgress from '@material-ui/core/CircularProgress';

import userRequests from '../../requests/userRequests'
require('./LoginPage.scss')

const useStyles = makeStyles(theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        width: 300
    },
    dense: {
        marginTop: theme.spacing(2),
    },
    menu: {
        width: 200,
    },
    card: {
        padding: theme.spacing(3)
    },
    button: {
        marginTop: theme.spacing(2),
        width: 150
    },
    progressHolder: {
        marginTop: 15,
        marginLeft: 15
    }
}));


export default function LoginPage(props) {
    const classes = useStyles();
    return (
        <Container maxWidth="sm" >
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
            <Card className={classes.card}>
                <img src={process.env.PUBLIC_URL + '/main.jpg'} alt="logo" width={50} />
                <Typography variant="h6" gutterBottom>
                    Sign In
                </Typography>
                <LoginMain />


            </Card>
        </Container>
    )
}
class LoginMain extends React.Component {
    constructor(props) {
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
            loginFailBadRequestError: false
        }
    }

    handleEmailChange(newEmail) {
        this.setState({ email: newEmail.target.value, emailError: false })
    }
    handlePasswordChange(newPassword) {
        this.setState({ password: newPassword.target.value, passwordError: false })
    }

    validateLoginInput() {
        let promise = new Promise((resolve, reject) => {
            if (this.state.email === "") {
                this.setState({ emailError: true })
            } else {
                this.setState({ emailError: false })
            }
            if (this.state.password === "") {
                this.setState({ passwordError: true })
            } else {
                this.setState({ passwordError: false })
            }
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
                <Typography className="error-text" variant="subtitle2">
                    Wrong Password!
            </Typography>
            )
        }
    }
    renderInternalServerError() {
        if (this.state.loginFailInternalServerError) {
            return (
                <Typography className="error-text" variant="subtitle2">
                    Internal Server Error!
            </Typography>
            )
        }
    }
    renderBadRequest() {
        if (this.state.loginFailBadRequestError) {
            return (
                <Typography className="error-text" variant="subtitle2">
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
                    console.log(!this.state.passwordError && !this.state.emailError)
                    if (!this.state.passwordError && !this.state.emailError) {
                        this.setState({ loginInProgress: true })
                        this.setState({loginFailUnauthorizedError: false, loginFailBadRequestError: false, loginFailInternalServerError:false})
                        userRequests.loginUserRequest(this.state.email, this.state.password).then(
                            (loginResponse) => {
                                if (loginResponse.status === 200) {
                                    console.log("Success")
                                } else if (loginResponse.status === 401) {
                                    console.log("Unauthorized")
                                    this.setState({ loginFailUnauthorizedError: true })
                                } else if (loginResponse.status === 400) {
                                    console.log("Bad Request")
                                    this.setState({ loginFailBadRequestError: true })
                                } else if (loginResponse.status === 500) {
                                    console.log("Internal Server Error")
                                    this.setState({ loginFailInternalServerError: true })
                                }
                                this.setState({ loginInProgress: false })
                            }
                        )
                    }
                }
            )
    }

    render() {
        return (
            <Box>
                <Box>
                    <EmailField onInput={this.handleEmailChange} error={this.state.emailError} />
                </Box>
                <Box>
                    <PasswordField onInput={this.handlePasswordChange} error={this.state.passwordError} />
                </Box>

                {this.renderLoginFailErrorText()}
                <Box display="flex">
                    <LoginButton onClick={this.handleLoginAttempt} />
                    {this.renderLoginLoading()}
                </Box>
            </Box>
        )
    }
}
function LoginButton(props) {
    const classes = useStyles();
    return (
        <Button
            variant="contained"
            color="primary"
            className={classes.button}
            onClick={props.onClick}
        >
            Sign In
        </Button>
    )
}


function EmailField(props) {
    const classes = useStyles();

    return (<TextField
        id="email"
        required
        error={props.error}
        label="Email"
        placeholder="somebody@email.com"
        className={classes.textField}
        margin="normal"
        variant="outlined"
        onInput={props.onInput}


    />)
}

function PasswordField(props) {
    const classes = useStyles();

    return (<TextField
        id="password"
        required
        onInput={props.onInput}
        error={props.error}
        label="Password"
        className={classes.textField}
        margin="normal"
        type="password"
        variant="outlined"

    />)
}

function CircularLoading() {
    const classes = useStyles();
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
        <Box display="flex" className={classes.progressHolder}>
            <CircularProgress size={30} variant="determinate" value={progress} />
        </Box>
    );
}
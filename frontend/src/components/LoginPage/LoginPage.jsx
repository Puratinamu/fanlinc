import React from 'react';
import Container from '@material-ui/core/Container';
import Card from '@material-ui/core/Card';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
//import LinkedInIcon from '@material-ui/icons/LinkedIn';

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
    }
}));




export default function LoginPage(props) {
    const classes = useStyles();
    return (
        <Container maxWidth="sm" >
            <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
            <Card className={classes.card}>
                <img src={process.env.PUBLIC_URL + '/main.jpg'} width={50} />
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
        this.handleUserNameChange = this.handleUserNameChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleLoginAttempt = this.handleLoginAttempt.bind(this);

        this.state = {
            username: "",
            password: "",
            usernameError:false,
            passwordError:false,
        }
    }

    handleUserNameChange(newUsername) {
        this.setState({ username: newUsername.target.value, usernameError:false })
    }
    handlePasswordChange(newPassword) {
        this.setState({ password: newPassword.target.value,passwordError:false })
    }

    handleLoginAttempt() {
        if (this.state.username === "") {
            this.setState({usernameError:true})
        } else{
            this.setState({usernameError:false})
        }
        if (this.state.password === "") {
            this.setState({passwordError:true})
        } else{
            this.setState({passwordError:false})
        }
    }

    render() {
        return (
            <Box>
                <Box>
                    <UserNameField onInput={this.handleUserNameChange} error={this.state.usernameError}/>
                </Box>
                <Box>
                    <PasswordField onInput={this.handlePasswordChange}  error={this.state.passwordError} />
                </Box>
                <LoginButton onClick={this.handleLoginAttempt} />
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


function UserNameField(props) {
    const classes = useStyles();

    return (<TextField
        id="username"
        required
        error={props.error}
        label="Username"
        placeholder="Username"
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


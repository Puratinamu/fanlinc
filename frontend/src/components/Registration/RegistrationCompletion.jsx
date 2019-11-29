import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';
import Box from '@material-ui/core/Box';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import { Typography } from '@material-ui/core';
import ErrorIcon from '@material-ui/icons/Error';
import userRequests from '../../requests/userRequests';
const useStyles = makeStyles(theme => ({
    progress: {
        margin: theme.spacing(2),
    },
}));

function CircularDeterminate() {
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
        <div>
            <CircularProgress className={classes.progress} variant="determinate" value={progress} />
        </div>
    );
}

function LoginComplete() {
    return (
        <Box>
            <Box justifyContent="center" display="flex">
                <CheckCircleIcon color="primary" fontSize="large" />
            </Box>
            <Box justifyContent="center" display="flex">
                <Typography variant="h5" component="h5" >Sign Up Complete!</Typography>
            </Box>
            <Box justifyContent="center" display="flex">
                <Typography variant="h5" component="h5" >Signing you in now... <CircularProgress /></Typography>
            </Box>
        </Box>
    )
}


function LoginFail() {
    return (
        <Box>
            <Box justifyContent="center" display="flex">
                <ErrorIcon color="error" fontSize="large" />
            </Box>
            <Box justifyContent="center" display="flex">
                <Typography variant="h5" component="h5" >Something Went Horribly Wrong....</Typography>
            </Box>
        </Box>
    )
}

class RegistrationCompletion extends React.Component {

    constructor(props) {
        super()
        this.getSignupStatus = this.getSignupStatus.bind(this);
        this.state = {
            request: {
                "email": props.email,
                "username": props.username,
                "description": props.bio,
                "password": props.passwordValue,
                "fandoms": [
                ]
            },
            currentState: "loading"
        }

        // Add the fandom oids to the request
        if (props.fandom) {
            this.state.request.fandoms.push({
                oidFandom: `${props.fandom.oidFandom}`,
                level: `${props.interestLevel}`
            });
        }
    }

    componentDidMount() {
        userRequests.putUser(this.state.request).then(response => {
            if (response.status === 200) {
                this.setState({ currentState: "success" })
            } else {
                this.setState({ currentState: "failed" })
            }
        });
    }

    getSignupStatus() {
        if (this.state.currentState === "loading") {
            return <CircularDeterminate />
        } else if (this.state.currentState === "success") {
            return <LoginComplete />
        } else {
            return <LoginFail />
        }
    }

    render() {
        return (
          <Box display="flex" justifyContent="center">
            {this.getSignupStatus()}
          </Box>
        )
    }
}

export default RegistrationCompletion;


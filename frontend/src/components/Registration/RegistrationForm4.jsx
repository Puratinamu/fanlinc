import React from 'react';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Box from '@material-ui/core/Box';
import Paper from '@material-ui/core/Paper';

const useStyles = makeStyles(theme => ({
  root: {
    padding: theme.spacing(1),
    margin: theme.spacing(1)
  },
  infoBox: {
    padding: theme.spacing(1),
    margin: theme.spacing(1)
  },
  paperBlock: {
    width: 200,
    padding: theme.spacing(1),
    marginTop: theme.spacing(1),
    display:"flex",
    paddingRight:theme.spacing(5)
  },
  bioHolder: {
    textOverflow: "ellipsis"
  },
  textBox: {
    fontWeight: "bold",
    marginLeft: "auto"
  }
}));

class RegistrationForm4 extends React.Component {

    constructor(props) {
        super()

        this.state = {
            firstName: props.firstName,
            lastName: props.lastName,
            bio: props.bio,
            email: props.email,
            username: props.username,
            fandom: props.fandom,
            interestLevel: props.interestLevel
        };
    }

    componentDidMount() {
        this.setState({
            "mounted":true
        });
    }

    render() {
        return (
          <RegistrationForm4Main {...this.state} />
        )
    }
}

function TextBox(props) {
    const classes = useStyles();

    return (
      <Paper className={classes.paperBlock}>
        <Typography component="span" variant="overline" gutterBottom>
          {props.label}
        </Typography>
        <Typography className={classes.textBox} component="span" variant="body2" gutterBottom>
          {props.value}
        </Typography>
      </Paper>
    )
}

function RegistrationForm4Main(props) {
    const classes = useStyles();

    return (
      <Box display="flex" justifyContent="center">
        <div className={classes.root}>
          <Typography variant="h5" component="h5" >
            Confirm Your Information
        </Typography>
          <Box className={classes.infoBox}>
            <TextBox label="First Name" value={props.firstName} />
            <TextBox label="Last Name" value={props.lastName} />
            <TextBox label="Username" value={props.username} />
            <TextBox label="Email" value={props.email} />
            <TextBox label="Bio" value={props.bio} />
            <TextBox label="Fandoms" value={props.fandom && props.fandom.name}/>
            <TextBox label="Interest Level" value={props.interestLevel}/>
          </Box>
        </div>
      </Box>
    );
}

export default RegistrationForm4;


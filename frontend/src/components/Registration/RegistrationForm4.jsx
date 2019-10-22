import React from 'react';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Box from '@material-ui/core/Box';
import Paper from '@material-ui/core/Paper';
import Divider from '@material-ui/core/Divider';


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
    padding: theme.spacing(1),
    paddingBottom: theme.spacing(2),
    marginTop: theme.spacing(1)
  },
  bioHolder: {
    textOverflow: "ellipsis"
  }
}));

class RegistrationForm4 extends React.Component {
  constructor(props) {
    super()
    this.state = {
      firstName: props.firstName,
      lastName: props.lastName,
      bio: props.bio,
      username: props.username
    }
  }
  updateValue(e) {
    this.setState({
      firstName: e.firstName,
      lastName: e.lastName,
      bio: e.bio,
      username: e.username
    })
  }
  render() {
    return (
      <RegistrationForm4Main {...this.state} />
    )
  }
}
function RegistrationForm4Main(props) {
  const classes = useStyles();

  return (
    <Box display="flex" justifyContent="center">
      <div className={classes.root}>
        <Typography variant="h5" component="h5">
          Confirm Your Information
      </Typography>
        <Box className={classes.infoBox}>
          <Paper className={classes.paperBlock}>
            <Typography variant="body2" gutterBottom>
              First Name
          </Typography>
            <Divider />
            <Typography variant="overline" display="block" gutterBottom>
              {props.firstName}
            </Typography>
          </Paper>
          <Paper className={classes.paperBlock}>
            <Typography  variant="body2" gutterBottom>
              Last Name
            </Typography>
            <Divider />
            <Typography  variant="overline" display="block" gutterBottom>
              {props.lastName}
            </Typography>
          </Paper>

          <Paper className={classes.paperBlock} >
            <Typography variant="body2" gutterBottom>
              Username
           </Typography>
           <Divider />
            <Typography  variant="overline" display="block" gutterBottom>
              {props.username}
            </Typography>

          </Paper >
          <Paper className={classes.paperBlock}>
            <Typography  variant="body2" gutterBottom>
              Bio
          </Typography>
          <Divider  />
            <Typography component="p" variant="overline" >
              {props.bio}
            </Typography>
          </Paper>
          <Paper className={classes.paperBlock}>
            <Typography variant="body2" gutterBottom>
              Your Fandoms
          </Typography>
          <Divider  />

          </Paper>
        </Box>
      </div>
    </Box>
  );
}
export default RegistrationForm4
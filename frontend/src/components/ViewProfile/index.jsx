import React from 'react';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper'
import Avatar from '@material-ui/core/Avatar'
import TextField from '@material-ui/core/TextField'
import Divider from '@material-ui/core/Divider';
import Box from '@material-ui/core/Box';
import CircularProgress from '@material-ui/core/CircularProgress';
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button';
import userRequests from '../../requests/userRequests';
import redirectManager from '../../redirectManager';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import Slide from '@material-ui/core/Slide'
import './styles.scss';

const USER_INFORMATION_LABEL = "User Information",
  USER_NAME_LABEL = "User Name",
  USER_EMAIL_LABEL = "Email",
  USER_BIO_LABEL = "Biography",
  USER_FANDOMS_LABEL = "Fandoms",
  SNACKBAR_TIMEOUT = 4000;


class ViewProfile extends React.Component {

  constructor(props) {
    super(props);
    this.handleAddingContact = this.handleAddingContact.bind(this);

    this.store = props.store;

    this.state = {
      user: null,
      loading: true,
      notificationOpen: false,
      message: "Unknown Error: Please contact support"
    };
  }

  handleAddingContact() {
    userRequests.putContact({
      "oidUser": this.props.store.get('authenticatedOidUser'),
      "contactOidUser": this.state.user.oidUser
    }).then(response => {
      if (response.status === 200) {
        this.setState({ message: "Your post has been successfully added!", notificationOpen: true })
      } else if (response.status === 500) {
        this.setState({ message: "Internal server error: Please contact support", notificationOpen: true })
      } else if (response.status === 409) {
        this.setState({ message: "You have already added this user as a contact", notificationOpen: true })
      } else if (response.status === 400) {
        this.setState({ message: "Bad request error: Please contact support", notificationOpen: true })
      }
      else {
        this.setState({ message: "Unknown Error: Please contact support", notificationOpen: true })
      }
    })
  }

  handleClose() {
    this.setState({
      notificationOpen: false
    });
  }

  componentDidMount() {
    let id;
    if (redirectManager.getUrlParam("id") === undefined) {
      id = this.props.store.get('authenticatedOidUser')
    } else {
      id = redirectManager.getUrlParam("id")
    };
    userRequests.getUser(id).then(response => {
      let user;

      if (response.status === 200) {
        user = response.data;
      }

      this.setState({
        user: user,
        loading: false
      });
    });
  }

  render() {
    // Render loader if it is loading
    if (this.state.loading && !this.state.user) {
      return (
        <CircularProgress />
      );
    }

    // Render message if user was not found !! THIS SHOULDNT HAPPEN !!
    if (!this.state.user) {
      return (
        <Typography component='h4' variant='h4' color='textSecondary' align='center'>
          User Profile Not Found
                </Typography>
      );
    }

    // Render list of fandoms
    let fandomList = [];
    for (let fandom of this.state.user.fandoms) {
      fandomList.push(
        <ProfileField
          key={fandom.oidFandom}
          label="Name"
          value={fandom.name}
          helperText={fandom.relationship && `Interest Level: ${fandom.relationship}`} />
      )
    }
    return (
      <Paper>
        <Box px={3} pt={5} pb={10} >
          <Grid spacing={2} className="cldi-view-user-profile" container direction="column" alignItems="center">
            <Grid item xs={12}>
              <Avatar className="cldi-profile-avatar" alt="Avatar" src="https://i.imgur.com/sZjieuI.jpg" />
            </Grid>
            <ProfileHeading label={USER_INFORMATION_LABEL} />
            <AddContactButton onClick={this.handleAddingContact} contact={this.state.user.oidUser} curUser={this.props.store.get('authenticatedOidUser')} />
            {this.state.user.username && <ProfileField label={USER_NAME_LABEL} value={this.state.user.username} />}
            {this.state.user.email && <ProfileField label={USER_EMAIL_LABEL} value={this.state.user.email} />}
            {this.state.user.description && <ProfileField label={USER_BIO_LABEL} value={this.state.user.description} />}
            {fandomList.length > 0 && <ProfileHeading label={USER_FANDOMS_LABEL} />}
            {fandomList}
          </Grid>
        </Box>
        <ShowMessages open={this.state.notificationOpen} handleClose={this.handleClose} message={this.state.message} />
      </Paper>
    );
  }

}

// Generates each field which includes the label and the value
const ProfileField = (input) => {

  return (
    <Grid className="cldi-profile-field" item xs={12}>
      <TextField
        label={input.label}
        defaultValue={input.value}
        fullWidth
        multiline
        helperText={input.helperText}
        InputProps={{
          readOnly: true,
        }}
        InputLabelProps={{
          classes: {
            root: "cldi-profile-field-label"
          }
        }} />
    </Grid>
  );

};

// Generates a header for each section
const ProfileHeading = (input) => {

  return (
    <Grid className="cldi-profile-heading" item xs={12}>
      <Typography align="center" className="cldi-profile-heading-label" variant="h6">{input.label}</Typography>
      <Divider className="cldi-profile-heading-divider" />
    </Grid>
  );

}

function AddContactButton(props) {

  if (props.curUser === props.contact) {
    return (
      <Button
        variant="contained"
        color="primary"
        className="contact-button"
        onClick={props.onClick}
      >
        Add Contact
        </Button>
    )
  } else {
    return null
  }

}

function ShowMessages(props) {
  return (
    <Snackbar
      autoHideDuration={SNACKBAR_TIMEOUT}
      open={props.open}
      onClose={props.handleClose}
      TransitionComponent={Slide}

    >
      <SnackbarContent style={{
        backgroundColor: "red",
      }}
        message={props.message}
      />
    </Snackbar>
  )
}

export default ViewProfile;

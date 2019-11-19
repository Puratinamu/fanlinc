import React from 'react';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper'
import Avatar from '@material-ui/core/Avatar'
import TextField from '@material-ui/core/TextField'
import Divider from '@material-ui/core/Divider';
import Box from '@material-ui/core/Box';
import Zoom from '@material-ui/core/Zoom';
import CircularProgress from '@material-ui/core/CircularProgress';
import Typography from '@material-ui/core/Typography'
import IconButton from '@material-ui/core/IconButton'
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import Draggable from 'react-draggable';
import userRequests from '../../requests/userRequests';
import './styles.scss';

const USER_INFORMATION_LABEL = "User Information",
  USER_NAME_LABEL = "User Name",
  USER_EMAIL_LABEL = "Email",
  USER_BIO_LABEL = "Biography",
  USER_FANDOMS_LABEL = "Fandoms";


class ViewProfile extends React.Component {

  constructor(props) {
    super(props);

    this.store = props.store;

    this.state = {
      user: null,
      loading: true
    };
  }


  componentDidMount() {
    userRequests.getUser(this.store.get('authenticatedOidUser')).then(response => {
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

  routeToJoinFandom() {
    this.props.history.push('/main/joinfandom');
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
    // TODO: Must not render add fandom bunton when not the current user
    return (
      <Zoom in={!this.state.loading}>

        <Paper>
          <Box px={3} pt={5} pb={10} >
            <Grid spacing={2} className="cldi-view-user-profile" container direction="column" alignItems="center">
              <Grid item xs={12}>
                <Avatar className="cldi-profile-avatar" alt="Avatar" src="https://i.imgur.com/sZjieuI.jpg" />
              </Grid>
              <ProfileHeading label={USER_INFORMATION_LABEL} />
              {this.state.user.username && <ProfileField label={USER_NAME_LABEL} value={this.state.user.username} />}
              {this.state.user.email && <ProfileField label={USER_EMAIL_LABEL} value={this.state.user.email} />}
              {this.state.user.description && <ProfileField label={USER_BIO_LABEL} value={this.state.user.description} />}
              {fandomList.length >= 0 && (
                <ProfileHeading label={USER_FANDOMS_LABEL}>
                  <IconButton
                    onClick={this.routeToJoinFandom.bind(this)}
                    className="cldi-profile-fandom-add-button"
                    disableRipple
                    disableFocusRipple
                    style={{ backgroundColor: 'transparent' }}>
                    <AddCircleOutlineIcon />
                  </IconButton>
                </ProfileHeading>
              )}
              {fandomList}
            </Grid>
          </Box>

        </Paper>
      </Zoom>
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
      <Typography align="center" className="cldi-profile-heading-label" variant="h6">{input.label}{input.children}</Typography>
      <Divider className="cldi-profile-heading-divider" />
    </Grid>
  );

}

export default ViewProfile;


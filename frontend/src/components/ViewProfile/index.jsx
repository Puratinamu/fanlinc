import React from 'react';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper'
import Avatar from '@material-ui/core/Avatar'
import TextField from '@material-ui/core/TextField'
import Divider from '@material-ui/core/Divider';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography'
import './styles.scss';

const USER_INFORMATION_LABEL = "User Information",
    USER_NAME_LABEL = "User Name",
    USER_EMAIL_LABEL = "Email",
    USER_BIO_LABEL = "Biography",
    USER_FANDOMS_LABEL = "Fandoms";


class ViewProfile extends React.Component {

    constructor(input) {
        super(input);

        this.username = input.username;
        this.email = input.email;
        this.description = input.description;
        this.fandoms = input.fandoms;
    }

    render() {
        // Render list of fandoms
        let fandomList = [];
        for (let fandom of this.fandoms) {
            fandomList.push(
                <ProfileField
                  key={fandom.oidFandom}
                  label="Name"
                  value={fandom.name}
                  helperText={`Interest Level: ${fandom.level}`} />
            )
        }

        return (
          <Paper>
            <Box px={3} py={5}>
              <Grid spacing={2} className="cldi-view-user-profile" container direction="column" alignItems="center">
                <Grid item xs={12}>
                  <Avatar className="cldi-profile-avatar" alt="Avatar" src="https://i.imgur.com/sZjieuI.jpg" />
                </Grid>
                <ProfileHeading label={USER_INFORMATION_LABEL}/>
                <ProfileField label={USER_NAME_LABEL} value={this.username} />
                <ProfileField label={USER_EMAIL_LABEL} value={this.email} />
                <ProfileField label={USER_BIO_LABEL} value={this.description} />
                {fandomList.length > 0 &&
                  <ProfileHeading label={USER_FANDOMS_LABEL} />
                }
                {fandomList}
              </Grid>
            </Box>
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
          }}/>
      </Grid>
    );

};

// Generates a header for each section
const ProfileHeading = (input) => {

    return (
      <Grid className="cldi-profile-heading" item xs={12}>
        <Typography align="center" className="cldi-profile-heading-label" variant="h6">{input.label}</Typography>
        <Divider className="cldi-profile-heading-divider"/>
      </Grid>
    );

}

export default ViewProfile;


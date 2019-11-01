import React from 'react';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper'
import Avatar from '@material-ui/core/Avatar'
import TextField from '@material-ui/core/TextField'
import Divider from '@material-ui/core/Divider';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography'

import './styles.scss';

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
                <ProfileField key={fandom.oidFandom} label="Name" value={fandom.name} helperText={fandom.level} />
            )
        }

        return (
          <Paper>
            <Box p={3}>
              <Grid spacing={2} className="cldi-view-user-profile" container direction="column" align="center">
                <Grid item xs={12}>
                  <Avatar className="cldi-profile-avatar" alt="Avatar" src="https://i.imgur.com/sZjieuI.jpg" />
                </Grid>
                <ProfileHeading label="User Information" />
                <ProfileField label="User Name" value={this.username} />
                <ProfileField label="Email" value={this.email} />
                <ProfileField label="Description" value={this.description} />
                <ProfileHeading label="Fandoms" />
                {fandomList}
              </Grid>
            </Box>
          </Paper>
        );
    }

}

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
            }}/>
        </Grid>
    );

};

const ProfileHeading = (input) => {

    return (
      <Grid item xs={12}>
        <Typography className="cldi-profile-heading" variant="h6">{input.label}</Typography>
        <Divider/>
      </Grid>
    );
}

export default ViewProfile;


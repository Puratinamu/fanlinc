import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';

import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import Avatar from '@material-ui/core/Avatar';


const useStyles = makeStyles(theme => ({
  button: {
    margin: theme.spacing(1),

  },
  input: {
    display: 'none',
  },
  bigAvatar: {
    margin: 10,
    width: 200,
    height: 200,
  }
}));

export default function RegistrationForm2() {
  const classes = useStyles();

  return (

      <Box textAlign="center">
        <Box display="flex" justifyContent="center">
          <Avatar alt="Avatar" src="https://i.imgur.com/sZjieuI.jpg" className={classes.bigAvatar} />
        </Box>
        <Box >
          <input
            accept="image/*"
            className={classes.input}
            id="contained-button-file"
            multiple
            type="file"
          />
          <label htmlFor="contained-button-file">
            <Button variant="contained" color="primary" className={classes.button} startIcon={<CloudUploadIcon />}  component="span">
              Upload an Image
            </Button>
          </label>
        </Box>
        <Box>
          <Button variant="contained" color="primary" className={classes.button} startIcon={<AccountCircleIcon />}>
            Choose From Preset

          </Button>
        </Box>
      </Box>
  );
}
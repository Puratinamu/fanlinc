import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';


const useStyles = makeStyles(theme => ({
  button: {
    margin: theme.spacing(1),
  
  },
  input: {
    display: 'none',
  },
}));

export default function RegistrationForm2() {
  const classes = useStyles();

  return (
    <Box textAlign="center">
      <div>
        <Button variant="contained" color="primary" className={classes.button}>
          Upload an Image
      </Button>
      </div>
      <div>
        <Button variant="contained" color="primary" className={classes.button}>
          Choose From Preset
        </Button>
      </div>
    </Box> 
  );
}
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';
import { Box } from '@material-ui/core';

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    justifyContent: 'center'
    
  },
}));

export default function Loading() {
  const classes = useStyles();

  return (
    <Box p={1} className={classes.root}>
      <CircularProgress />
    </Box>
  );
}
import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import InputBase from '@material-ui/core/InputBase';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import Box from '@material-ui/core/Box';
import fandomRequests from '../../requests/fandomRequests'
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles(theme => ({
  root: {
    padding: theme.spacing(1),
    display: 'flex',
    alignItems: 'center',
    width: 400,
    margin: 'auto'
  },
  input: {
    marginLeft: theme.spacing(1),
    flex: 1,
  },
  iconButton: {
    padding: 10,
  },
  divider: {
    height: 28,
    margin: 4,
  },
  paperRoot:{
    padding: theme.spacing(3, 1),
    margin: theme.spacing(3,2),
    marginTop: theme.spacing(1),
    width:400,
    margin: 'auto'
  }

}));

export default function RegistrationForm3() {
  const classes = useStyles();
  const [searchTerm, setSearchTerm] = useState("");

  const fandomSearch = async function () {
    let fandom = await fandomRequests.listFandoms(searchTerm);
    console.log(fandom)
  };
  const handleSearchBarUpdate = (e) => {
    setSearchTerm(e.target.value)
  };

  return (
    <Box>
      <Box  >
        <Paper className={classes.root}>
          <InputBase
            className={classes.input}
            placeholder="Search for your Fandoms"
            inputProps={{ 'aria-label': 'search for fandoms' }}
            onChange={handleSearchBarUpdate}
          />
          <IconButton onClick={fandomSearch} className={classes.iconButton} aria-label="search">
            <SearchIcon />
          </IconButton>
        </Paper>
      </Box>
      <Box >
        <Paper className={classes.paperRoot}>
          <Typography variant="h6" component="h6">
            LOL
        </Typography>
          <Typography component="p">
            League Of Legends Fandom
        </Typography>
        </Paper>
      </Box>
    </Box>
  )
};
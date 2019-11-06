import React from 'react';
import CssBaseline from '@material-ui/core/CssBaseline'
import AppBar from '@material-ui/core/AppBar'
import Typography from '@material-ui/core/Typography'
import Toolbar from '@material-ui/core/Toolbar'
import Divider from '@material-ui/core/Divider';
import Box from '@material-ui/core/Box';
import Grid from '@material-ui/core/Grid';


require('./styles.scss')

class Messenger extends React.Component {
    constructor() {
        super()
    }

    render() {
        return (
            <div>

                <CssBaseline />
                <AppBar position="fixed" >
                    <Toolbar >
                        <Grid display="flex" className="messenger-header-main">
                            <Box className="messenger-header-1" >
                                <Typography variant="h6" noWrap className="messenger-header-1-text" >
                                    Chats
                                </Typography>
                            </Box>
                            <Box className="messenger-header-divider">
                                <Divider
                                    orientation="vertical"
                                    
                                    light
                                />
                            </Box>
                            <Box className="messenger-header-2">
                                <Typography variant="h6" className="messenger-header-2-text" noWrap >
                                    Conversation With
                                </Typography>
                                <Typography variant="h6" className="messenger-header-name-" noWrap >
                                    Nicholas Wong
                                </Typography>
                            </Box>

                        </Grid>
                    </Toolbar>
                </AppBar>
            </div>
        )
    }
}

export default Messenger
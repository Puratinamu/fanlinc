import React from 'react';
import MessageHolder from '../MessageHolder';
import MessageComposer from '../MessageComposer'
import Paper from '@material-ui/core/Paper'
import Box from '@material-ui/core/Box'
import { Typography } from '@material-ui/core';

require('./styles.scss')

class MessengerMain extends React.Component {

    render() {
        return (
            <Box className="messenger-main">
                <Paper className="messenger-header">
                    <Box p={2} >
                        <Typography variant="h6">
                            Your Chat With Nicholas Wong
                    </Typography>
                    </Box>
                </Paper>
                <Box pt={2} pr={2} pb={2}>
                    <Paper >
                        <Box p={2} className="messenger-chat-holder">
                            <MessageHolder store={this.props.store}/>
                            <MessageComposer store={this.props.store} className="message-composer"/>
                        </Box>
                    </Paper>
                </Box>

            </Box>
        )
    }
}
export default MessengerMain
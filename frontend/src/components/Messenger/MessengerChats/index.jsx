import React from 'react';
import { Typography, Paper, Box } from '@material-ui/core';
import MessengerContacts from '../MessengerContacts'
import MessengerChatSearch from '../MessengerChatSearch';
import MessengerFandoms from '../MessengerFandoms';

require('./styles.scss')


class MessengerChats extends React.Component {
    render() {
        return (
            <Box className="messenger-chats-view">
                <Paper className="messenger-header" >
                    <Box p={2}>
                        <Typography variant="h4">
                            Chats
                        </Typography>
                    </Box>
                </Paper>
                <Box  pt={2} pl={2}>
                    <Paper >
                        <Box className="messenger-chat" >
                            <MessengerChatSearch store={this.props.store}/>
                            <MessengerContacts store={this.props.store}/>
                            <MessengerFandoms store={this.props.store}/>
                        </Box>
                    </Paper>
                </Box>
            </Box>

        )
    }
}
export default MessengerChats
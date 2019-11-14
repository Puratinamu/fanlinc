import React from 'react';
import { Typography, Paper, Box } from '@material-ui/core';
import MessengerContacts from '../MessengerContacts'
import MessengerChatSearch from '../MessengerChatSearch';
import MessengerFandoms from '../MessengerFandoms';

require('./styles.scss')


class MessengerChats extends React.Component {
    constructor(props){
        super(props);
        this.handleOptionSelect = this.handleOptionSelect.bind(this);
        this.state = {
            selectedFromContacts:false,
            selectedContact: 0,
            selectedFromFandoms:true,
            selectedFandom: 0
        }
    }

    handleOptionSelect(from, index){
        if(from === "contacts"){
            this.setState({
                selectedFromContacts:true,
                selectedFromFandoms:false,
                selectedContact:index
            })
        } else if(from === "fandoms"){
            this.setState({
                selectedFromFandoms:true,
                selectedFromContacts:false,
                selectedFandom:index
            })
        }
    }

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
                            <MessengerContacts callback={this.props.callback} selectedCallback={this.handleOptionSelect} hasSelected={this.state.selectedFromContacts} selected={this.state.selectedContact} store={this.props.store}/>
                            <MessengerFandoms callback={this.props.callback} selectedCallback={this.handleOptionSelect} hasSelected={this.state.selectedFromFandoms} selected={this.state.selectedFandom} store={this.props.store}/>
                        </Box>
                    </Paper>
                </Box>
            </Box>

        )
    }
}
export default MessengerChats
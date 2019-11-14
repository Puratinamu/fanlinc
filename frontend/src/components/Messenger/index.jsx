import React from 'react';
import MessengerChats from './MessengerChats'
import MessengerMain from './MessengerMain'
import { Box } from '@material-ui/core';
require('./styles.scss')

class Messenger extends React.Component {
    constructor() {
        super();
        this.handleNewSelectedChat = this.handleNewSelectedChat.bind(this);
        this.state = {
            fandomId: -1,
            fandomInterestLevel: ""
        }
    }
    handleNewSelectedChat(fandomId, fandomInterestLevel) {
        this.setState({
            fandomId: fandomId,
            fandomInterestLevel: fandomInterestLevel
        })
    }
    render() {
        return (
            <Box display="flex">
                <MessengerChats callback={this.handleNewSelectedChat} store={this.props.store} />
                <MessengerMain fandomId={this.state.fandomId} fandomInterestLevel={this.state.fandomInterestLevel} store={this.props.store} />
            </Box>
        )
    }
}


export default Messenger
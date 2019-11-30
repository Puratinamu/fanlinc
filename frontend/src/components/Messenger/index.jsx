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
            fandomInterestLevel: "",
            chattingWith:""
        }
    }
    handleNewSelectedChat(fandomId, fandomInterestLevel, chattingWith) {
        this.setState({
            fandomId: fandomId,
            fandomInterestLevel: fandomInterestLevel,
            chattingWith: chattingWith
        })
    }
    render() {
        return (
            <Box className="messenger">
                <MessengerChats className="messenger-chat" callback={this.handleNewSelectedChat} store={this.props.store} />
                <MessengerMain className="messenger-main" chattingWith={this.state.chattingWith} fandomId={this.state.fandomId} fandomInterestLevel={this.state.fandomInterestLevel} store={this.props.store} />
            </Box>
        )
    }
}


export default Messenger
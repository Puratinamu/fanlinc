import React from 'react';
import MessageHolder from '../MessageHolder';
import MessageComposer from '../MessageComposer'
import Paper from '@material-ui/core/Paper'
import Box from '@material-ui/core/Box'
import { Typography } from '@material-ui/core';
import chatRequests from '../../../requests/chatRequests';
let alertsound = require('../../../assets/alert.mp3')


require('./styles.scss')

class MessengerMain extends React.Component {
    constructor(props) {
        super(props);
        this.handleMadePost = this.handleMadePost.bind(this);
        this.getMessages = this.getMessages.bind(this)

        this.state = {
            messages: [],
            fandomId: props.fandomId,
            chattingWith: props.chattingWith,
            fandomInterestLevel: props.fandomInterestLevel,
            loading: true
        }
        this.audio = new Audio(alertsound);
    }

    handleMadePost(post) {
        let messages = this.state.messages;
        messages.push(post);
        this.setState(messages);
    }
    componentDidUpdate() {
        if (this.props.fandomId !== this.state.fandomId) {
            this.setState({ fandomId: this.props.fandomId, fandomInterestLevel: this.props.fandomInterestLevel, chattingWith: this.props.chattingWith }, this.getMessages);
        }

    }
    getMessages() {
        chatRequests.getChatMessagesForFandom(this.state.fandomId, this.state.fandomInterestLevel).then(response => {
            let newMessages = [];

            if (response.status === 200) {
                newMessages = response.data.messages.reverse();
            }
            
            if (this.state.messages.length !== 0 && newMessages.length != 0) {
                if (newMessages[newMessages.length - 1].msgId !== this.state.messages[this.state.messages.length - 1].msgId && newMessages[newMessages.length - 1].fromId != this.props.store.get("authenticatedOidUser")) {
                    this.audio.play()
                }
            }
            this.setState({
                messages: newMessages,
                loading: false
            });

        });
    }
    componentDidMount() {
        this.interval = setInterval(() => {
            this.getMessages();
        }, 500);
    }
    render() {
        return (
            <Box className="messenger-main">
                <Paper className="messenger-header">
                    <Box p={2} >
                        <Typography variant="h6">
                            {this.state.chattingWith}
                        </Typography>
                    </Box>
                </Paper>
                <Box pt={2} pr={2} pb={2}>
                    <Paper >
                        <Box p={2} className="messenger-chat-holder">
                            <MessageHolder store={this.props.store} messages={this.state.messages} loading={this.state.loading} />
                            <MessageComposer store={this.props.store} fandomId={this.props.fandomId} fandomInterestLevel={this.props.fandomInterestLevel} madePostCallBack={this.handleMadePost} className="message-composer" />
                        </Box>
                    </Paper>
                </Box>

            </Box>
        )
    }
}
export default MessengerMain
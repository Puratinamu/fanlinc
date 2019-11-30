import React from 'react';
import MessageHolder from '../MessageHolder';
import MessageComposer from '../MessageComposer'
import Paper from '@material-ui/core/Paper'
import Box from '@material-ui/core/Box'
import chatRequests from '../../../requests/chatRequests';


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

            if (response && response.status === 200) {
                newMessages = response.data.messages.reverse();
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
                <Box p={1}>
                    <Paper >
                        <Box pt={1} pl={1} pr={1} className="messenger-chat-holder">
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
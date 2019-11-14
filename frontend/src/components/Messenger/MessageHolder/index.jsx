import React from 'react';
import MessageBubble from '../MessageBubble'
import Loading from '../../core/Loading'
import chatRequests from '../../../requests/chatRequests';

require('./styles.scss')

class MessageHolder extends React.Component {
    constructor(props) {
        super(props)
        this.store = props.store;

        this.state = {
            loading: true,
            messages : []
        }
    }
    componentDidMount(){
        chatRequests.getChatMessagesForFandom(87, "CASUAL").then(response => {
            let messages;

            if (response.status === 200) {
                messages = response.data.messages;
                console.log(messages)
            }

            this.setState({
                messages: messages,
                loading: false
            });
        });
    }

    render() {
        return (

            <div className="container">
                {!this.state.loading && this.state.messages.reverse().map((message) => 
                    <MessageBubble
                        key = {message.createdTimeStamp}
                        value={message.content}
                        sender={message.fromId == this.store.get("authenticatedOidUser") ? "me" : message.fromId}
                    />)
                }
                {this.state.loading &&
                    <Loading />
                }
            </div>

        )
    }
}

export default MessageHolder
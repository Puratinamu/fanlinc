import React from 'react';
import MessageBubble from '../MessageBubble'
import Loading from '../../core/Loading'
import ScrollToBottom from 'react-scroll-to-bottom';

require('./styles.scss')

class MessageHolder extends React.Component {
    constructor(props) {
        super(props)
        this.store = props.store;
        this.state = {
            messages : this.props.messages
        }
    }
  
    render() {
        return (

            <ScrollToBottom  className="container">
                {!this.props.loading && this.props.messages.map((message, index) => 
                    <MessageBubble
                        key = {index}
                        value={message.content}
                        sender={message.fromId == this.store.get("authenticatedOidUser") ? "me" : message.fromUsername}
                     />)
                }
                {this.props.loading &&
                    <Loading />
                }
            </ScrollToBottom >

        )
    }
}

export default MessageHolder
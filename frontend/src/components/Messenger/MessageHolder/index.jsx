import React from 'react';
import MessageBubble from '../MessageBubble'
require('./styles.scss')

class MessageHolder extends React.Component {

    render() {
        return (
            <div className="container">
                <MessageBubble
                    value="Hi "
                    sender="me"
                />
                <MessageBubble
                    value="Reeeee"
                    sender="nick"
                />
                <MessageBubble
                    value="how's it going?"
                    sender="me"
                />
                <MessageBubble
                    value="what a good meme"
                    sender="me"
                />
                <MessageBubble
                    value="Reeeee"
                    sender="user1"
                />
                 <MessageBubble
                    value="OH GOD THE HUMANITY"
                    sender="user2"
                />

            </div>
        )
    }
}

export default MessageHolder
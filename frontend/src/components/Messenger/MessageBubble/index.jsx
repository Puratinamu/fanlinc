import React from 'react';
require('./styles.scss')

class MessageBubble extends React.Component {
    constructor(props) {
        super()
        this.state = {
            sender: props.sender,
            value: props.value
        }
    }

    render() {
        return (
            <div>
                <div className={`${'name-container'} ${this.props.sender === "me" ? 'me' : 'other'}`}>
                {this.props.sender !== "me" &&
                    (
                        <div className="chat-bubble-sender">{this.props.sender}</div>
                    )
                }
                </div>
                <div className={`${'chat-bubble-container'} ${this.props.sender === "me" ? 'me' : 'other'}`} >

                    <div className={`${'chat-bubble'}  ${this.props.sender === "me" ? 'chat-bubble-me' : 'chat-bubble-other'}`}>
                        {this.props.value}
                    </div>
                </div>
            </div>
        )
    }
}
export default MessageBubble
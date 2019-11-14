import React from 'react';
import Box from '@material-ui/core/Box';
import TextField from '@material-ui/core/TextField';
import SendIcon from '@material-ui/icons/Send';
import { IconButton } from '@material-ui/core';
import EmojiSelector from '../EmojiSelector';
import chatRequests from '../../../requests/chatRequests';

require('./styles.scss')

class MessageComposer extends React.Component {
    constructor(props) {
        super(props)
        this.handleEmoji = this.handleEmoji.bind(this);
        this.handlePost = this.handlePost.bind(this);
        this.handleInput = this.handleInput.bind(this);
        this.handleKeyPress = this.handleKeyPress.bind(this);

        this.state = {
            value: ""
        }
    }
    handleEmoji(e, data) {
        let newValue = this.state.value + data.emoji
        this.setState({ value: newValue })
    }
    handleInput(e) {
        this.setState({ value: e.target.value })
    }
    handlePost() {
        if (this.state.value !== "") {
            
            chatRequests.postMessageToFandom(this.props.fandomId, this.props.fandomInterestLevel, this.props.store.get("authenticatedOidUser"), this.state.value)
                .then((response) => {
                    if (response.status === 200) {
                        this.props.madePostCallBack(response.data);
                        this.setState({value:""})
                    } else {
                        alert("failed to make post");
                    }
                })
        }
    }
    handleKeyPress(e){
        if (e.key === 'Enter') {
            this.handlePost();
          }
    }

    render() {
        return (
            <Box className="message-composer-holder" >

                <TextField
                    id="message-composer"
                    margin="normal"
                    variant="outlined"
                    placeholder="Enter a message..."
                    className="message-composer-input"
                    onKeyPress={this.handleKeyPress}
                    onInput={this.handleInput}
                    value={this.state.value}
                />
                <EmojiSelector callback={this.handleEmoji} />
                <IconButton onClick={this.handlePost} >
                    <SendIcon color="primary" fontSize="large" className="send-button"  />
                    
                </IconButton>
            </Box>

        )
    }
}

export default MessageComposer
import React from 'react';
import Box from '@material-ui/core/Box';
import TextField from '@material-ui/core/TextField';
import SendIcon from '@material-ui/icons/Send';
import { IconButton } from '@material-ui/core';
import EmojiSelector from '../EmojiSelector'
require('./styles.scss')

class MessageComposer extends React.Component {
    constructor() {
        super()
        this.handleEmoji = this.handleEmoji.bind(this);
        this.handleInput = this.handleInput.bind(this);

        this.state={
            value:""
        }
    }
    handleEmoji(e, data){
        let newValue = this.state.value+"\0"+e
        console.log(e,data)
        this.setState({value:newValue})
    }
    handleInput(e){
        this.setState({value:e.target.value})
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
                    value={this.state.value}
                    onInput={this.handleInput}
                />
                <EmojiSelector callback={this.handleEmoji}/>
                <IconButton>
                    <SendIcon color="primary" fontSize="large" className="send-button" />
                </IconButton>
            </Box>

        )
    }
}

export default MessageComposer
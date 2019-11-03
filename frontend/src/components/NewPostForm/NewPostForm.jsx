import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
require('./NewPostForm.scss')

class NewPostForm extends React.Component {
    constructor(props) {
        super()
        this.handlePostAttempt = this.handlePostAttempt.bind(this);
        this.handlePostInput = this.handlePostInput.bind(this);

        this.state = {
            fandom: "",
            postText: "",
            fandomMissingError: false,
            postTextMissingError: false,
            postFailUnauthorizedError: false,
            postFailInternalServerError: false,
            postFailBadRequestError: false,
            postSuccess: false
        }
    }

    handlePostAttempt(newPost) {
        this.setState({ postTextMissingError: this.state.postText === "" })
    }

    handlePostInput(newPost) {
        this.setState({ postText: newPost.target.value, postTextMissingError: false })
    }

    renderErrorMessages() {
        if (this.state.postTextMissingError) {
            return (
                <div> Please type a post!</div>
            )
        }
    }

    render() {
        return (
            <Box>
                <Box>
                    <PostField onInput={this.handlePostInput} error={this.state.postTextMissingError} />
                </Box>
                <Box>
                    <PostButton onClick={this.handlePostAttempt} />
                </Box>
                <Box>{this.renderErrorMessages()}</Box>

            </Box>
        )
    }
}

function PostField(props) {

    return (<TextField
        id="standard-multiline-static"
        required
        error={props.error}
        label="Your Post"
        multiline
        rows="4"
        placeholder="Say Something......."
        className="post-text-box"
        margin="normal"
        variant="outlined"
        onInput={props.onInput}
    />)
}

function PostButton(props) {
    return (
        <Button
            variant="contained"
            color="primary"
            className="post-button"
            onClick={props.onClick}

        >
            Post
        </Button>
    )
}

export default NewPostForm
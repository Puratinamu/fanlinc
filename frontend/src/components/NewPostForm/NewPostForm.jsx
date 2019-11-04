import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import textPostRequests from '../../requests/textPostRequests';
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
        textPostRequests.putTextPost({
            "oidCreator": "",
            "text": this.state.postText,
            "oidFandom": "",
        }).then(response => {
            if (response.status === 200) {
                //success
            } else if (response.status === 500) {
                this.setState({ postFailInternalServerError: true })
            } else if (response.status === 400) {
                this.setState({ postFailBadRequestError: true })
            }
        })
    }

    handlePostInput(newPost) {
        this.setState({ postText: newPost.target.value, postTextMissingError: false })
    }

    renderErrorMessage() {
        if (this.state.postTextMissingError || this.state.fandomMissingError) {
            return (
                <div> Please enter the required fields!</div>
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
                <Box>{this.renderErrorMessage()}</Box>

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
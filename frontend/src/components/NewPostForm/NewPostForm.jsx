import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import textPostRequests from '../../requests/textPostRequests';
import { Redirect } from 'react-router-dom'
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
            postTextMissingError: true,
            postFailUnauthorizedError: false,
            postFailInternalServerError: false,
            postFailBadRequestError: false,
            unknownError: false,
            postSuccess: false
        }
    }

    checkPostSuccess() {
        if (this.state.postSuccess) {
            return <Redirect to='/' />;
        }
    }

    postAttempt() {
        textPostRequests.putTextPost({
            "oidCreator": "",
            "text": this.state.postText,
            "oidFandom": ""
        }).then(response => {
            if (response.status === 200) {
                this.setState({ postSuccess: true })
            } else if (response.status === 500) {
                this.setState({ postFailInternalServerError: true })
            } else if (response.status === 400) {
                this.setState({ postFailBadRequestError: true })
            }
            else {
                this.setState({ unknownError: true })
            }
        })
    }

    handlePostAttempt(newPost) {
        this.setState({ postTextMissingError: this.state.postText === "" })
        this.postAttempt()
    }

    handlePostInput(newPost) {
        this.setState({ postText: newPost.target.value, postTextMissingError: false, unknownError: false })
    }

    renderErrorMessage() {
        if (this.state.postTextMissingError || this.state.fandomMissingError) {
            return (
                <div> Please enter the required fields!</div>
            )
        }
        else if (this.state.postFailInternalServerError) {
            return (
                <div> Internal Server Error</div>
            )
        }
        else if (this.state.postFailBadRequestError) {
            return (
                <div> Bad Request Error</div>
            )
        }
        else if (this.state.unknownError) {
            return (
                <div> An unknown error has occured</div>
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
                    <PostButton error={this.state.postTextMissingError} onClick={this.handlePostAttempt} />
                </Box>
                <Box>{this.renderErrorMessage()}</Box>
                {this.checkPostSuccess()}
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
            disabled={props.error}
        >
            Post
        </Button>
    )
}

export default NewPostForm
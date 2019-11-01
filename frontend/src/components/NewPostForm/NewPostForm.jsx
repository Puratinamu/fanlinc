import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
require('./NewPostForm.scss')

class NewPostForm extends React.Component {
    constructor(props) {
        super()
        this.handlePostAttempt = this.handlePostAttempt.bind(this);
        this.handlePostInput = this.handlePostAttempt.bind(this);

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

    handlePostAttempt() {}

    handlePostInput() {

    }
    

    render() {
        return (
            <Box>
                <Box>
                    <PostField />
                </Box>
                <Box>
                    <PostButton />
                </Box>

            </Box>
        )
    }
}

function PostField(props) {

    return (<TextField
        id="standard-multiline-static"
        label="Your Post"
        multiline
        rows="4"
        placeholder="Say Something......."
        className="post-text-box"
        margin="normal"
        variant="outlined"
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
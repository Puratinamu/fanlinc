import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import textPostRequests from '../../requests/textPostRequests';
import { Redirect } from 'react-router-dom'
import Paper from '@material-ui/core/Paper';
import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container'
import SearchField from '../core/searchfield'
import fandomRequest from '../../requests/fandomRequests';

require('./NewPostForm.scss')

class NewPostForm extends React.Component {
    constructor(props) {
        super()
        this.handlePostAttempt = this.handlePostAttempt.bind(this);
        this.setSelectedFandom = this.setSelectedFandom.bind(this);
        this.createFandomOptions = this.createFandomOptions.bind(this);
        this.handlePostInput = this.handlePostInput.bind(this);

        this.state = {
            fandom: "",
            postText: "",
            fandomMissingError: false,
            postTextMissingError: false,
            postFailUnauthorizedError: false,
            postFailInternalServerError: false,
            postFailBadRequestError: false,
            unknownError: false,
            postSuccess: false,
            selectedFandom: "",
            fandomSelected: false,
            fandomsList: [],
            loading:true
        }
    }

    checkPostSuccess() {
        if (this.state.postSuccess) {
            return <Redirect to='/' />;
        }
    }
    componentDidMount() {
        // Get the list of all fandoms
        fandomRequest.getAllFandoms().then(response => {
            let newFandomsList = [];

            if (response.status === 200) {
                newFandomsList = this.createFandomOptions(response.data);
                console.log(response.data)
            }
            this.setState({
                fandomsList: newFandomsList,
                loading:false
            });
        })
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
    createFandomOptions(fandoms) {
        let options = [];

        for (let i = 0; i < fandoms.length; i++) {
            if (fandoms[i]) {
                options.push({
                    value: `${fandoms[i].oidFandom}`,
                    label: `${fandoms[i].name}`,
                    data: fandoms[i]
                });
            }
        }

        return options;
    }

    handlePostAttempt(newPost) {
        this.setState({ postTextMissingError: this.state.postText === "" })
        this.postAttempt()
    }

    handlePostInput(newPost) {
        this.setState({ postText: newPost.target.value, postTextMissingError: newPost.target.value === "", unknownError: false })
    }

    renderErrorMessage() {
        if (this.state.postTextMissingError || this.state.fandomMissingError) {
            return (
                <Typography> Please enter the required fields!</Typography>
            )
        }
        else if (this.state.postFailInternalServerError) {
            return (
                <Typography> Internal Server Error</Typography>
            )
        }
        else if (this.state.postFailBadRequestError) {
            return (
                <Typography> Bad Request Error</Typography>
            )
        }
        else if (this.state.unknownError) {
            return (
                <Typography> An unknown error has occured</Typography>
            )
        }
    }

    setSelectedFandom(selection) {
        this.setState({
            selectedFandom: selection.data,
            fandomSelected: true
        });
    }
    componentWillUpdate(input) {
        this.children = input.children;
    }
    render() {
        return (
            <Container maxWidth="md">
                <Paper className="cldi-make-post-main" >
                    <Typography variant="h6">
                        Make a Post
                      </Typography>
                    <Divider />

                    <Box className="text-holder">
                        <Box >
                            {!this.state.loading &&
                                (
                                    <SearchField
                                        callback={this.setSelectedFandom}
                                        placeHolder="Search A Fandom"
                                        searchList={this.state.fandomsList} />
                                )
                            }
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
                        </Box>
                    </Box>
                </Paper >
            </Container >
        )
    }
}

function PostField(props) {

    return (<TextField
        id="standard-multiline-static"
        required
        error={props.error}
        multiline
        rows="4"
        placeholder="Your Post"
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
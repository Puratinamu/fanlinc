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
import userRequests from '../../requests/userRequests';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import Slide from '@material-ui/core/Slide'
import { withStore } from '../../store'

require('./NewPostForm.scss')

const SNACKBAR_TIMEOUT = 4000;

class NewPostForm extends React.Component {
    constructor(props) {
        super(props)
        this.handlePostAttempt = this.handlePostAttempt.bind(this);
        this.setSelectedFandom = this.setSelectedFandom.bind(this);
        this.createFandomOptions = this.createFandomOptions.bind(this);
        this.handlePostInput = this.handlePostInput.bind(this);

        this.handleClose = this.handleClose.bind(this);

        this.state = {
            postText: "",
            postTextMissingError: false,
            postFailUnauthorizedError: false,
            postFailInternalServerError: false,
            postFailBadRequestError: false,
            unknownError: false,
            postSuccess: false,
            selectedFandom: null,
            fandomSelected: false,
            fandomsList: [],
            notificationOpen: false,
            loading: true,
            message: "Unknown Error: Please contact support"
        }
    }

    async postAttempt() {
        textPostRequests.putTextPost({
            "oidCreator": this.props.store.get("authenticatedOidUser"),
            "text": this.state.postText,
            "oidFandom": this.state.selectedFandom
        }).then(response => {
            if (response.status === 200) {
                this.setState({ postSuccess: true, message: "Your post has been successfully added!", notificationOpen: true })
            } else if (response.status === 500) {
                this.setState({ postFailInternalServerError: true, message: "Internal server error: Please contact support", notificationOpen: true })
            } else if (response.status === 400) {
                this.setState({ postFailBadRequestError: true, message: "Bad request error: Please contact support", notificationOpen: true })
            }
            else {
                this.setState({ unknownError: true, message: "Unknown Error: Please contact support", notificationOpen: true })
            }
        })
    }
    checkPostSuccess() {
        if (this.state.postSuccess) {
            return <Redirect to='/' />;
        }
    }
    handleClose() {
        this.setState({
            notificationOpen: false
        });
    }
    handlePostInput(newPost) {
        this.setState({ postText: newPost.target.value, postTextMissingError: newPost.target.value === "", unknownError: false })
    }
    handlePostAttempt(newPost) {
        this.setState({ postTextMissingError: this.state.postText === "" })
        if (this.state.postTextMissingError || this.state.postText === "") {
            this.setState({ message: "Please enter a post", notificationOpen: true })
        } else if (!this.state.fandomSelected) {
            this.setState({ message: "Please select a fandom", notificationOpen: true })
        } else {
            this.postAttempt()
        }

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
    setSelectedFandom(selection) {
        this.setState({
            selectedFandom: selection.data,
            fandomSelected: true
        });
    }
    componentWillUpdate(input) {
        this.children = input.children;
    }
    componentDidMount() {
        // Get the user
        userRequests.getUser(this.props.store.get("authenticatedOidUser")).then(response => {
            let newFandomsList = [];

            if (response.status === 200) {
                newFandomsList = this.createFandomOptions(response.fandoms);
                console.log(response.data)
            } else if (response.status === 500) {
                this.setState({ postFailInternalServerError: true, message: "Internal server error generating fandoms: Please contact support", notificationOpen: true })
            } else if (response.status === 400) {
                this.setState({ postFailBadRequestError: true, message: "Bad request error generating fandoms: Please contact support", notificationOpen: true })
            } else if (response.status === 404) {
                this.setState({ postFailUnauthorizedError: true, message: "Unauthorized user error generating fandoms: Please contact support", notificationOpen: true })
            } else {
                this.setState({ unknownError: true, message: "Unknown error generating fandoms: Please contact support", notificationOpen: true })
            }
            this.setState({
                fandomsList: newFandomsList,
                loading: false
            });
        })
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
                            <SelectFandom loading={this.state.loading} callback={this.setSelectedFandom} searchList={this.state.fandomsList} />
                            <Box>
                                <Box>
                                    <PostField onInput={this.handlePostInput} error={this.state.postTextMissingError} />
                                </Box>
                                <Box>
                                    <PostButton error={this.state.postTextMissingError} onClick={this.handlePostAttempt} />
                                </Box>
                                <ShowMessages open={this.state.notificationOpen} handleClose={this.handleClose} postSuccess={this.state.postSuccess} message={this.state.message} />
                                {this.checkPostSuccess()}
                            </Box>
                        </Box>
                    </Box>
                </Paper >
            </Container >
        )
    }
}

function SelectFandom(props) {
    return (!props.loading &&
        (
            <SearchField
                callback={props.callback}
                placeHolder="Search A Fandom"
                searchList={props.searchList} />
        )
    )
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

function ShowMessages(props) {
    return (
        <Snackbar
            autoHideDuration={SNACKBAR_TIMEOUT}
            open={props.open}
            onClose={props.handleClose}
            TransitionComponent={Slide}

        >
            <SnackbarContent style={{
                backgroundColor: `${!props.postSuccess ? 'red' : 'green'}`,
            }}
                message={props.message}
            />
        </Snackbar>
    )
}


export default withStore(NewPostForm)
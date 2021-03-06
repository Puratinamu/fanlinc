import React from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import postRequests from '../../requests/postRequests';
import Paper from '@material-ui/core/Paper';
import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';
import Zoom from '@material-ui/core/Zoom';
import Container from '@material-ui/core/Container'
import SearchField from '../core/searchfield'
import userRequests from '../../requests/userRequests';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import Slide from '@material-ui/core/Slide'
import { withStore } from '../../store'

require('./styles.scss')

const SNACKBAR_TIMEOUT = 4000;

class NewPostForm extends React.Component {
    constructor(props) {
        super(props)
        this.handlePostAttempt = this.handlePostAttempt.bind(this);
        this.postAttempt = this.postAttempt.bind(this);
        this.handleTitleInput = this.handleTitleInput.bind(this);

        this.setSelectedFandom = this.setSelectedFandom.bind(this);
        this.createFandomOptions = this.createFandomOptions.bind(this);
        this.handlePostInput = this.handlePostInput.bind(this);

        this.handleClose = this.handleClose.bind(this);

        this.state = {
            postText: "",
            postTextMissingError: false,
            postFailError: false,
            unknownError: false,
            postSuccess: false,
            selectedFandom: null,
            fandomSelected: false,
            fandomsList: [],
            notificationOpen: false,
            loading: true,
            title: '',
            message: "Unknown Error: Please contact support"
        }
    }
    handleTitleInput(e){
        this.setState({title:e.target.value})
    }
    async postAttempt() {

        postRequests.putPost({
            "oidCreator": parseInt(this.props.store.get("authenticatedOidUser")),
            "text": this.state.postText,
            "oidFandom": this.state.selectedFandom.oidFandom,
            "title": this.state.title,
        }, this.props.store.get("sessionToken")).then(response => {
            if (response.status === 200) {
                this.setState({ postSuccess: true, message: "Your post has been successfully added!", notificationOpen: true })
            } else if (response.status === 500) {
                this.setState({ postFailError: true, message: "Internal server error: Please contact support", notificationOpen: true })
            } else if (response.status === 400) {
                this.setState({ postFailError: true, message: "Bad request error: Please contact support", notificationOpen: true })
            }
            else {
                this.setState({ unknownError: true, message: "Unknown Error: Please contact support", notificationOpen: true })
            }
        })
    }

    handleClose() {
        this.setState({
            notificationOpen: false,
            postSuccess: false
        });
    }
    handlePostInput(newPost) {
        this.setState({ postText: newPost.target.value, postTextMissingError: newPost.target.value === "", unknownError: false })
    }
    handlePostAttempt(newPost) {
        if (this.state.postTextMissingError || this.state.postText === "" || this.state.title === "") {
            this.setState({ message: "Please enter all values", notificationOpen: true })
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
            let msg = "Unknown error generating fandoms: Please contact support"
            let err = false

            if (response.status === 200) {
                newFandomsList = this.createFandomOptions(response.data.fandoms);
            } else if (response.status === 500) {
                msg ="Internal server error generating fandoms: Please contact support"
                err = true
            } else if (response.status === 400) {
                msg = "Bad request error generating fandoms: Please contact support"
                err = true
            } else if (response.status === 404) {
                msg = "Unauthorized user error generating fandoms: Please contact support"
                err = true
            } else {
                err = true
            }
            this.setState({
                fandomsList: newFandomsList,
                loading: false,
                message: msg,
                notificationOpen: err,
                postFailError: err
            });
        })
    }

    render() {
        return (
            <Zoom in={!this.state.loading}>
                <Container className="cldi-make-post-container">
                    <Paper className="cldi-make-post-main" >
                        <Typography variant="h6">
                            Make a Post
                            </Typography>
                        <Divider />
                
                        <Box className="text-holder" p={1}>
                            <Box >
                                <SelectFandom loading={this.state.loading} callback={this.setSelectedFandom} searchList={this.state.fandomsList} />
                                <Box>
                                    <Box pt={1} >
                                        <TextField error={this.state.postTextMissingError} onInput={this.handleTitleInput} value={this.state.title} className="post-text-box" placeholder="Title" variant="outlined" />
                                    </Box>
                                    <Box>
                                        <PostField onInput={this.handlePostInput} error={this.state.postTextMissingError} />
                                    </Box>
                                    <Box>
                                        <PostButton error={this.state.postTextMissingError} onClick={this.handlePostAttempt} />
                                    </Box>
                                    <ShowMessages open={this.state.notificationOpen} handleClose={this.handleClose} postSuccess={this.state.postSuccess} message={this.state.message} />
                                </Box>
                            </Box>
                        </Box>
                    </Paper >
                </Container >
            </Zoom>
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

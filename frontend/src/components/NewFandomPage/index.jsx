import React from 'react';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import TextField from '@material-ui/core/TextField';
import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button'
import Zoom from '@material-ui/core/Zoom'
import Container from '@material-ui/core/Container'
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import Slide from '@material-ui/core/Slide'
import { withStore } from '../../store'

import fandomRequests from '../../requests/fandomRequests'
import './styles.scss';
const SNACKBAR_TIMEOUT = 4000;

class NewFandomPage extends React.Component {
    constructor(props) {
        super(props)
        this.handleFandomNameChange = this.handleFandomNameChange.bind(this);
        this.handleDescriptionChange = this.handleDescriptionChange.bind(this);
        this.handleCreateFandomRequest = this.handleCreateFandomRequest.bind(this);

        this.handleClose = this.handleClose.bind(this);

        this.state = {
            canProceed: false,
            fandomName: "",
            fandomDescription: "",
            error: false
        }
    }

    handleClose() {
        this.setState({
            notificationOpen: false
        });
    }
    handleFandomNameChange(e) {
        this.setState({ fandomName: e.target.value })
    }
    handleDescriptionChange(e) {
        this.setState({ fandomDescription: e.target.value })
    }

    
    async handleCreateFandomRequest() {
        let fandomName = this.state.fandomName;
        let fandomDescription = this.state.fandomDescription;
        let response = await fandomRequests.createFandom(fandomName, fandomDescription, this.props.store.get("authenticatedOidUser"), this.props.store.get("sessionToken"))
        if (response.status === 200) {
            this.setState({
                notificationOpen: true,
                message: "Created Fandom! What fun!",
                error: false
            });

        } else if (response.status === 422) {
            this.setState({
                notificationOpen: true,
                error: true,
                message: "The Fandom Already Exists! Consider a different name!"
            })

        }
        else {
            this.setState({
                notificationOpen: true,
                error: true,
                message: "Something has gone horribly wrong!"
            })
        }
    }
    render() {
        return (
            <Zoom in={true}>
              <Container maxWidth="md">
                <Paper className="cldi-make-fandom-main" >
                    <Typography variant="h6">
                        Make a Fandom
                      </Typography>
                    <Divider />
                    <Box className="cldi-text-container">
                        <Box textAlign="center" className="cldi-text-holder">
                            <Box display="flex">
                                <Typography className="cldi-text-box-label" component="div" fontWeight="fontWeightLight" >
                                    Fandom Name
                                </Typography>
                                <Box marginLeft="auto">
                                    <TextField
                                        error={this.state.error}
                                        id="Fandom Name"
                                        margin="normal"
                                        placeholder="League Of Legends"
                                        variant="outlined"
                                        className="cldi-text-box"
                                        value={this.state.fandomName}
                                        onInput={this.handleFandomNameChange}
                                    />
                                </Box>
                            </Box>
                            <Box display="flex">
                                <Typography component="div" className="cldi-text-box-label" fontWeight="fontWeightLight" >
                                    Description
                                </Typography>
                                <Box marginLeft="auto">
                                    <TextField
                                        id="Fandom Name"
                                        error={this.state.error}
                                        placeholder="The world's largest salt mine"
                                        multiline={true}
                                        rows="5"
                                        variant="outlined"
                                        className="cldi-text-box"
                                        value={this.state.fandomDescription}
                                        onInput={this.handleDescriptionChange}
                                    />
                                </Box>
                            </Box>

                        </Box>
                        <Box width="100%" align="right">
                            <Button
                                variant="contained"
                                color="primary"
                                disabled={this.state.fandomName === "" || this.state.fandomDescription === ""}
                                onClick={this.handleCreateFandomRequest}>
                                Create Fandom
                                 </Button>

                        </Box>
                    </Box>


                </Paper>
                <Snackbar
                    autoHideDuration={SNACKBAR_TIMEOUT}
                    open={this.state.notificationOpen}
                    onClose={this.handleClose}
                    TransitionComponent={Slide}

                >
                    <SnackbarContent style={{
                        backgroundColor: `${this.state.error ? 'red' : ''}`,
                    }}
                        message={this.state.message}

                    />
                </Snackbar>
            </Container>
          </Zoom>

        )
    }

}
export default withStore(NewFandomPage)

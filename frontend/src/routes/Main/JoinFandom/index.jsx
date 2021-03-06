import React from 'react';
import AddFandomToUserForm from '../../../components/AddFandomToUserForm/';
import {withStore} from '../../../store'
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button'
import Box from '@material-ui/core/Box';
import Snackbar from '@material-ui/core/Snackbar';
import Slide from '@material-ui/core/Slide';
import fandomRequest from '../../../requests/fandomRequests';
import './styles.scss';

const SNACKBAR_TIMEOUT = 4000;

class JoinFandom extends React.Component {

    constructor(input) {
        super(input);

        // Initialize the state
        this.state = {
            notificationOpen: false,
            message: "",
            fandom: null,
            interestLevel: "",
            canAdd: false
        };

        this.options = [{label: "Add New fandom", value: "/main/newfandom"}];

        // Needed to change the scope of 'this' in the function
        this.setFandomInfo = this.setFandomInfo.bind(this);
        this.addUserToFandom = this.addUserToFandom.bind(this);
        this.handleClose = this.handleClose.bind(this);
    }

    addUserToFandom() {
        fandomRequest.addFandomToUser(
            this.props.store.get("authenticatedOidUser"),
            this.state.fandom.oidFandom,
            this.state.interestLevel,
            this.props.store.sessionToken).then(response => {

            if (response.status === 200) {
                this.setState({
                    notificationOpen: true,
                    message: "Relationship added successfully"
                });
            } else {
                this.setState({
                    notificationOpen: true,
                    message: "Failed to add relationship"
                });
            }
        });
    }

    setFandomInfo(fandomInfo) {
        this.setState({
            fandom: fandomInfo.fandom,
            interestLevel: fandomInfo.interestLevel,
            canAdd: true
        });
    }

    handleClose() {
        this.setState({
            notificationOpen: false
        });
    }

    /**
     * Using the selectValue here just in case we decide to add more options in the future
     */
    routeToNewFandom(selectedValue) {
        this.props.history.push(selectedValue);
    }

    render() {
        return (
          <Grid className="cldi-join-fandom-page" container justify='center'>
            <Grid item xs={12} sm={8} md={6}>
              <AddFandomToUserForm
                  onOptionSelect={this.routeToNewFandom.bind(this)}
                  callback={this.setFandomInfo}
                  options={this.options}>
                <Box width="100%" align="center">
                  <Button className="cldi-join-fandom-add-button"
                    disabled={!this.state.canAdd}
                    onClick={this.addUserToFandom}
                    variant="contained"
                    color="primary">
                      ADD
                  </Button>
                </Box>
              </AddFandomToUserForm>
            </Grid>
            <Snackbar
              autoHideDuration={SNACKBAR_TIMEOUT}
              open={this.state.notificationOpen}
              onClose={this.handleClose}
              TransitionComponent={Slide}
              message={this.state.message}>
            </Snackbar>
          </Grid>
        );
    }
}

export default withStore(JoinFandom);


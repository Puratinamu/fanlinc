import React from 'react';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Zoom from '@material-ui/core/Zoom';
import Divider from '@material-ui/core/Divider';
import userRequests from '../../requests/userRequests';
import ContactList from './ContactsList';
import './styles.scss';
import Container from "@material-ui/core/Container";

const ContactsList = "Contact List";


class ViewContacts extends React.Component {

    constructor(input) {
        super(input);

        this.store = input.store;

        // Initialize the state
        this.state = {
            contactsList: [],
            loading: true,
        };

    }

    componentDidMount() {
        userRequests.getContacts(this.store.get('authenticatedOidUser'),
            this.props.store.get("sessionToken")).then(response => {
            let contactsList = [];
            if (response.status === 200) {
                contactsList = response.data;
            }
            this.setState({
                contactsList: contactsList,
                loading: false
            });
        });
    }


    render() {
        return (
            <Zoom in={!this.state.loading}>
                <Container className="cldi-view-contacts-form-container" maxWidth="md" >
                    <Box justify='center'>
                      <Paper>
                        <Box px={4} pb={4} pt={3}>
                          <Grid container spacing={4} direction="column">
                            {!this.state.loading &&
                              (
                                <Grid item xs={12}>
                                  <Typography component="h3" variant='h4' align="center">{ContactsList}</Typography>
                                  <Divider/>
                                    {/*// If there are no contacts, show a message*/}
                                    {(this.state.contactsList.length === 0) ?
                                    (
                                        <Typography component='h4' variant='h4' color='textSecondary' align='center'>
                                            You Have No Contacts.
                                        </Typography>
                                    ) : (
                                          <ContactList contactsList={this.state.contactsList} history={this.props.history}/>
                                      )
                                    }
                                </Grid>
                              )
                            }
                            {this.children}
                          </Grid>
                        </Box>
                      </Paper>
                    </Box>
                </Container>
            </Zoom>
        );
    }
}

export default ViewContacts;


import React from 'react';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import userRequests from '../../requests/userRequests';
import ContactList from './ContactsList';
import './styles.scss';
import Container from "@material-ui/core/Container";

const ContactsList = "Contact List";

// Request Body:
// {
//     "oidUser" : 123
// }
//
// Response Body:
// {
//     "contacts": [
//     {
//         "oidUser" : 1234,
//         "email" : "bob.marley@gmail.com",
//         "username": "TheBobMarley",
//         "description": "a person",
//         "fandoms": [123]
//     },
//     ...
// ]
// }


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
        userRequests.getContacts(this.store.get('authenticatedOidUser')).then(response => {
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
            <Container maxWidth="md" >
                <Box className="cldi-view-contacts-form-container" justify='center'>
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
        );
    }
}

export default ViewContacts;


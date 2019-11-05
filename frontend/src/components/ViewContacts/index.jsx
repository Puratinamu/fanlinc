import React from 'react';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import SearchField from '../core/searchfield/';
import Grid from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import userRequest from '../../requests/userRequests';
import ContactList from './ContactsList'
import './styles.scss';
import Container from "@material-ui/core/Container";


const SearchAFandom = "Search for a Fandom";
const SelectInterestLevel = "Select Level of Interest";

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

const ContactsList = "Contact List";


class ViewContacts extends React.Component {

    constructor(input) {
        super(input);

        // Initialize the state
        this.state = {
            loading: true,
            oidUser: null,
            contactsList: [],
        }

        // Needed to change the scope of 'this' in the function
        this.callback = input.callback;
        this.children = input.children;
    }

    componentDidMount() {
        // Get the list of all contacts
        userRequest.getContacts(this.state.oidUser).then(response => {
            let contactsList = [];
            if (response.status === 200) {
                contactsList = this.createContactList(response.data);
            }
            this.setState({
                contactsList: contactsList,
                loading: false
            });
        })
    }

    /*
     * Rerender the children component whenever the parent of this node is rerendered
     */
    componentWillUpdate(input) {
        this.children = input.children;
    }

    createContactList(contacts) {
        let options = [];

        for (let i = 0; i < contacts.length; i++) {
            if (contacts[i]) {
                options.push({
                    value: `${contacts[i].oidUser}`,
                    label: `${contacts[i].username}`,
                    data: contacts[i]
                });
            }
        }

        return options;
    }



    render() {

        // // If there are no contacts, show a message
        // if (!this.state.loading && this.state.contactsList.length === 0) {
        //     return (
        //         <Typography component='h4' variant='h4' color='textSecondary' align='center'>
        //             You Have No Contacts.
        //         </Typography>
        //     );
        // }

        return (
            <Container maxWidth="md" >
                <Box className="cldi-view-contacts-form-container" container justify='center'>
                  <Paper>
                    <Box px={4} pb={4} pt={3}>
                      <Grid container spacing={4} direction="column">
                        {!this.state.loading &&
                          (
                            <Grid item xs={12}>
                              <Typography component="h3" variant='h4' align="center">{ContactsList}</Typography>
                              <Divider/>
                                {/*// If there are no contacts, show a message*/}
                                {!this.state.contactsList.length === 0 &&
                                (
                                    <Typography component='h4' variant='h4' color='textSecondary' align='center'>
                                        You Have No Contacts.
                                    </Typography>
                                )}
                                {this.state.contactsList.length === 0 &&
                                  (
                                    <ContactList/>
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


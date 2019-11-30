import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { Typography, Box } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';
import contactRequests from '../../../requests/contactsRequest'
import Loading from '../../core/Loading';

require('./styles.scss')

function MessengerContact(props) {
    return (
        <Grid item xs={12} className="messenger-contacts-box">
            <Card onClick={props.onClick} className={`message-contacts-box-content ${props.isSelected ? "message-contacts-box-selected" : ""}`}>
                <CardContent>
                    <Typography variant="h6" color="primary"> {props.contactName}</Typography>
                </CardContent>
            </Card>
        </Grid >
    )
}

class MessengerContacts extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            contacts: {},
            loading: true
        }
    }
    componentDidMount() {
        contactRequests.getContacts(this.props.store.get("authenticatedOidUser"), this.props.store.get("sessionToken")).then((response) => {
            this.setState({ contacts: response.data.contacts, loading: false })
        })
    }
    render() {
        return (
            <Box p={1} >
                <Box className="message-contacts-header" p={1} mb={1}>
                    <Typography color="primary" variant="h6">
                        Direct Messages
                    </Typography>
                </Box>
                {!this.state.loading &&
                    <Grid spacing={1} className="message-box-container-grid" container alignItems="center" >
                        {this.state.contacts.length > 0 && this.state.contacts.map((contact, index) =>
                            <MessengerContact contactName="Michael Cottow" onClick={() => { this.props.selectedCallback("contacts", 0) }} isSelected={this.props.hasSelected && (this.props.selected === 0)} />
                        )}
                    </Grid>
                }
                {this.state.contacts.length === 0 &&
                    <Box p={1}>
                        <Typography color="primary" variant="body2"> You don't have any Direct Messages</Typography>
                    </Box>
                }
                {this.state.loading &&
                    <Loading />
                }
            </Box>
        )
    }
}

export default MessengerContacts;
import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { Typography, Box } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';

require('./styles.scss')

function MessengerContact(props) {
    return (
            <Grid item xs={12} className="messenger-contacts-box">
                <Card className={`message-contacts-box-content ${props.isSelected ? "message-contacts-box-selected" : ""}`}>
                    <CardContent>
                        <Typography variant="h6" color="primary"> {props.contactName}</Typography>
                        <Typography variant="overline"> {props.contactMessageCount} Past Message{props.contactMessageCount === "1" ? "" : "s"}</Typography>
                    </CardContent>
                </Card>
            </Grid >
    )
}

class MessengerContacts extends React.Component {

    render() {
        return (
            <Box px={3} pt={3} pb={3} >
                <Box className="message-contacts-header" p={1} mb={1}>
                    <Typography color="primary" variant="h6">
                        Contacts
                    </Typography>
                </Box>

                <Grid spacing={1} className="message-box-container-grid" container alignItems="center" >
                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" isSelected="true" />
                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />
                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />
                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />
                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />

                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />

                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />

                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />

                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />

                    <MessengerContact contactName="Michael Cottow" contactMessageCount="1" />

                </Grid>
            </Box>
        )
    }
}

export default MessengerContacts;
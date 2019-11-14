import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { Typography, Box } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';

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

    render() {
        return (
            <Box px={3} pt={3} pb={3} >
                <Box className="message-contacts-header" p={1} mb={1}>
                    <Typography color="primary" variant="h6">
                        Contacts
                    </Typography>
                </Box>

                <Grid spacing={1} className="message-box-container-grid" container alignItems="center" >
                    <MessengerContact contactName="Michael Cottow" onClick={() => {this.props.selectedCallback("contacts", 0)}} isSelected={this.props.hasSelected && (this.props.selected === 0)} />
                    <MessengerContact contactName="Michael Cottow" onClick={() => {this.props.selectedCallback("contacts", 1)}} isSelected={this.props.hasSelected && (this.props.selected === 1)} />
                    <MessengerContact contactName="Michael Cottow" onClick={() => {this.props.selectedCallback("contacts", 2)}} isSelected={this.props.hasSelected && (this.props.selected === 2)}/>


                </Grid>
            </Box>
        )
    }
}

export default MessengerContacts;
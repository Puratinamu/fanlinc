import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { Typography, Box } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';

require('./styles.scss')

function MessengerFandom(props) {
    return (
        <Grid item xs={12} className="fandom-contacts-box">
            <Card className={`fandom-contacts-box-content ${props.isSelected ? "fandom-contacts-box-selected" : ""}`}>
                <CardContent>
                    <Typography variant="h6" color="primary"> {props.fandomName}</Typography>
                    <Typography variant="overline"> {props.fandomInterestLevel}</Typography> <br />
                    <Typography variant="overline"> {props.fandomMessageCount} Past Message{props.fandomMessageCount === "1" ? "" : "s"}</Typography>
                </CardContent>
            </Card>
        </Grid >
    )
}

class MessengerFandoms extends React.Component {

    render() {
        return (
            <Box px={3} pt={1} pb={8} >
                <Box className="fandom-contacts-header" p={1} mb={1}>
                    <Typography color="primary" variant="h6">
                        Fandoms
                    </Typography>
                </Box>

                <Grid spacing={1} className="fandom-box-container-grid" container alignItems="center" >
                    <MessengerFandom fandomName="Minecraft" fandomMessageCount="1"  fandomInterestLevel="Expert" />
                    <MessengerFandom fandomName="Minecraft" fandomMessageCount="1"  fandomInterestLevel="Expert" />
                    <MessengerFandom fandomName="Minecraft" fandomMessageCount="1"  fandomInterestLevel="Expert" />
                    <MessengerFandom fandomName="Minecraft" fandomMessageCount="1"  fandomInterestLevel="Expert" />

                </Grid>
            </Box>
        )
    }
}

export default MessengerFandoms;
import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { Typography, Box } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';
import Loading from '../../core/Loading'
import userRequests from '../../../requests/userRequests';

require('./styles.scss')

function MessengerFandom(props) {
    return (
        <Grid item xs={12} className="fandom-contacts-box">
            <Card onClick={props.onClick} className={`fandom-contacts-box-content ${props.isSelected ? "fandom-contacts-box-selected" : ""}`}>
                <CardContent>
                    <Typography variant="h6" color="primary"> {props.fandomName}</Typography>
                    <Typography variant="overline"> {props.fandomInterestLevel}</Typography> <br />
                </CardContent>
            </Card>
        </Grid >
    )
}

class MessengerFandoms extends React.Component {
    constructor(props) {
        super(props)
        this.store = props.store;

        this.state = {
            loading: true
        }
    }
    componentDidMount() {
        userRequests.getUser(this.store.get('authenticatedOidUser')).then(response => {
            let user;

            if (response.status === 200) {
                user = response.data;
            }

            this.setState({
                user: user,
                loading: false
            });
            if (user.fandoms.length !== 0) {
                this.props.callback(user.fandoms[0].oidFandom, user.fandoms[0].relationship, user.fandoms[0].name);
            }
        });

    }
    render() {
        return (
            <Box px={3} pt={1} pb={8} >
                <Box className="fandom-contacts-header" p={1} mb={1}>
                    <Typography color="primary" variant="h6">
                        Fandoms
                    </Typography>
                </Box>
                {!this.state.loading &&
                    <Grid spacing={1} className="fandom-box-container-grid" container alignItems="center" >
                        {this.state.user.fandoms.length > 0 && this.state.user.fandoms.map((fandom, index) =>
                            <MessengerFandom key={fandom.oidFandom}
                                fandomName={fandom.name}
                                isSelected={this.props.hasSelected && (this.props.selected === index)}
                                fandomInterestLevel={fandom.relationship.toLowerCase()}
                                onClick={() => { this.props.callback(fandom.oidFandom, fandom.relationship, fandom.name); this.props.selectedCallback("fandoms", index) }} />)}
                        {this.state.user.fandoms.length === 0 &&
                            <Box p={1}>
                                <Typography color="primary" variant="body2"> You're not in any fandoms!</Typography>
                            </Box>}
                    </Grid>
                }
                {this.state.loading &&
                    <Loading />
                }

            </Box>
        )
    }
}

export default MessengerFandoms;
import React from 'react';
import MessengerChats from  './MessengerChats'
import MessengerMain from './MessengerMain'
import { Box } from '@material-ui/core';
require('./styles.scss')

class Messenger extends React.Component {

    render() {
        return (
            <Box display="flex">
                <MessengerChats store={this.props.store}/>
                <MessengerMain store={this.props.store}/>
            </Box>
        )
    }
}


export default Messenger
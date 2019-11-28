import React from 'react';
import SearchField from '../../core/searchfield'
import { Box } from '@material-ui/core';

class MessengerChatSearch extends React.Component{

    render() {
        return (
            <Box px={3} pt={2}>
                <SearchField />
            </Box>
        )
    }
}

export default MessengerChatSearch
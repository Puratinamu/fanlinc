import React from 'react';
import Messenger from '../../../components/Messenger'
import { withStore } from '../../../store';
import { Box } from '@material-ui/core';

class MessengerPage extends React.Component {

    render() {

        return (<Box p={1}><Messenger store={this.props.store} /></Box>)
    }
}
export default withStore(MessengerPage);
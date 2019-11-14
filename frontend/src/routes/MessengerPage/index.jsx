import React from 'react';
import Messenger from '../../components/Messenger'
import { withStore } from '../../store';

class MessengerPage extends React.Component {

    render() {
        return <Messenger store={this.props.store}/>
    }
}
export default withStore(MessengerPage);
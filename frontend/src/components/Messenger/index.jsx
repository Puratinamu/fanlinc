import React from 'react';
import MessageHolder from './MessageHolder';
import MessageComposer from './MessageComposer';

require('./styles.scss')

class Messenger extends React.Component {

    render() {
        return (
            <div>
                <MessageHolder />
                <MessageComposer />
            </div>
        )
    }
}

export default Messenger
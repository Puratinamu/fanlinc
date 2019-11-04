import React from 'react';
import MessageHolder from './MessageHolder'
import MessageComposer from './MessageComposer'
require('./styles.scss')

class Messenger extends React.Component{
    constructor(){
        super()
    }

    render(){
        return(
            //<MessageHolder />
            <MessageComposer />
        )
    }
}

export default Messenger
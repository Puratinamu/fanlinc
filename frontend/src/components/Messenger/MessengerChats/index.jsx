import React from 'react';
import { Paper, Box } from '@material-ui/core';
import MessengerFandoms from '../MessengerFandoms';

require('./styles.scss')


class MessengerChats extends React.Component {
    constructor(props){
        super(props);
        this.handleOptionSelect = this.handleOptionSelect.bind(this);
        this.state = {
            selectedFromFandoms:true,
            selectedFandom: 0
        }
    }

    handleOptionSelect(from, index){
       if(from === "fandoms"){
            this.setState({
                selectedFromFandoms:true,
                selectedFandom:index
            })
        }
    }

    render() {
        return (
            <Box className="messenger-chats-view">
                <Box  p={1}>
                    <Paper >
                        <Box className="messenger-chat" >
                            <MessengerFandoms callback={this.props.callback} selectedCallback={this.handleOptionSelect} hasSelected={this.state.selectedFromFandoms} selected={this.state.selectedFandom} store={this.props.store}/>
                        </Box>
                    </Paper>    
                </Box>
            </Box>

        )
    }
}
export default MessengerChats
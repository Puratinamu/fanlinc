import React from 'react';
import CssBaseline from '@material-ui/core/CssBaseline'
import AppBar from '@material-ui/core/AppBar'
import Typography from '@material-ui/core/Typography'
import Toolbar from '@material-ui/core/Toolbar'
import Divider from '@material-ui/core/Divider';
import Box from '@material-ui/core/Box';
import Grid from '@material-ui/core/Grid';
import MessageHolder from './MessageHolder';
import MessageComposer from './MessageComposer';

require('./styles.scss')

class Messenger extends React.Component {
    constructor() {
        super()
    }

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
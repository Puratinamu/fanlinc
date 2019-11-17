import React from 'react';
import { Card, Divider, Typography, Box } from '@material-ui/core';
import './styles.scss';

class Post extends React.Component {
    render() {
        return (
            <Box className="post-item" p={1}>
                <Card>
                    <Box pt={2} pb={2} pl={1} pr={1}>
                        <Box display="flex">
                            <Box>
                                <Typography variant="overline" >
                                    {this.props.fandom}
                                </Typography>
                            </Box>
                            <Box className="post-data" >
                                <Typography variant="overline" >
                                    Post by {this.props.author} at {this.props.date}
                            </Typography>
                            </Box>
                        </Box>
                        <Divider />
                        <Box pt={2}>
                            <Box pb={2}>
                                <Typography variant="h5" color="primary">
                                    {this.props.title}
                                </Typography>
                            </Box>
                            <Typography variant="body1">
                                {this.props.text}
                        </Typography>
                        </Box>
                    </Box>
                </Card>
            </Box>
        )
    }
}

export default Post
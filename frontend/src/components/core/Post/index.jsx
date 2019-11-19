import React from 'react';
import { Card, Divider, Typography, Box } from '@material-ui/core';
import Link from '@material-ui/core/Link';

require('./styles.scss')

class Post extends React.Component {
    render() {
        return (
            <Box className="post-item" p={1}>
                <Card>
                    <Box py={2} px={2} >
                        <Box display="flex" justifyContent="space-between">
                            <Box>
                                <Typography variant="overline" >
                                    {this.props.fandom}
                                </Typography>
                            </Box>
                            <Box display="flex" className="post-data" >
                                <Link href={`main/viewprofile?id=${this.props.creatorOid}`}>
                                    <Typography variant="overline" > Post by &nbsp;</Typography>
                                    <Typography variant="overline" color="primary">{this.props.author}</Typography>
                                </Link>
                                <Typography variant="overline" >&nbsp; At &nbsp;</Typography>
                                <Typography variant="overline" color="primary"> {this.props.date}</Typography>
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
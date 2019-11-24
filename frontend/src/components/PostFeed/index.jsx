import React from 'react';
import { Container, Typography, Box } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';
import Zoom from '@material-ui/core/Zoom';
import Post from '../core/Post';
import Loading from '../core/Loading';
import './styles.scss';

import postRequests from '../../requests/postRequests'

class PostFeed extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            posts: [],
            loading: true, 
            error: false
        }
    }
    componentDidMount() {
        postRequests.getPostFeed(this.props.store.get("authenticatedOidUser"), this.props.store.get("sessionToken")).then((response) => {
            if (response.status === 200) {
                this.setState({ loading: false, posts: response.data.posts, error: false })
            } else {
                this.setState({ loading: false, error: true})
            }
        })
    }
    render() {
        return (
            <Zoom in={!this.state.loading}>
                <Container className="post-feed-container">
                    <Grid>
                        {this.state.loading === true && <Loading />}
                        {this.state.loading === false && this.state.error && <Typography color="error"> Posts could not be retrieved</Typography>}
                        {this.state.loading !== true && !this.state.error && this.state.posts.length === 0 &&
                            <Box p={1} >
                                <Typography color="textSecondary" variant="h4" align="center"> There are no posts in your feed</Typography>
                            </Box>
                        }
                        {
                            this.state.loading !== true &&
                            this.state.posts.length > 0 &&
                            !this.state.error &&
                            this.state.posts.map((postData, index) => {
                                let date = new Date(postData.creationTimeStamp);
                
                                return <Grid item xs={12} key={index} >
                                    <Post key={index} creatorOid={postData.oidCreator} title={postData.title} date={date.toLocaleTimeString('en-US') + ", " + date.toLocaleDateString()} text={postData.text} author={postData.username} fandom={postData.fandomName} />
                                </Grid>
                            })
                        }
                    </Grid>
                </Container>
            </Zoom>
        )
    }

}

export default PostFeed

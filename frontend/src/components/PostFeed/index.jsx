import React from 'react';
import { Container, Typography, Box } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';
import Post from '../core/Post';
import Loading from '../core/Loading';
import './styles.scss';

import postRequests from '../../requests/postRequests'

class PostFeed extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            posts: [],
            loading: true
        }
    }
    componentDidMount() {
        postRequests.getPostFeed(this.props.store.get("authenticatedOidUser"), this.props.store.get("sessionToken")).then((response) => {
            console.log(response)

            if (response.status === 200) {
                this.setState({ loading: false, posts: response.data.posts })
            }
        })
    }
    render() {
        return (
            <Container>
                <Grid className="post-feed-container">
                    {this.state.loading === true && <Loading />}
                    {this.state.loading !== true && this.state.posts.length === 0 &&
                        <Box display="flex" justifyContent="center" p={1} >
                            <Typography color="primary" variant="h3"> There are no posts in your feed</Typography>
                        </Box>
                    }
                    {
                        this.state.loading !== true &&
                        this.state.posts.length > 0 &&
                        this.state.posts.map((postData, index) => {
                            let date = new Date(postData.creationTimeStamp)

                            return <Grid item xs={12} key={index} >
                                <Post key={index} title={postData.title} date={date.toLocaleTimeString('en-US') + ", " + date.toLocaleDateString()} text={postData.text} author={postData.username} fandom={postData.fandomName} />
                            </Grid>
                        })
                    }
                </Grid>
            </Container>
        )
    }

}

export default PostFeed
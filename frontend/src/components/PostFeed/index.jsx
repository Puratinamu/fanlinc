import React from 'react';

import { Container, Typography, Box } from '@material-ui/core';
import Post from '../core/Post';
import Loading from '../core/Loading';

class PostFeed extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            posts: [],
            loading: false
        }
    }
    render() {
        return (
            <Container >
                {this.state.loading === true && <Loading />}
                {this.state.loading !== true && this.state.posts.length === 0 &&
                    <Box display="flex" justifyContent="center" p={1} >
                        <Typography color="primary" variant="h3"> There are no posts in your feed</Typography>
                    </Box>
                }
                {this.state.loading !== true && this.state.posts.length > 0 &&
                    <div>
                        <Post title="wack" date="wack" text="wack" author="wack" fandom="wack" />
                        <Post title="wack" date="wack" text="wack" author="wack" fandom="wack" />
                        <Post title="wack" date="wack" text="wack" author="wack" fandom="wack" />
                        <Post title="wack" date="wack" text="wack" author="wack" fandom="wack" />
                        <Post title="wack" date="wack" text="wack" author="wack" fandom="wack" />
                        <Post title="wack" date="wack" text="wack" author="wack" fandom="wack" />
                        <Post title="wack" date="wack" text="wack" author="wack" fandom="wack" />
                        <Post title="wack" date="wack" text="wack" author="wack" fandom="wack" />
                    </div>
                }

            </Container>
        )
    }

}

export default PostFeed
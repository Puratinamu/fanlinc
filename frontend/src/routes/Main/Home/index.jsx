import React from 'react';
import PostFeed from '../../../components/PostFeed';
import {withStore} from '../../../store'

class Home extends React.Component{
    render(){
        return(
            <PostFeed store={this.props.store}/>
        )
    }

}

export default withStore(Home)
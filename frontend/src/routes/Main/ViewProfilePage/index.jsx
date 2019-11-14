import React from 'react';
import Grid from '@material-ui/core/Grid';
import ViewProfile from '../../../components/ViewProfile/';
import mockUser from './__mocks__/mockUser.json';
import { withStore } from '../../../store';

import './styles.scss';

class ViewProfilePage extends React.Component {

    render() {
        return (
          <Grid className="cldi-view-user-profile-page" container justify='center'>
            <Grid item xs={12} sm={8} md={6}>
                <ViewProfile store={this.props.store} {... mockUser}/>
            </Grid>
          </Grid>
        );
    }

}

export default withStore(ViewProfilePage);

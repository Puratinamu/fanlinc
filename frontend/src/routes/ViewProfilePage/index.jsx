import React from 'react';
import Grid from '@material-ui/core/Grid';
import ViewProfile from '../../components/ViewProfile/';
import mockUser from './__mocks__/mockUser.json';
import { withStore } from '../../store';
import redirectManager from '../../redirectManager'
import userRequests from '../../requests/userRequests';

import './styles.scss';

class ViewProfilePage extends React.Component {
  constructor() {

  }
  componentDidMount() {

    userRequests.getUser(redirectManager.getUrlParam("id")  === "" ? this.store.get('authenticatedOidUser') : redirectManager.getUrlParam("id")).then(response => {
      let user;

      if (response.status === 200) {
        user = response.data;
      }

      this.setState({
        user: user,
        loading: false
      });
    });
  }
  render() {
    return (
      <Grid className="cldi-view-user-profile-page" container justify='center'>
        <Grid item xs={12} sm={8} md={6}>

          <ViewProfile store={this.props.store} {...mockUser} user={this.store.user}/>
        </Grid>
      </Grid>
    );
  }

}

export default withStore(ViewProfilePage);

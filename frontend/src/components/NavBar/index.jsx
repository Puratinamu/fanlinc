import React from 'react';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import FolderIcon from '@material-ui/icons/Folder';
import RestoreIcon from '@material-ui/icons/Restore';
import FavoriteIcon from '@material-ui/icons/Favorite';
import LocationOnIcon from '@material-ui/icons/LocationOn';

import './styles.scss';

class Navbar extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            currentSelection: "profile"
        }

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(_, newValue) {
        this.setState({currentSelection: newValue});
    }

    render() {
        return (
            <BottomNavigation className="cldi-nav-bar" value={this.state.profile} onChange={this.handleChange}>
              <BottomNavigationAction label="Profile" value="recents" icon={<RestoreIcon />} />
              <BottomNavigationAction label="Favorites" value="favorites" icon={<FavoriteIcon />} />
              <BottomNavigationAction label="Nearby" value="nearby" icon={<LocationOnIcon />} />
              <BottomNavigationAction label="Folder" value="folder" icon={<FolderIcon />} />
          </BottomNavigation>
        )
    }
}

export default Navbar;

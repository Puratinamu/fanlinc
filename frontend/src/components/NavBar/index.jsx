import React from 'react';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import ChatIcon from '@material-ui/icons/Chat';
import PeopleAltIcon from '@material-ui/icons/PeopleAlt';
import PersonIcon from '@material-ui/icons/Person';
import HomeIcon from '@material-ui/icons/Home';
import NewPostIcon from '@material-ui/icons/Create';

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

        // Execute callback if it exists
        if (this.props.callback && this.props.callback instanceof Function) {
            this.props.callback(newValue);
        }
    }

    render() {
        return (
          <BottomNavigation showLabels className="cldi-nav-bar" value={this.state.profile} onChange={this.handleChange}>
            <BottomNavigationAction className="NavBarIcon" label="Home" value="/main" icon={<HomeIcon />} />
            <BottomNavigationAction  className="NavBarIcon" label="Profile" value="/main/viewprofile" icon={<PersonIcon />} />
            <BottomNavigationAction className="NavBarIcon"  label="Lincs" value="/main/contacts" icon={<PeopleAltIcon />} />
            <BottomNavigationAction  className="NavBarIcon" label="Chat" value="/main/messenger" icon={<ChatIcon />} />
            <BottomNavigationAction  className="NavBarIcon" label="New Post" value="/main/newpost" icon={<NewPostIcon />} />

          </BottomNavigation>
        )
    }
}

export default Navbar;

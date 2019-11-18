import React from 'react';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import ChatIcon from '@material-ui/icons/Chat';
import PeopleAltIcon from '@material-ui/icons/PeopleAlt';
import PersonIcon from '@material-ui/icons/Person';
import HomeIcon from '@material-ui/icons/Home';

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
            <BottomNavigationAction label="Home" value="/main" icon={<HomeIcon />} />
            <BottomNavigationAction label="Profile" value="/main/viewprofile" icon={<PersonIcon />} />
            <BottomNavigationAction label="Lincs" value="/main/contacts" icon={<PeopleAltIcon />} />
            <BottomNavigationAction label="Chat" value="/main/chat" icon={<ChatIcon />} />
          </BottomNavigation>
        )
    }
}

export default Navbar;

import React from 'react';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import SearchField from '../core/searchfield/';
import Grid from '@material-ui/core/Grid';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import IconButton from '@material-ui/core/IconButton';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import Zoom from '@material-ui/core/Zoom';
import Divider from '@material-ui/core/Divider';
import fandomRequest from '../../requests/fandomRequests';
import './styles.scss';


const SearchAFandom = "Search for a Fandom";
const SelectInterestLevel = "Select Level of Interest";


class AddFandomToUserForm extends React.Component {

    constructor(input) {
        super(input);

        // Generate options for the Interest levels
        this.interestLevels = this.createInterestLevelOptions(fandomRequest.getLevels());

        // Initialize the state
        this.state = {
            loading: true,
            fandomsList: [],
            menuOpen: false,
            menuAnchorEl: null,
            fandomSelected: false,
            selectedFandom: null,
            selectedInterestLevel: ""
        }

        // Needed to change the scope of 'this' in the function
        this.setSelectedFandom = this.setSelectedFandom.bind(this);
        this.setInterestLevel = this.setInterestLevel.bind(this);
        this.renderOptionsMenu = this.renderOptionsMenu.bind(this);
        this.closeOptionsMenu = this.closeOptionsMenu.bind(this);

        this.callback = input.callback;
        this.children = input.children;
    }

    componentDidMount() {
        // Get the list of all fandoms
        fandomRequest.getAllFandoms().then(response => {
            let fandomsList = [];
            if (response.status === 200) {
                fandomsList = this.createFandomOptions(response.data);
            }
            this.setState({
                fandomsList: fandomsList,
                loading: false
            });
        });
    }

    /*
     * Rerender the children component whenever the parent of this node is rerendered
     */
    componentWillUpdate(input) {
        this.children = input.children;
    }

    createInterestLevelOptions(levels) {
        let options = [];

        for (let level in levels) {
            options.push({
                value: levels[level],
                label: `${level} - ${levels[level]}`,
                data: levels[level]
            });
        }

        return options;
    }

    createFandomOptions(fandoms) {
        let options = [];

        for (let i = 0; i < fandoms.length; i++) {
            if (fandoms[i]) {
                options.push({
                    value: `${fandoms[i].oidFandom}`,
                    label: `${fandoms[i].name}`,
                    data: fandoms[i]
                });
            }
        }

        return options;
    }

    setSelectedFandom(selection) {
        this.setState({
            selectedFandom: selection.data,
            fandomSelected: true
        });

        // Execute callback if it exists and interest level is already selected
        if (this.state.selectedInterestLevel && this.callback && this.callback instanceof Function) {
            this.callback({
                fandom: selection.data,
                interestLevel: this.state.selectedInterestLevel
            });
        }
    }

    setInterestLevel(selection) {
        this.setState({
            selectedInterestLevel: selection.data
        });

        // Execute callback if it exists
        if (this.callback && this.callback instanceof Function) {
            this.callback({
                fandom: this.state.selectedFandom,
                interestLevel: selection.data
            });
        }
    }

    handleOptionsMenuClick(event) {
        this.setState({
            menuOpen: !this.state.menuOpen,
            menuAnchorEl: (this.state.menuAnchorEl ? null : event.currentTarget)
        });
    }

    handleOptionMenuItemClick(selectedValue) {
        this.setState({
            menuOpen: false,
            menuAnchorEl: null
        });

        // Emit out the clicked value
        if (this.props.onOptionSelect && this.props.onOptionSelect instanceof Function) {
            this.props.onOptionSelect(selectedValue);
        }
    }

    closeOptionsMenu() {
        this.setState({
            menuOpen: false,
            menuAnchorEl: null
        });
    }

    renderOptionsMenu() {
        // Create menu items for each option
        let items = [];
        for (let i = 0, len = this.props.options.length; i < len; i++) {
            let currentOption = this.props.options[i];
            // On click of the menu item emit out the value that was clicked
            items.push(
                <MenuItem key="menu-item" onClick={this.handleOptionMenuItemClick.bind(this, currentOption.value)}>{currentOption.label}</MenuItem>
            );
        }

        return (
            <div className="cldi-options-menu">
                <IconButton className="cldi-options-menu-icon" onClick={this.handleOptionsMenuClick.bind(this)}>
                    <MoreVertIcon />
                </IconButton>
                <Menu open={this.state.menuOpen} anchorEl={this.state.menuAnchorEl} onClose={this.closeOptionsMenu}>
                    {items}
                </Menu>
            </div>
        );
    }

    render() {
        // If there are no fandoms to select from, show a message
        if (!this.state.loading && this.state.fandomsList.length === 0) {
            return (
                <Box >
                    {this.props.options && this.renderOptionsMenu()}
                    <Typography component='h4' variant='h4' color='textSecondary' align='center'>
                        Sorry, No Fandoms Currently Exist.
                </Typography>
                </Box>
            );
        }

        return (
            <Zoom in={!this.state.loading}>
                <Box className="cldi-add-fandom-to-user-form-container">
                    <Paper>
                        <Box px={4} pb={4} pt={3}>
                            <Grid container spacing={4} direction="column">
                                {!this.state.loading &&
                                    (
                                        <Grid item xs={12}>
                                            <div className="cldi-select-fandom-heading-container">
                                                <Typography variant="h6">{SearchAFandom}</Typography>
                                                {this.props.options && this.renderOptionsMenu()}
                                            </div>
                                            <Divider />
                                            <SearchField
                                                callback={this.setSelectedFandom}
                                                placeHolder={SearchAFandom}
                                                searchList={this.state.fandomsList} />
                                        </Grid>
                                    )
                                }
                                {this.state.fandomSelected &&
                                    (
                                        <Grid item xs={12}>
                                            <Typography variant="h6">{SelectInterestLevel}</Typography>
                                            <Divider />
                                            <SearchField
                                                callback={this.setInterestLevel}
                                                isSearchable={false}
                                                placeHolder={SelectInterestLevel}
                                                searchList={this.interestLevels} />
                                        </Grid>
                                    )
                                }
                                {this.children}
                            </Grid>
                        </Box>
                    </Paper>
                </Box>
            </Zoom>
        );
    }
}

export default AddFandomToUserForm;


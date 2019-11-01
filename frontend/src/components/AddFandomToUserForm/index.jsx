import React from 'react';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import SearchField from '../core/searchfield/';
import Grid from '@material-ui/core/Grid';
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
            fandomSelected: false,
            selectedFandom: null,
            selectedInterestLevel: ""
        }

        // Needed to change the scope of 'this' in the function
        this.setSelectedFandom = this.setSelectedFandom.bind(this);
        this.setInterestLevel = this.setInterestLevel.bind(this);

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
        })
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

    render() {

        // If there are no fandoms to select from, show a message
        if (!this.state.loading && this.state.fandomsList.length === 0) {
            return (
                <Typography component='h4' variant='h4' color='textSecondary' align='center'>
                    Sorry, No Fandoms Currently Exist.
                </Typography>
            );
        }

        return (
            <Box className="cldi-add-fandom-to-user-form-container">
              <Paper>
                <Box px={2} pb={4} pt={3}>
                  <Grid container spacing={4} direction="column">
                    {!this.state.loading &&
                      (
                        <Grid item xs={12}>
                          <Typography variant="h6">{SearchAFandom}</Typography>
                          <Divider/>
                          <SearchField
                            callback={this.setSelectedFandom}
                            placeHolder={SearchAFandom}
                            searchList={this.state.fandomsList}/>
                        </Grid>
                      )
                    }
                    {this.state.fandomSelected &&
                      (
                        <Grid item xs={12}>
                          <Typography variant="h6">{SelectInterestLevel}</Typography>
                          <Divider/>
                          <SearchField
                            callback={this.setInterestLevel}
                            isSearchable={false}
                            placeHolder={SelectInterestLevel}
                            searchList={this.interestLevels}/>
                        </Grid>
                      )
                    }
                    {this.children}
                  </Grid>
                </Box>
              </Paper>
            </Box>
        );
    }
}

export default AddFandomToUserForm;


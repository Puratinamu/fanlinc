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
    }

    componentDidMount() {
        // Get the list of all fandoms
        fandomRequest.getAllFandoms().then(response => {
            if (response.status === 200) {
                this.setState({
                    fandomsList: this.createFandomOptions(response.data),
                    loading: false
                });
            }
        })
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

    setSelectedFandom(fandom) {
        this.setState({
            selectedFandom: fandom,
            fandomSelected: true
        });
    }

    setInterestLevel(interestLevel) {
        this.setState({ selectedInterestLevel: interestLevel });
    }

    render() {
        return (
            <Box className="cldi-add-fandom-to-user-form-container">
              <Paper>
                <Box p={2}>
                  <Grid container spacing={4} direction="column">
                    {this.state.loading === false &&
                      (
                        <Grid item xs={12}>
                          <Typography component="h3">{SearchAFandom}</Typography>
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
                          <Typography component="h3">{SelectInterestLevel}</Typography>
                          <Divider/>
                          <SearchField
                            callback={this.setInterestLevel}
                            isSearchable={false}
                            placeHolder={SelectInterestLevel}
                            searchList={this.interestLevels}/>
                        </Grid>
                      )
                    }
                  </Grid>
                </Box>
              </Paper>
            </Box>
        );
    }
}

export default AddFandomToUserForm;


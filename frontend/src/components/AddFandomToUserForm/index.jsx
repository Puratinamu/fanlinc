import React from 'react';
import Paper from '@material-ui/core/Paper';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import SearchField from '../core/searchfield/';
import Grid from '@material-ui/core/Grid';
import Divider from '@material-ui/core/Divider';
import './styles.scss';


const options = [
      { value: 'chocolate', label: 'Chocolate'  },
      { value: 'strawberry', label: 'Strawberry'  },
      { value: 'vanilla', label: 'Vanilla'  },
];

const SearchAFandom = "Search for a Fandom";
const SelectInterestLevel = "Select Level of Interest";


class AddFandomToUserForm extends React.Component {

    constructor(input) {
        super(input);

        // Initialize the state
        this.state = {
            selectedFandom: {},
            fandomSelected: false,
            selectedInterestLevel: ""
        }

        // Needed to change the scope of 'this' in the function
        this.setSelectedFandom = this.setSelectedFandom.bind(this);
        this.setInterestLevel = this.setInterestLevel.bind(this);
    }

    setSelectedFandom(fandom) {
        this.setState({
            selectedFandom: fandom,
            fandomSelected: true
        });
    }

    setInterestLevel(interestLevel) {
        this.setState({ setInterestLevel: interestLevel });
    }

    render() {
        return (
            <Box className="cldi-add-fandom-to-user-form-container">
              <Paper>
                <Box p={2}>
                  <Grid container spacing={4} direction="column">
                    <Grid item xs={12}>
                      <Typography component="h3">{SearchAFandom}</Typography>
                      <Divider/>
                      <SearchField
                        callback={this.setSelectedFandom}
                        placeHolder={SearchAFandom}
                        searchList={options}/>
                    </Grid>
                    {this.state.fandomSelected &&
                        (
                            <Grid item xs={12}>
                              <Typography component="h3">{SelectInterestLevel}</Typography>
                              <Divider/>
                              <SearchField
                                  callback={this.setInterestLevel}
                                  isSearchable={false}
                                  placeHolder={SelectInterestLevel}
                                  searchList={options}/>
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


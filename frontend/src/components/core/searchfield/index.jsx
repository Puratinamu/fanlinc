import React from 'react';
import Select from 'react-select';
import './styles.scss';


const PlaceHolderDefault = "Search";


class SearchField extends React.Component {

    constructor(input) {
        super(input);

        this.callback = input.callback;
        this.isSearchable = input.isSearchable === undefined ? true : false;
        this.placeHolder = input.placeHolder || PlaceHolderDefault;
        this.searchList = input.searchList || [];

        this.state = {
            selected: ""
        };

        // Needed to change the scope of 'this' in the function
        this.inputChange = this.inputChange.bind(this);
    }

    inputChange(change) {
        this.setState({ selected: change.label });

        // Execute callback if it exists
        if (this.callback && this.callback instanceof Function) {
            this.callback({name: change.label});
        }
    }

    render() {
        return (
            <div>
                <Select
                    className="cldi-search-field"
                    placeholder={this.state.selected || this.placeHolder}
                    onChange={this.inputChange}
                    isSearchable={this.isSearchable}
                    options={this.searchList}>
                </Select>
            </div>
        );
    }
}

export default SearchField;


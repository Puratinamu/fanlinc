import React from 'react';
import SearchField from './';
import renderer from 'react-test-renderer';


describe('SearchField', () => {

    let component, instance;

    beforeEach(() => {
        component = renderer.create(
            <SearchField />,
        );
        instance = component.getInstance();
    });

    it('Should update state when the inputChange is called', () => {
        let testChange = {
            label: "something"
        };
        instance.inputChange(testChange);
        expect(instance.state.selected).toEqual(testChange.label);
    });

});


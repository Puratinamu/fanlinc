import '../../requests/__mocks__/fandomRequestsMock';
import React from 'react';
import JoinFandom from './';
import renderer from 'react-test-renderer';


describe('JoinFandom', () => {

    let component, instance;

    beforeEach(() => {
        component = renderer.create(
            <JoinFandom />,
        );
        instance = component.getInstance();
    });

    it('Should set fandom info', () => {
        let testLevel = "NOOB";
        let testFandom = {
            "oidFandom": "2",
            "name": "fandom2"
        };
        let testFandomInfo = {
            fandom: testFandom,
            interestLevel: testLevel
        };

        let actual = instance.setFandomInfo(testFandomInfo);

        expect(instance.state.fandom).toEqual(testFandom);
        expect(instance.state.interestLevel).toEqual(testLevel);
    });

});


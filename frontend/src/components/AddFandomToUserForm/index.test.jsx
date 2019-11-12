import '../../requests/__mocks__/mockRequests';
import React from 'react';
import AddFandomToUserForm from './';
import renderer from 'react-test-renderer';


describe('AddFandomToUserForm', () => {

    let component, instance;

    beforeEach(() => {
        component = renderer.create(
            <AddFandomToUserForm />,
        );
        instance = component.getInstance();
    });

    it('Should initialize the interest level options', () => {
        let testLevels = {
            1: "HELLO",
            2: "HELLOTHERE"
        };

        let actual = instance.createInterestLevelOptions(testLevels);
        let expected = [
            {
                value: testLevels[1],
                label: `1 - ${testLevels[1]}`,
                data: testLevels[1],
            },
            {
                value: testLevels[2],
                label: `2 - ${testLevels[2]}`,
                data: testLevels[2],
            }
        ];
        expect(actual).toEqual(expected);
    });

    it('Should initialize the fandoms options', () => {
        let testFandoms = [
            {
                "oidFandom": "1",
                "name": "fandom1"
            },
            {
                "oidFandom": "2",
                "name": "fandom2"
            }
        ];

        let actual = instance.createFandomOptions(testFandoms);
        let expected = [
            {
                value: testFandoms[0].oidFandom,
                label: testFandoms[0].name,
                data: testFandoms[0],
            },
            {
                value: testFandoms[1].oidFandom,
                label: testFandoms[1].name,
                data: testFandoms[1],
            }
        ];
        expect(actual).toEqual(expected);
    });

});


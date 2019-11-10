import '../../requests/__mocks__/mockRequests';
import userRequests from '../../requests/userRequests';
import React from 'react';
import ViewProfile from './';
import renderer from 'react-test-renderer';

describe('ViewProfile', () => {

    let component, instance, spy, mockStore, testOidUser;

    beforeEach(() => {
        testOidUser = 1;
        mockStore = {get: jest.fn(() => testOidUser)};

        component = renderer.create(
            <ViewProfile store={mockStore} />,
        );
        instance = component.getInstance();
    });

    it('Should call getUser with the authenticatedOidUser', () => {
        expect(mockStore.get).toHaveBeenCalledWith('authenticatedOidUser');
        expect(userRequests.getUser).toHaveBeenCalledWith(testOidUser);
    });

});



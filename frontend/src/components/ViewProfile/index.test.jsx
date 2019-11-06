import '../../requests/__mocks__/mockRequests';
import userRequests from '../../requests/userRequests';
import React from 'react';
import ViewProfile from './';
import renderer from 'react-test-renderer';

describe('ViewProfile', () => {

    let component, instance, spy;

    beforeEach(() => {
        component = renderer.create(
            <ViewProfile />,
        );
        instance = component.getInstance();
    });

    it('Should call getUser on mount', () => {
        expect(userRequests.getUser).toHaveBeenCalled();
    });

});



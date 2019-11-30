// Link.react.test.js
import '../../__mocks__/store.js';
import store from '../../store.js';
import React from 'react';
import Root from './';
import { BrowserRouter as Router } from 'react-router-dom';
import renderer from 'react-test-renderer';

test('Link changes the class when hovered', () => {
    const component = renderer.create(
        <Router>
            <Root store={store}/>
        </Router>
    );
    let tree = component.toJSON();
    expect(tree).toMatchSnapshot();
});

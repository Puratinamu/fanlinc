// Link.react.test.js
import React from 'react';
import Home from './Home';
import renderer from 'react-test-renderer';

test('Link changes the class when hovered', () => {
  const component = renderer.create(
    <Home />,
  );
  let tree = component.toJSON();
  expect(tree).toMatchSnapshot();
});
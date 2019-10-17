// Link.react.test.js
import React from 'react';
import SampleComponent from './SampleComponent';
import renderer from 'react-test-renderer';

test('Link changes the class when hovered', () => {
  const component = renderer.create(
    <SampleComponent />,
  );
  let tree = component.toJSON();
  expect(tree).toMatchSnapshot();
});
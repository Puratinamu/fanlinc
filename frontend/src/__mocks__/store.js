let mockStore = {
    get: jest.fn()
};

jest.mock('../store', () => ({
    get: jest.fn(),
    withStore: jest.fn((input) => {
        if (input.props) {
            input.props.store = mockStore;
        } else {
            input.props = {
                store: mockStore
            };
        }
        return input;
    })
}));



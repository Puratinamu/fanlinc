let mockAsync = jest.fn(() =>({then: jest.fn()}));
jest.mock('axios', () => jest.fn());
jest.mock('../fandomRequests', () => {
    return {
        getLevels: jest.fn(),
        getAllFandoms: mockAsync
    }
});
jest.mock('../userRequests', () => {
    return {
        putUser: mockAsync,
        loginUserRequest: mockAsync,
        getUser: mockAsync
    }
});


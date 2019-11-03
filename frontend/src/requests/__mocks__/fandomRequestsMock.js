jest.mock('axios', () => jest.fn());
jest.mock('../fandomRequests', () => {
    return {
        getLevels: jest.fn(),
        getAllFandoms: jest.fn(() =>({then: jest.fn()}))
    }
});


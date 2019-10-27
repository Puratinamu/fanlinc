# Sprint 2 Meeting

## Attendees

| Name              | Utorid       |
| ----------------- |:------------:|
| Andrew Fung       | fungand2     |
| Michael Cottow    | cottowmi     |
| Nicholas Wong     | wongni21     |
| Sean Applebaum    | appleb16     |
| Sofia Ilina       | ilinasof     |
| Tanner Bergeron   | berger48     |
| Padmanaban Prasad | prasada7     |

All members were present during this meeting.

## User Stories For Sprint 2
- 

## Tasks

* Login - Front end (5) (Michael):
    * Create Login Component
    * Routes to landing page
    * Tests
    * QA

* Login - Back end (2) (Andrew):
    * See Andrew’s code
    * Return auth token
    * Tests

* Authentication - Front end (3) (Michael):
    * Implement Session Tokens
    * Save returned auth token after login to a cookie/session memory
    * Tests

* Authentication - Back end (5) (Nick):
    * Implement Authentication tokens
    * Return on login
    * Check on each Secured requests (things you need to be logged in for)
    * Tests

* View User Profile - Front end (5) (Anandha):
    * Create Component to display user’s info
    * Query Backend to get info
    * Send auth token in the headers
    * Tests

* View User Profile - Backend (2) (Tanner):
    * Endpoint GET /profile
    * Only return if auth token is valid
    * Only return certain fields
    * Tests

* View Other User’s Profile - Front end (3) (Sean):
    * Display user information based on route i.e /users/userid
    * No auth needed
    * Figure out routing
    * Only return certain fields, less fields than get own profile
    * Tests

* Get Other User Profile - Back end (2) (Tanner):
    * getUser endpoint
    * Only return certain fields (use your brain)
    * Tests

* View Contacts - Front end (3) (Andrew):
    * Create component to display a user’s contacts
    * Data retrieved with getProfile, should be in the state
    * Tests

Total Story Points Planned: 30

CARRY OVER:
- (2) Cloud 31 - Test post Backend - Sofia
- (2) Cloud 29 - Text Post Front End - Sean 
- (5) Cloud 32 -  Add Existing Fandom to user - Anandha
- (2) Cloud 30 - Create Fandom Front end - Michael
- (2) Cloud 28 - Add Users to Contact List - Backend - Sofia
- (1) Cloud 27 - (Tests)(Review) - Tanner
- (1) Cloud 33 - (Tests)(Review) - Sofia

Total Carry Over: 15


##Point breakdown (carry-over) + new

    Michael -> (2) + 5 + 3 = 10
    Sofia -> (5) + 0 = 5
    Tanner -> (1) + 2  + 2 = 5
    Sean -> (2) + 3 = 5
    Nick -> (0) + 5 = 5
    Anandha -> (5) + 5 = 10
    Andrew -> (0) + 2 + 3 = 5
    
Total Story Points: 45


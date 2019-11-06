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
- Login (2)
    - As Miranda, Jarrod, or Steven, I should be able to use the credentials I provided during registration to authenticate the access to my account to view my personal information and fandoms on the website.

- View Profile (3) 
    - As Miranda, Jarrod, or Steven, I should be able to view my profile after logging in to the website to see any personal information and fandoms I have provided the website.

- Add Fandom (3)
    - As Miranda, Jarrod, or Steven, I should be able to add fandoms to my profile by providing the type and level of fandom I am in to reflect my current interests.

- View Other People's Profiles (3)
    - As Miranda, I should be able to view other cosplayers' profiles to see their information and their interests.

- Add Contacts (3)
    - As Miranda, Jarrod, or Steven, I should be able to add users to my list of contacts when I view their profile so that I can keep my contacts list up to date.

- Text Posts (5)
    - As Steven, I should be able to make text posts that ouline strategies in improving the ranks in EVE Online to my timeline so that it will appear in other user’s feeds and guide new players. (5)

- Create Fandom (3)
    - As Jarrod, I should be able to create a fandom related to my music albums if it does not already exist so I provide a ground for fans to critique my music on Fanlinc. (3) 

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

# Sprint Backlog
## User Stories
- CLOUD-7 Login
- CLOUD-8 View Profile
- CLOUD-9 Add Fandom
- CLOUD-10 View Other People's Profiles
- CLOUD-12 Add Contacts
- CLOUD-14 Text Posts
- CLOUD-16 Create Fandom

All stories have been started.

### Sprint 2 Tasks

- CLOUD-32 Add existing fandom to user - frontend
    - Assignee: Anandha Prasad
    - Relates to CLOUD-16

- CLOUD-38 Login - Frontend
    - Assignee: Michael Cottow
    - Relates to CLOUD-7
   
- CLOUD-45 Get profile of general user with limited information (Backend)
    - Assignee: Tanner Bergeron
    - Relates to CLOUD-8

- CLOUD-46 View User Profile - Front end
    - Assignee: Anandha Prasad
    - Relates to CLOUD-8

- CLOUD-31 Make text posts backend
    - Assignee: Sofia Ilina
    - Relates to CLOUD-14

- CLOUD-30 Create Fandom (Front-End)
    - Assignee: Michael Cottow
    - Relates to CLOUD-16
    
- CLOUD-40 Login-Backend
    - Assignee: Andrew Fung
    - Relates to CLOUD-7

- CLOUD-44 Get authenticated user information
    - Assignee: Tanner Bergeron

- CLOUD-42 View Contacts - Front end
    - Assignee: Andrew Fung
    - Relates to CLOUD-8

- CLOUD-29 Make text posts to timeline - Front End (unfinished due time constraints from assignments and midterms)
    - Assignee: Sean Applebaum
    - Relates to CLOUD-14

- CLOUD-39 Authentication - Back end
    - Assignee: Nicholas Wong

- CLOUD-41 Authentication Frontend
    - Assignee: Michael Cottow

- CLOUD-28 Add users to contact list - Back End (unfinished due time constraints from assignments and midterms)
    - Assignee: Sofia Ilina
    - Relates to CLOUD-12

- CLOUD-43 View Other User’s Profile - Front end (unfinished due time constraints from assignments and midterms)
    - Assignee: Sean Applebaum
    - Relates to CLOUD-10

# Sprint 1 Meeting

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

## User Stories For Sprint 1
- Create Profile (5)
    - As Miranda, Jarrod, or Steven, I should be able to sign up for Fanlinc and create my profile so that I can 
    register with the service and access the website.

- Add Fandom (3)
    - As Miranda, Jarrod, or Steven, I should be able to add fandoms to my profile by providing the type and level of fandom I am in to reflect my current interests.

- Text Posts (5)
    - As Steven, I should be able to make text posts that ouline strategies in improving the ranks in EVE Online to my timeline so that it will appear in other user’s feeds and guide new players.

- Create Fandom (3)
    - As Jarrod, I should be able to create a fandom related to my music albums if it does not already exist so I provide a ground for fans to critique my music on Fanlinc.

## Tasks
 
* Initialize DB Objects (3) NICK
    * User
    * Post
    * Fandom
    * Make sure all required fields are included in the object
    * Make sure all necessary relationships are present and try to cover as many necessary

* Sign-up page - front end (5) - MICHAEL
    * Follow the design on MarvelApp
    * Make the proper REST requests (see API doc)
    * Basic tests
    * DOES NOT include add fandom stage

* Sign-up - back end (3) - ANDREW
    * Implement POST user functionality
    * Create User service
    * Hash password (Bcrypt)
    * Save to db
    * Tests

* Add existing fandom to user - Front End (5) PADMANABAN
    * Implement search for fandoms from GET fandoms
    * Add fandom to user profile during sign-up
    * Should call GET fandom endpoint for list of fandoms
    * Create basic mock data for front end for testing
    * User should be able to add as many fandoms to their profile as they want
    * User should be able to select level and type
    * CANNOT create new fandoms here
    * Tests

* Add existing fandom to user - Back End (2) TANNER
    * GET Fandoms API endpoint
    * Should return only name/id based on header (don’t respond with list of posts, other metadata if it’s just for getting list of fandom names)
    * Update PUT user endpoint
    * Tests

* Add users to contact list - Back End (2) SEAN
    * Put User endpoint should update user’s contact list
    * Tests

* Make text posts to timeline - Front End (2) SEAN
    * Create Text Form Component for user to type text into
    * Allow user to specify which fandom to post into
    * Have a submit button
    * Create New Post Button
    * Make Backend call to POST Post
    * Tests

* Make text posts - Back End (2) SOFIA
    * Create post endpoint
    * Should update a relationship with User and Fandom
    * Tests
    * Create Fandom if it does not exist - Front End (2) TANNER
    * Create form component that lets user create new fandom
    * Name of fandom
    * On submit, make sure the fandom doesn’t already exist (avoid overwriting it) HTTP 422
    * Tests

* Create Fandom if it does not exist - Back End (2) SOFIA
    * Create endpoint for creating fandoms (POST fandom)
    * Validation endpoint to confirm if fandom exists
    * Tests

### Story Point Breakdown
- Padmanaban - 5 
- Michael - 5
- Nick - 3
- Sofia - 2 + 2
- Tanner - 2 + 2
- Andrew - 3
- Sean - 2 + 2 <br />
<br />
Total = 28

# Sprint Backlog
### User Stories
- CLOUD-6 Create Profile
- CLOUD-9 Add Fandom
- CLOUD-14 Text Posts (unfinished due time constraits from assignments and midterms)
- CLOUD-16 Create Fandom

### Sprint 1 Tasks
- CLOUD-1 Initialize Backend Spring Java
    - Assignee: Nicholas Wong

- CLOUD-2 Initialize the Frontend React Project
    - Assignee: Michael Cottow
    
- CLOUD-25 Create node entities
    - Assignee: Nicholas Wong
    
- CLOUD-26 API Documentation
    - Assignee: Sofia Ilina
    
- CLOUD-5 Sign-up - Back end
    - Assignee: Andrew Fung
    - Relates to CLOUD-6
    
- CLOUD-32 Add existing fandom to user - frontend
    - Assignee: Padmanaban Prasad
    - Relates to CLOUD-16
    
- CLOUD-27 Add Fandom to User (Back-End)
    - Assignee: Tanner Bergeron
    - Relates to CLOUD-9
    
- CLOUD-28 Add users to contact list - Back End (unfinished due time constraits from assignments and midterms)
    - Assignee: Sean Applebaum
    - Relates to CLOUD-12
    
- CLOUD-29 Make text posts to timeline - Front End (unfinished due time constraits from assignments and midterms)
    - Assignee: Sean Applebaum
    - Relates to CLOUD-14
    
- CLOUD-30 Create Fandom (Front-End) (unfinished due time constraits from assignments and midterms)
    - Assignee: Tanner Bergeron
    - Relates to CLOUD-16
    
- CLOUD-31 Make text posts backend (unfinished due time constraits from assignmennts and midterms)
    - Assignee: Sofia Ilina
    - Relates to CLOUD-14
    
- CLOUD-33 Create Fandom if does not exist - backend (unfinished due time constraits from assignments and midterms)
    - Assignee: Sofia Ilina
    - Relates to CLOUD-16
    
- CLOUD-37 Sign-up page - front end
    - Assignee: Michael Cottow
    - Relates to CLOUD-6
    
- CLOUD-34 Make Components to follow the design on Marvel App
    - Assignee: Michael Cottow

- CLOUD-35 Make http requests to backend for registration.
    - Assignee: Michael Cottow

- CLOUD-36 Tests for frontend registration
    - Assignee: Michael Cottow

# Sprint 1 Meeting

### Tasks
 
Initialize DB Objects (3) NICK
* User
* Post
* Fandom
* Make sure all required fields are included in the object
* Make sure all necessary relationships are present and try to cover as many necessary

Sign-up page - front end (5) - MICHAEL
* Follow the design on MarvelApp
* Make the proper REST requests (see API doc)
* Basic tests
* DOES NOT include add fandom stage

Sign-up - back end (3) - ANDREW
* Implement POST user functionality
* Create User service
* Hash password (Bcrypt)
* Save to db
* Tests

Add existing fandom to user - Front End (5) AAAAANANDHA
* Implement search for fandoms from GET fandoms
* Add fandom to user profile during sign-up
* Should call GET fandom endpoint for list of fandoms
* Create basic mock data for front end for testing
* User should be able to add as many fandoms to their profile as they want
* User should be able to select level and type
* CANNOT create new fandoms here
* Tests

Add existing fandom to user - Back End (2) TANNER
* GET Fandoms API endpoint
* Should return only name/id based on header (don’t respond with list of posts, other metadata if it’s just for getting list of fandom names)
* Update PUT user endpoint
* Tests

Add users to contact list - Back End (2) SEAN
* Put User endpoint should update user’s contact list
* Tests

Make text posts to timeline - Front End (2) SEAN
* Create Text Form Component for user to type text into
* Allow user to specify which fandom to post into
* Have a submit button
* Create New Post Button
* Make Backend call to POST Post
* Tests

Make text posts - Back End (2) SOFIA
* Create post endpoint
* Should update a relationship with User and Fandom
* Tests
* Create Fandom if it does not exist - Front End (2) TANNER
* Create form component that lets user create new fandom
* Name of fandom
* On submit, make sure the fandom doesn’t already exist (avoid overwriting it) HTTP 422
* Tests

Create Fandom if it does not exist - Back End (2) SOFIA
* Create endpoint for creating fandoms (POST fandom)
* Validation endpoint to confirm if fandom exists
* Tests

### Story point breakdown

Ananda - 5

Michael - 5

Nick - 3

Sofia - 2 + 2

Tanner - 2 + 2

Andrew - 3

Sean - 2 + 2

= 28

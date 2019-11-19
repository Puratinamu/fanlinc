# Sprint 3 Meeting

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

## User Stories For Sprint 3
- Add Contacts (3)
    - As Miranda, Jarrod, or Steven, I should be able to add users to my list of contacts when I view their profile so that I can keep my contacts list up to date.

- View Profile (3) 
    - As Miranda, Jarrod, or Steven, I should be able to view my profile after logging in to the website to see any personal information and fandoms I have provided the website.

- Search Fandom (3)
    - As Miranda, Steven, or Jarrod, I should be able to search for a fandom to find a community I am interested in.

- Post Feed (13)
    - As Miranda, I should have a feed showing me posts made by other cosplayers so I can keep up to date with current cosplaying events.

- View Other People's Profiles (3)
    - As Miranda, I should be able to view other cosplayers' profiles to see their information and their interests.

- Contacts (3)
    - As Miranda, Jarrod, or Steven, I should be able to add users to my list of contacts when I view their profile so that I can keep my contacts list up to date.

- Text Posts (5)
    - As Steven, I should be able to make text posts that ouline strategies in improving the ranks in EVE Online to my timeline so that it will appear in other user’s feeds and guide new players.

- Chat (13)
    - As Steven, I would like to chat with other members of EVE Online fandom to discuss strategies to improve ones rank in the in-game universe. (13)

## Tasks

* Landing Page - Front end TOP PRIORITY (Anandha) (5)
    * Create buttons for each Route (Profile, Contacts, Feed, etc.
    * Create nav bar (Bottom bar)
    * Tests

* Post Feed - Front end (Michael) (5)
    * Receive a list of posts by fandom
    * Render them in chronological order
    * Sort out pagination

* Post Feed - Back end (Nick) (5)
    * Return posts for a user based on their fandoms and recency

* Search Fandom - Front end (Anandha) (1)
    * Route to Join fandom
    * Test it. Important

* Link Contacts to Profiles - Front end (Andrew) (2)
    * Add a button in user’s contacts page that links to another users profile

* Modify GetFandoms endpoint to have members field (Sofia) (2)
    * Update endpoint to return lists of oidUser values which represent members belonging to a fandom  

* Update fandom endpoints to deal with response bodies (Sofia) (2)
    * All fandom related endpoints should be dealing with response body, not Fandom objects

* Chat (Michael) (13)
    * Create chat for different fandoms and fandom levels

Total Story Points Planned: 35

CARRY OVER:
- (2) Cloud 47 - Add Contacts Front end - Michael
- (3) Cloud 42 -  View Contacts Front end - Andrew
- (5) Cloud 46 - View User Profile Front end - Anandha
- (2) Cloud 29 - Make text posts to timeline Frontend - Sean
- (2) Cloud 28 - Add users to contact list Back end - Sofia
- (3) Cloud 43 - View Other User’s Profile Front end - Sean
- (2) Cloud 44 - Get authenticated user info Back end - Tanner

Total Carry Over: 19


##Point breakdown (carry-over) + new

    Michael -> (2) + 5 + 13 = 20
    Sofia -> (2) + 2 + 2 = 6
    Tanner -> (2) + 0 = 2
    Sean -> (2) + (3) = 5
    Nick -> (0) + 5 = 5
    Anandha -> (5) + 5 + 1 = 11
    Andrew -> (3) + 2 = 5
    
Total Story Points: 54

# Sprint Backlog
## User Stories
- CLOUD-8 View Profile
- CLOUD-10 View Other People's Profiles
- CLOUD-11 Contacts
- CLOUD-12 Add Contacts
- CLOUD-13 Post Feed
- CLOUD-14 Text Posts
- CLOUD-15 Search Fandom
- CLOUD-19 Chat

All stories have been started.

### Sprint 3 Tasks

- CLOUD-50 Post Feed - Front end
    - Assignee: Michael Cottow
    - Relates to CLOUD-13

- CLOUD-49 Post Feed - Back end
    - Assignee: Nicholas Wong
    - Relates to CLOUD-13

-CLOUD-47 Add Contacts - Front end
    - Assignee: Michael Cottow 
    - Relates to CLOUD-12

- CLOUD-46 View User Profile - Front end
    - Assignee: Anandha Prasad
    - Relates to CLOUD-8

- CLOUD-29 Make text posts to timeline - Front End
    - Assignee: Sean Applebaum
    - Relates to CLOUD-14

- CLOUD-43 View Other User’s Profile - Front end
    - Assignee: Sean Applebaum
    - Relates to CLOUD-10

- CLOUD-44 Get authenticated user information
    - Assignee: Tanner Bergeron 

- CLOUD-54 Link Contacts to Profiles - Front end
    - Assignee: Andrew Fung
    - Relates to CLOUD-42

- CLOUD-42 View Contacts - Front end
    - Assignee: Andrew Fung 
    - Relates to CLOUD-11

- CLOUD-55 Modify GetFandoms endpoint to have members field
    - Assignee: Sofia Ilina 

- CLOUD-56 Update fandom endpoints to deal with response bodies
    - Assignee: Sofia Ilina 

- CLOUD-28 Add users to contact list - Back End
    - Assignee: Sofia Ilina
    - Relates to CLOUD-12

- CLOUD-53 Create Landing Page
    - Assignee: Anandha Prasad 

- CLOUD-57 getContacts Endpoint
    - Assignee: Andrew Fung
    - Relates to CLOUD-11

- CLOUD-58 Chat
    - Assignee: Michael Cottow
    - Relates to CLOUD-19

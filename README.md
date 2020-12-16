
Readme
----
# OnTheGo - Server
This is the server repo, view here the client repo:
https://github.com/MeniToledano/ClientTransmit

A Web application which connects volunteers deliver packages on their regular routes for those without the means to do so. For example, if you need assistance transfering a used fridge from Tel Aviv to Haifa, you might find a person who already drives daily on this route, and can assist with this task.

The system contains both volunteers and people who request their assistance. A volunteer can add his daily routes, in order to find people who are asking for help on this route.

A simple algorithm matches people’s requests with volunteer routes, and lets them know about each other.

# My Goal
After graduating from college, I was looking for an idea to develop in my free time. An idea that will be interesting and inspiring to develop. Currently this project is not production ready (obviously) but I wish it will be, and eventually it will help people who really need help. In the meantime, I enjoy adding new code every day and see the progress.
Also, I wanted to experience a new framework and technologies.

# Tech Stack
I chose **Java**, **Hibernate** & **H2** for the backend, and **Angular** & **Typescript** for the client.

# Project status
The current version includes:
1. Basic authentication - to be replaced with a more secure way
2. Dashboard - where users can view other user’s requests for assistance
3. Match finder - a simple algorithm which finds a volunteer for a user who requested assistance.
4. My routes - a page where any user can add their routes to help others
5. My requests - a page where any user can add a request for assistance

# In progress
1. Authentication - replacing basic authentication with JWT for better security.
2. Authorization - add roles (user, admin) in order to protect some endpoints and show different features to different roles.
3. Deployment - upload this project to AWS/GCP/another service. 

# Further work:

4. Feel & looks (client) - make the app look better by using icons and more components from Material Design.
5. Algorithm - make the algorithm more flexible: allow it to find a match that is not exactly what the user asked, but close enough (will require integration with external API such as Google Maps)
6. Algorithm - keep a list of matches, so in case of rejection, the algorithm won’t pick the same match.
7. Emails - send an email once a match has been made.
8. Require match approval - a volunteer should be able to reject a match found by the algorithm. In this case, a new match will be found for the user who requested help.
9. Manual matches - allow volunteers to use the dashboard to pick the user’s they can and want to assist.
10 Private Messages - allow users to chat with each other regarding the match (or just offer their assistance)
11. Dashboard - add filters to the list of messages (free text, by field, etc…)
12. Dashboard - support client pagination (server already supports that)
13. Giveaway portal - another portal allowing users to upload messages with items they wish to get rid of, and assist other users by giving them away.

# Installation and Setup Instructions
TBD

# Images
https://github.com/MeniToledano/ServerTransmit/issues/1

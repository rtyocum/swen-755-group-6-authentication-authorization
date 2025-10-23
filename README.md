# swen-755-group-6-authentication-authorization

## Prerequisites: 

### Have an CLI or IDE (IntelliJ) installed and launched

## How to run code: 
- Git clone https://github.com/rtyocum/swen-755-group-6-authentication-authorization.git
- Run Java AuthorizationApplication.java ( CLI or IDE)
- Open a browser, head to localhost:8080

## Frameworks & Libraries used:
### Spring Framework
### Spring Boot
### Spring Web
### Spring Security


# Design
## Overview

Spring framework was selected over JAAS due to the large number of deprecated dependencies subsequently Spring framework assists in encapsulating modules that perform authentication for users. This technology tool allows the graceful implementation of both authenticating and authorizing actors, specifically in the library Spring Security. In addition, this provides actual login and access control logic. The system is constrained to Java implementation and runs on a Windows environment via command line interface, on Visual Studio Code or IntelliJ IDE.

##  Implementation

Firstly, the SpringBoot module “@SpringBootApplication” is a wrapper class that serves as an entry point for the application. It is composed of three other modules: “@SecurityConfig”, “@EnableAutoConfig”, and “@ComponentScan”. This system is responsible for authorizing actors. The system requires authentication of actors via a password. Particularly the “UserDetailsService” is a central authentication module, storing their information in memory. Upon launching the entry point, a login is prompted and if incorrect, the actor is not authenticated as they are not confirmed to be who they are without the correct password. The passwords are encrypted at rest by a hash using the Spring Framework.

User accounts:

* bob/password  
* alice/password

The actors types are admin, user, and public. What this translates to is that certain types or in this case roles have more access than others on the endpoints, designated by an URL. Whereas admin or user must be logged in to access their roles and endpoint URLs. Given incorrect login, the system reprompts login information. The system supports validating input and is sanitized by Spring framework.

The system supports the authorization of actors by ensuring that an authenticated actor has the rights to access a service for that designated role. For example, in the public URL, anyone can see that endpoint. Alice, who is a user, can only login and see that she is authenticated, but bob who is an admin, has greater rights in the more restricted URL. This is verified by the “.hasRole()” command. Hence, proper identification is verified; the system also contains credential-based authentication in the form of username/password. One late bound component that exists here is the “security.core.Authentication” module, as it is bound at runtime, and only created after a user has provided credentials. 

Endpoints:

*  /  
*  /public  
*  /user  
* /admin

Each endpoint has their own  “@RestController” which is composed of “@Controller” and “@ResponseBody”, that is a RESTful web service. For instance, in the AdminController class, return “admin endpoint” is returned as a raw HTTP response body. The “/” endpoint occurs by default after a successful login, in which the user can know who and what role they are. The InMemoryUserDetailsManager verifies user details. These above aforementioned tactics satisfy the security quality attribute. 

The following documentation showcases the validation and verification of an actor's identity..

## Authentication
### Sequence diagram:
![Alt text](authenticate_seq.png)

The above sequence diagram displays the interactions between the front-end to Spring framework encapsulation of authentication procedure. Authentication manager is an created automatically by Spring Boot using UserDetailsService and PasswordEncoder. SecurityChainFilter uses AuthenticationManager to authenticate input from user, then it looks for inputted user to verify their existence and then checks the password is correct as well. Upon having correct credentials, the user is redirected to the default login. If invalid credentials were entered, then authentication was not possible and if on an restricted endpoint, access is denied. 

### Acitvity diagram:
![Alt text](authenticate_act.png)
The activity diagram above depicts a user being authenticated which will prompt a login. It runs a check to determine if anyone is logged in and if not it will prompt a login. The input will be validated as user credentials that contain both username and password and eventually grant login status or deny being logged in for invalid credentials.

## Authorization
Assumptions: The user is already logged in or a public user; the only concern here is what they are allowed to access. Therefore, the diagrams will not include the act of logging in. The scope of these diagrams illustrates the logic flow of the front-en,d where Spring framework system-level and internal back-end services are outside the scope and not depicted. 

### Sequence Diagram:
![Alt text](authorize_seq.png)
In the Authorization sequence diagram, it is showing the concurrent act of whichever endpoint is being accessed. In this case, it starts with the most simple endpoint "public" anyone can access it via it's permitAll() function that allows access without authentication. Then, in the case of admin and user endpoints, they share the same restriction, that first it must be authenticated then authorized by role. The sequence diagram has somewhat overlapping if-else blocks in order to achieve this representation of shared authorized behavior, but for differing endpoints. To prevent the diagram from becoming convoluted, the return endpoint(User/Admin) means "or" between them. Additionally, the same abbreviation was applied for the Controller object that represents many controllers: AdminController, UserController, and PublicController. In the sequence diagram, the Controller is essentially an abstracted reference to the actual controllers but due to size of diagram it would have been difficult to read. Note that missing role also can be a default role that is not supposed to access that point either.

### Activity diagram:
![Alt text](authorize_act.png)

This diagram is verifying that the user properly has access to the resource by verifying the role they claim to be. For the diagram of Authorization tactic, the logged in user or public user will enter an endpoint. The associated filter SecurityFilterChain will then map the user role, if authenticated to then grant access to that endpoint or if unauthenticated it will allow entry to the public endpoint. 

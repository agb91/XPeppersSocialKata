Exercise for XPeppers

How to run the application:

pull the github repository:
https://github.com/agb91/XPeppersSocialKata

Here there are two project: 
server side is in the folder xpeppers-social-kata-server
client side is in the folder xpeppers-social-kata-client

Required Environment:
I've installed java 1.8.0_171, I'm pretty sure that all java version >= 1.8 will be fine
I'm using Maven 3.3.9 (probably it will work with other maven versions)

Let's begin bootstrapping the server:
open a linux or windowns terminal, enter in the folder of the server xpeppers-social-kata-server. 
Enter the following 2 commands:
mvn clean package
mvn spring-boot:run

Now we can run one or more client console (one for each user that we wanna use)
enter in the folder xpeppers-social-kata-client
Enter the following 2 commands:
mvn clean package
mvn spring-boot:run

in general mvn clean package is needed just the first time in order to build the jar, after you can use only mvn spring-boot:run


General Requirements:

Social Networking Kata
----------------------

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.

### Scenarios

**Posting**: Alice can publish messages to a personal timeline

> \> Alice -> I love the weather today    
> \> Bob -> Damn! We lost!     
> \> Bob -> Good game though.    

**Reading**: Bob can view Alice’s timeline

> \> Alice    
> \> I love the weather today (5 minutes ago)    
> \> Bob    
> \> Good game though. (1 minute ago)     
> \> Damn! We lost! (2 minutes ago)    

**Following**: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

> \> Charlie -> I'm in New York today! Anyone wants to have a coffee?     
> \> Charlie follows Alice    
> \> Charlie wall    
> \> Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)    
> \> Alice - I love the weather today (5 minutes ago)    

> \> Charlie follows Bob    
> \> Charlie wall    
> \> Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)     
> \> Bob - Good game though. (1 minute ago)     
> \> Bob - Damn! We lost! (2 minutes ago)     
> \> Alice - I love the weather today (5 minutes ago)    

### General requirements 

- Application must use the console for input and output; 
- User submits commands to the application: 
    - posting: \<user name> -> \<message> 
    - reading: \<user name> 
    - following: \<user name> follows \<another user> 
    - wall: \<user name> wall 
- Don't worry about handling any exceptions or invalid commands. Assume that the user will always type the correct commands. Just focus on the sunny day scenarios.
- Use whatever language and frameworks you want. (provide instructions on how to run the application)
- **NOTE:** "posting:", "reading:", "following:" and "wall:" are not part of the command. All commands start with the user name.

**IMPORTANT:**  Implement the requirements focusing on **writing the best code** you can produce.

**CODE SUBMISSION:** Add the code to your own Github account and send us the link.

Exercise for XPeppers

Some overview:
The application has one server, that is the brain of the social network, and some clients, one client for every user. So if we wanna Alice, Bob and Charlie we bootstrap the server, and we bootstrap 3 clients, one for Alice, one for Bob, one for Charlie. The first thing that the client does is to ask you who you are, from that moment, that client has an identity, and cannot change it.

How to run the application:

Required Environment:
I've installed java 1.8.0_171, I'm pretty sure that all java version >= 1.8 will be fine
I'm using Maven 3.3.9 (probably it will work with other maven versions)

clone the github repository:
https://github.com/agb91/XPeppersSocialKata

Here there are two project: 
server side is in the folder xpeppers-social-kata-server
client side is in the folder xpeppers-social-kata-client

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

How to use the project:
follow the indication that the client application gives to you (about inserting your name, the command that you can use etc)

Comments about the project:

Why Spring Boot? 
Because it is an easy way to create a web based application and to release it. I've considered Python as an alternative but for a client-server based application I prefer Spring

Why did you use System.out.println and not a logging system?
Because of simplicity, this is a very little project, I don't need formatted logs and different levels

Why did you use both Optional and != null notation?
I'm used to use both of them, in that case I've chosen each time the easiest one. In general when I work on a project i follow the guidelines of the project

Why didn't you use the observer pattern to notify the follower when the followed posts a new message?
I've considered it, but I don't need to push notifications in that case, simply each user pulls them when he uses the "wall" command

Is the test coverage complete?
No it isn't. I've tried to be pragmatic, I've created some test cases just to check the main functionalities of the application at each refactor. In a production project I usually spend more time in tests than in the project.

Why Post is an object that belongs to User and is not an independent object?
I've considered that in this scenario an user can post a message only on his dashboard and without a receiver, so a post can be related only to one user, the sender.

Why you used POST for the rest request of "follow" and not PUT
Actually follow is idempotent and it is quite an "update" more than a "create" so it should be PUT but for the sake of simplicity I've preferred to use just GET and POST requests

General Requirements of the project:
see https://github.com/sandromancuso/social_networking_kata

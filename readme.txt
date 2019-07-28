Exercise with Spring Boot - Angular

this is an implementation (with some freedom in the interpretation of requirements) of this exercise:
see https://github.com/sandromancuso/social_networking_kata
done just to order to try the frameworks Spring Boot and Angular


Some overview:
The application has a server-side, that is the brain of the social network, and some clients, one client for every user. So if we wanna Alice, Bob and Charlie we bootstrap the server, and we bootstrap 3 clients, one for Alice, one for Bob, one for Charlie. 

In the repository there are two clients:
1) the Java based one
2) the Angular based one
Both are working, the first one is very pragmatic, the second one is more interesting.

How to run the application:

Required Environment:
I've installed java 1.8.0_171, I'm pretty sure that all java version >= 1.8 will be fine
My Angular version is 7.3.3, my TS version is 3.2.4
I'm using Maven 3.3.9 (probably it will work with other maven versions)

clone the github repository:
https://github.com/agb91/XPeppersSocialKata

Here there are three projects: 
server side is in the folder xpeppers-social-kata-server
client side java based is in the folder xpeppers-social-kata-client
client side angular based is in the folder  social-kata-angular-client

Let's begin bootstrapping the server:
open a linux or windowns terminal, enter in the folder of the server xpeppers-social-kata-server. 
Enter the following 2 commands:
mvn clean package
mvn spring-boot:run

Now we can run one or more client console (one for each user that we wanna use)

	A) for the Angular-based client:
enter in the folder social-kata-angular-client
run ng serve --open

	B) for the Java-based client:
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
No it isn't. I've tried to be pragmatic, I've created some test cases just to check the main functionalities of the application at each refactor. 

Why Post is an object that belongs to User and is not an independent object?
I've considered that in this scenario an user can post a message only on his dashboard and without a receiver, so a post can be related only to one user, the sender.

Why you used POST for the rest request of "follow" and not PUT
Actually follow is idempotent and it is quite an "update" more than a "create" so it should be PUT but for the sake of simplicity I've preferred to use just GET and POST requests

Why Angular?
Because it is quite simple to learn and I wanted do add a GUI to the project

What about the UI?
It is just very simple, my goal in this project was to improve my abilities as a software developer, I did not take care of the aesthetic aspect, that is absolutely to improve

What about the security?
there is some kind of authentication, but very basic, this is not the goal of the project





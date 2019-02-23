package xpeppers.social.xpeppers_social_kata_client.user_interaction;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import xpeppers.social.xpeppers_social_kata_client.model.CommandType;
import xpeppers.social.xpeppers_social_kata_client.rest_client.RESTClient;

@Component
public class UserClient {

	@Autowired
	UserClientServices service;

	@Autowired
	private RESTClient restClient;
	

	// take this info from application.properties
	@Value("${localhost}")
	private String baseUrl;

	private String READ_MESSAGE = "Enter the user that you want to see: ";
	private String POST_MESSAGE = "Enter the message that you want to post: ";
	private String FOLLOW_MESSAGE = "Enter the user that you want to follow: ";
	private String WALL_MESSAGE = "I'm goint to print the wall ";

	// until the user exits continue to ask him commands
	public void interactWithUser() {
		Scanner scanner = new Scanner(System.in);
		String username = service.askName(scanner);

		boolean again = true;
		while (again) {
			String commandName = service.askWhichCommand(scanner);

			switch (commandName.toLowerCase()) {
			case "read":
				restClient.callServer(service.getCommand(scanner, username, CommandType.READ, READ_MESSAGE, true), "message", baseUrl);
				break;
			case "post":
				restClient.callServer(service.getCommand(scanner, username, CommandType.POST, POST_MESSAGE, true), "message", baseUrl);
				break;
			case "follow":
				restClient.callServer(service.getCommand(scanner, username, CommandType.FOLLOW, FOLLOW_MESSAGE, true), "relation", baseUrl);
				break;
			case "wall":
				restClient.callServer(service.getCommand(scanner, username, CommandType.WALL, WALL_MESSAGE, false), "user", baseUrl);
				break;
			case "quit":
				again = false;
				break;
			default:
				System.out.println("inacceptable command");
				break;
			}
		}
		scanner.close();
	}

}

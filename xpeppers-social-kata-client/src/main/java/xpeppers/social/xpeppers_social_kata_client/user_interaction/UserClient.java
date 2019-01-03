package xpeppers.social.xpeppers_social_kata_client.user_interaction;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import xpeppers.social.xpeppers_social_kata_client.model.Command;
import xpeppers.social.xpeppers_social_kata_client.rest_client.RESTClient;

@Component
public class UserClient {
	
	private String READ_MESSAGE = "Enter the user that you want to see: ";
	private String POST_MESSAGE = "Enter the message that you want to post:";
	private String FOLLOW_MESSAGE = "Enter the user that you want to follow: ";
	private String WALL_MESSAGE = "I'm goint to print the wall ";

	private RESTClient restClient;
	

	public void setRestClient(RESTClient restClient) {
		this.restClient = restClient;
	}

	public void interactWithUser() {
		Scanner scanner = new Scanner(System.in);
		String username = askName(scanner);
		
		boolean again = true;
		while (again) {
			String commandName = askWhichCommand(scanner);
			System.out.println( "COMMAND: " + commandName );
			
			switch (commandName.toLowerCase()) {
				case "read":
					restClient.callServer( getCommand(scanner, username, READ_MESSAGE , true), "read");
					break;
				case "post":
					restClient.callServer(getCommand(scanner, username, POST_MESSAGE , true), "post");
					break;
				case "follow":
					restClient.callServer(getCommand(scanner, username, FOLLOW_MESSAGE, true), "follow");
					break;
				case "wall":
					restClient.callServer(getCommand(scanner, username, WALL_MESSAGE , false), "wall");
					break;
				case "quit":
					again = false;
					break;
				default:
					again = false;
					;
					break;
			}
		}
		scanner.close();
	}

	private String askName(Scanner scanner) {
		System.out.println("Enter your name: ");
		String username = scanner.nextLine();

		return username;
	}

	private String askWhichCommand(Scanner scanner) {
		System.out.println("What is yout command? you can insert: \nRead \nPost \nFollow \nWall \nQuit");
		String command = scanner.nextLine();
		//System.out.println( "inserted: " + command );
		return command;
	}

	private Command getCommand(Scanner scanner, String username, String whatToAsk, boolean hasTarget) {
		Command com = new Command();
		com.setSender(username);
		if( hasTarget )
		{
			System.out.println(whatToAsk);
			String target = scanner.nextLine();
			com.setTarget(target);
		}
		return com;

	}


}

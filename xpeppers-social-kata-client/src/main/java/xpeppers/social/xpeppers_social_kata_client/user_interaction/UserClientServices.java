package xpeppers.social.xpeppers_social_kata_client.user_interaction;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import xpeppers.social.xpeppers_social_kata_client.model.Command;
import xpeppers.social.xpeppers_social_kata_client.model.CommandType;

@Service
public class UserClientServices {
	
	
	public String askName(Scanner scanner) {
		System.out.println("Enter your name: ");
		String username = scanner.nextLine();

		return username;
	}

	public String askWhichCommand(Scanner scanner) {
		System.out.println("What is yout command? you can insert: \nRead \nPost \nFollow \nWall \nQuit");
		String command = scanner.nextLine();
		System.out.println( "you chose: " + command );
		return command;
	}

	public Command getCommand(Scanner scanner, String username, CommandType type, String whatToAsk, boolean hasTarget) {
		Command com = new Command();
		com.setSender(username);
		com.setType( type );
		if( hasTarget )
		{
			System.out.println(whatToAsk);
			String target = scanner.nextLine();
			com.setTarget(target);
		}
		return com;

	}

}

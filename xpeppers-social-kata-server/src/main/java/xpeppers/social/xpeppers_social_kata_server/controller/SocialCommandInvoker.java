package xpeppers.social.xpeppers_social_kata_server.controller;

import java.util.Optional;
import org.springframework.stereotype.Service;
import xpeppers.social.xpeppers_social_kata_server.command.Command;

@Service
public class SocialCommandInvoker {
		
	private Command command;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public String execute( )
	{
		if( Optional.of(command).isPresent() )
			return command.execute();
		else
			return "no command setter in invoker";
	}
	

}

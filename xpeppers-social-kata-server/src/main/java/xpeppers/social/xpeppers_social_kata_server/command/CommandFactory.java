package xpeppers.social.xpeppers_social_kata_server.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//is this class useful? now no, it is not, if in a future the commands will be in some way specialized 
//probably it would be 
@Service
public class CommandFactory {

	@Autowired
	private Command command;

	public Command getCommand(CommandType type, String sender, String target) {
		switch (type) {
			case POST: {
				setPostCommand(sender, target);
				return command;
			}
			case READ: {
				setReadCommand(sender, target);
				return command;
			}
			case FOLLOW: {
				setFollowCommand(sender, target);
				return command;
			}
			case WALL: {
				setWallCommand(sender);
				return command;
			}
			case GETALL:
			{
				setGetAllCommand();
				return command;
			}
		}

		return command;

	}

	private void setPostCommand(String sender, String target) {

		command.setSender(sender);
		command.setType(CommandType.POST);
		command.setTarget(target);
	}
	
	private void setGetAllCommand() {
		command.setType(CommandType.GETALL);
	}

	private void setReadCommand(String sender, String target) {
		command.setSender(sender);
		command.setType(CommandType.READ);
		command.setTarget(target);

	}

	private void setFollowCommand(String sender, String target) {
		command.setSender(sender);
		command.setType(CommandType.FOLLOW);
		command.setTarget(target);

	}

	private void setWallCommand(String sender) {

		command.setSender(sender);
		command.setType(CommandType.WALL);
	}
}

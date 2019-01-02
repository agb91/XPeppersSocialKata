package xpeppers.social.xpeppers_social_kata_server.command;

import org.springframework.stereotype.Service;



@Service
public class CommandFactory {

	public PostCommand getPostCommand(Command general) {
		PostCommand result = new PostCommand();

		result.setSender(general.getSender());
		result.setCommandType(CommandType.POST);
		result.setMessage(general.getTarget());

		return result;
	}

	public ReadCommand getReadCommand(Command general) {
		ReadCommand result = new ReadCommand();

		result.setSender(general.getSender());
		result.setCommandType(CommandType.READ);
		result.setTargetUser( general.getTarget() );

		return result;
	}
}

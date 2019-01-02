package xpeppers.social.xpeppers_social_kata_server.command;

public class PostCommand extends Command {

	public PostCommand(String sender, String target) {
		super(sender, target);
	}

	public PostCommand() {
		super();
	}

	private String message;
	private CommandType commandType;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

}

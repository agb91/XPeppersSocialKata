package xpeppers.social.xpeppers_social_kata_server.command;

public class FollowCommand extends Command {

	private CommandType commandType;

	public FollowCommand(String sender, String target) {
		super(sender, target);
	}

	public FollowCommand() {
		super();
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

}

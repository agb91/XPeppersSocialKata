package xpeppers.social.xpeppers_social_kata_server.command;

public class FollowCommand extends Command {

	private String targetUser;
	private CommandType commandType;

	public FollowCommand(String sender, String target) {
		super(sender, target);
	}

	public FollowCommand() {
		super();
	}

	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

}

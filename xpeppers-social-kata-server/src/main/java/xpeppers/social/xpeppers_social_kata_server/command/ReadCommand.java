package xpeppers.social.xpeppers_social_kata_server.command;

public class ReadCommand extends Command {

	private String targetUser;
	private CommandType commandType;

	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

	public ReadCommand(String sender, String target) {
		super(sender, target);
	}

	public ReadCommand() {
		super();
	}

	public ReadCommand(String sender) {
		super(sender);
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

}

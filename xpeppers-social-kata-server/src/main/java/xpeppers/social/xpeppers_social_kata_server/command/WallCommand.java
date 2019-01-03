package xpeppers.social.xpeppers_social_kata_server.command;

public class WallCommand extends Command {
	private CommandType commandType;

	public WallCommand(String sender) {
		super(sender);
	}

	public WallCommand() {
		super();
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}
	
}

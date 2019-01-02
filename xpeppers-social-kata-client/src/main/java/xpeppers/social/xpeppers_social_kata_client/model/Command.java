package xpeppers.social.xpeppers_social_kata_client.model;

/*
 * commands are:
 * posting: Alice -> Ciao sono alice
 * reading: Alice
 * following: Charlie follows Alice
 * Wall: Charlie wall
 */
public class Command {

	// every command has at least a type taken from an enum of
	// acceptable types and a sender
	// adding to this can have a target (user or message)
	protected String sender;
	protected String target;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
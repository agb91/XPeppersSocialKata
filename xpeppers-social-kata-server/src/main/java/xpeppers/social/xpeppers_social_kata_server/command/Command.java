package xpeppers.social.xpeppers_social_kata_server.command;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.models.User;
import xpeppers.social.xpeppers_social_kata_server.services.Printer;
import xpeppers.social.xpeppers_social_kata_server.services.SocialServiceReceiver;

/*
 * commands are:
 * posting: Alice -> Ciao sono alice
 * reading: Alice
 * following: Charlie follows Alice
 * Wall: Charlie wall
 */

@Component
public class Command {
	
	//this it the command off the commmand pattern

	@Autowired
	SocialServiceReceiver receiver;

	@Autowired
	Printer printer;

	// every command has at least a sender
	// adding to this can have a target (user or message)
	private String sender;
	private String target;
	private CommandType type;

	public String execute() {
		switch (type) {
			case POST: {
				receiver.addUserIfNotExistent(sender);
				receiver.addMessageToUser(sender, target);
				return "Post " + target + " added by " + sender;
			}
			case READ: {
				receiver.addUserIfNotExistent(sender);
				List<Post> posts = receiver.getReadPosts(target);
				return printer.formatPostToString(posts);
			}
			case FOLLOW: {
				receiver.addUserIfNotExistent(sender);
				boolean done = receiver.follow(sender, target);
				if (done) {
					return sender + " now follows : " + target;
				} else {
					return "inexistent user";
				}
			}
			case WALL: {
				receiver.addUserIfNotExistent(sender);
				List<Post> posts = receiver.getFollowedPosts(sender);
	
				return printer.formatPostToString(posts);
			}
			case GETALL: {
				Map<String, User> users = receiver.getUsers();
			
				return printer.formatUsersToString(users);
			}
		}
		
		return "";

	}

	public CommandType getType() {
		return type;
	}

	public void setType(CommandType type) {
		this.type = type;
	}

	public Command(String sender, String target, CommandType type) {
		this.sender = sender;
		this.target = target;
		this.type = type;
	}

	public Command(String sender, CommandType type) {
		this.sender = sender;
		this.type = type;
	}

	public Command() {
		super();
	}

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

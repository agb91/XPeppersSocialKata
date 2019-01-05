package xpeppers.social.xpeppers_social_kata_server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.models.User;
import xpeppers.social.xpeppers_social_kata_server.services.Printer;
import xpeppers.social.xpeppers_social_kata_server.services.SocialNetworkService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xpeppers.social.xpeppers_social_kata_server.command.Command;
import xpeppers.social.xpeppers_social_kata_server.command.CommandFactory;
import xpeppers.social.xpeppers_social_kata_server.command.FollowCommand;
import xpeppers.social.xpeppers_social_kata_server.command.PostCommand;
import xpeppers.social.xpeppers_social_kata_server.command.ReadCommand;
import xpeppers.social.xpeppers_social_kata_server.command.WallCommand;

@RestController
public class SocialController {

	@Autowired
	CommandFactory commandFactory;

	@Autowired
	SocialNetworkService socialService;

	@Autowired
	Printer printer;

	// just for debug use
	@RequestMapping("/")
	public String index() {
		return "social server works";
	}
	
	//just for test use
	@RequestMapping("/killAll")
	public void killer() {
		socialService.setUsers( new HashMap<String,User>() );
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String posting(@RequestBody Command command) {
		PostCommand post = commandFactory.getPostCommand(command);
		socialService.addUserIfNotExistent(post.getSender());
		socialService.addMessageToUser(post.getSender(), post.getMessage());
		return "Post " + post.getMessage() + " added by " + post.getSender();
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Command command = new Command(sender, target);
		ReadCommand read = commandFactory.getReadCommand(command);
		socialService.addUserIfNotExistent(read.getSender());
		List<Post> posts = socialService.getReadPosts(read.getTarget());
		return printer.formatPostToString(posts);
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public String follow(@RequestBody Command command) {
		FollowCommand follow = commandFactory.getFollowCommand(command);
		socialService.addUserIfNotExistent(follow.getSender());
		boolean done = socialService.follow(follow.getSender(), follow.getTarget());
		if (done) {
			return follow.getSender() + " now follows : " + follow.getTarget();
		} else {
			return "inexistent user";
		}

	}

	@RequestMapping(value = "/wall", method = RequestMethod.GET)
	public String wall(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Command command = new Command(sender, target);
		WallCommand wall = commandFactory.getWallCommand(command);
		socialService.addUserIfNotExistent(wall.getSender());
		List<Post> posts = socialService.getFollowedPosts(wall.getSender());

		return printer.formatPostToString(posts);
	}

	public Map<String, User> getUsers() {
		return socialService.getUsers();
	}

}

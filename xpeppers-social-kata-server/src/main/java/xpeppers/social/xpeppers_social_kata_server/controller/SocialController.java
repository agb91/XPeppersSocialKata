package xpeppers.social.xpeppers_social_kata_server.controller;

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

	@RequestMapping("/")
	public String index() {
		return "social server works";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String posting(@RequestBody Command command) {
		PostCommand post = commandFactory.getPostCommand(command);
		socialService.addUserIfNotExists(post.getSender());
		socialService.addMessageToUser(post.getSender(), post.getMessage());
		return "Post " + post.getMessage() + " added by " + post.getSender();
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Command command = new Command(sender, target);
		ReadCommand read = commandFactory.getReadCommand(command);
		User user = socialService.getUserByName(read.getTargetUser()).orElse(new User());

		List<Post> posts = user.getPosts();

		return printer.formatPostToString(posts);
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public String follow(@RequestBody Command command) {
		FollowCommand follow = commandFactory.getFollowCommand(command);
		boolean done = socialService.follow(follow.getSender(), follow.getTargetUser());
		if( done )
		{
			return follow.getSender() + " now follows : " + follow.getTargetUser();
		}
		else
		{
			return "inexistent user";
		}
		
		

	}

	@RequestMapping(value = "/wall", method = RequestMethod.GET)
	public String wall(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Command command = new Command(sender, target);
		WallCommand wall = commandFactory.getWallCommand(command);
		List<Post> posts = socialService.getFollowedPosts(wall.getSender());

		return printer.formatPostToString(posts);
	}

	public Map<String, User> getUsers() {
		return socialService.getUsers();
	}

}

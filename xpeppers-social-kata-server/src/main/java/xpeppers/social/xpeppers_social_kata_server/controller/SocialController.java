package xpeppers.social.xpeppers_social_kata_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	CommandFactory cf;

	@RequestMapping("/")
	public String index() {
		return "social server works";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String posting(@RequestBody Command command) {
		PostCommand post = cf.getPostCommand(command);
		System.out.println("message: " + post.getMessage());
		System.out.println("sender: " + post.getSender());
		return "posting a message";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Command command = new Command( sender, target );
		ReadCommand read = cf.getReadCommand(command);
		System.out.println("targetUser: " + read.getTargetUser());
		System.out.println("sender: " + read.getSender());
		return "read a user's messages ";
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public String follow(@RequestBody Command command) {
		FollowCommand read = cf.getFollowCommand(command);
		System.out.println("targetUser: " + read.getTargetUser());
		System.out.println("sender: " + read.getSender());
		return "following another user";
	}

	@RequestMapping(value = "/wall", method = RequestMethod.GET)
	public String wall(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Command command = new Command( sender, target );
		WallCommand read = cf.getWallCommand(command);
		System.out.println("sender: " + read.getSender());
		return "showing your wall";
	}

}

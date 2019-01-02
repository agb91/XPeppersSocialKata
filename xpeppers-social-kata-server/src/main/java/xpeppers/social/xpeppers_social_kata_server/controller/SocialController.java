package xpeppers.social.xpeppers_social_kata_server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xpeppers.social.xpeppers_social_kata_server.command.Command;
import xpeppers.social.xpeppers_social_kata_server.command.CommandFactory;
import xpeppers.social.xpeppers_social_kata_server.command.PostCommand;
import xpeppers.social.xpeppers_social_kata_server.command.ReadCommand;

@RestController
public class SocialController {

	@Autowired
	CommandFactory cf;

	Logger logger = LoggerFactory.getLogger(SocialController.class);

	@RequestMapping("/")
	public String index() {
		return "social server works";
	}

	@RequestMapping(value = "/posting", method = RequestMethod.POST)
	public String posting(@RequestBody Command command) {
		PostCommand post = cf.getPostCommand(command);
		logger.debug("message: " + post.getMessage());
		logger.debug("sender: " + post.getSender());
		return "posting a message";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(@RequestBody Command command) {
		ReadCommand read = cf.getReadCommand(command);
		logger.debug("targetUser: " + read.getTargetUser());
		logger.debug("sender: " + read.getSender());
		return "read a user's messages ";
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public String follow() {
		return "following another user";
	}

	@RequestMapping(value = "/wall", method = RequestMethod.GET)
	public String wall() {
		return "showing your wall";
	}

}

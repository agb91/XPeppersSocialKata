package xpeppers.social.xpeppers_social_kata_server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.models.User;
import xpeppers.social.xpeppers_social_kata_server.services.Printer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xpeppers.social.xpeppers_social_kata_server.command.Command;
import xpeppers.social.xpeppers_social_kata_server.command.CommandFactory;
import xpeppers.social.xpeppers_social_kata_server.command.CommandType;

@RestController
public class SocialController {

	@Autowired
	CommandFactory commandFactory;

	@Autowired
	SocialCommandInvoker invoker;

	// just for debug use
	@RequestMapping("/")
	public String index() {
		return "social server works";
	}

	/*
	 * //just for test use
	 * 
	 * @RequestMapping("/killAll") public void killer() { socialService.setUsers(
	 * new HashMap<String,User>() ); }
	 */

	@CrossOrigin
	@PostMapping(value = "/message")
	public String posting(@RequestBody Command command) {
		Command post = commandFactory.getCommand( 
				CommandType.POST , command.getSender(), command.getTarget());
		invoker.setCommand(post);
		return invoker.execute();
	}

	@CrossOrigin
	@GetMapping(value = "/message")
	public String read(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		
		Command read = commandFactory.getCommand( 
				CommandType.READ , sender, target);
		invoker.setCommand(read);
		return invoker.execute();
	}

	@CrossOrigin
	@PostMapping(value = "/relation")
	public String follow(@RequestBody Command command) {
		Command follow = commandFactory.getCommand( 
				CommandType.FOLLOW , command.getSender(), command.getTarget());
		invoker.setCommand(follow);
		return invoker.execute();

	}

	@CrossOrigin
	@GetMapping(value = "/user")
	public String wall(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Command wall = commandFactory.getCommand( 
				CommandType.WALL , sender, null);
		invoker.setCommand(wall);
		return invoker.execute();
	}

}

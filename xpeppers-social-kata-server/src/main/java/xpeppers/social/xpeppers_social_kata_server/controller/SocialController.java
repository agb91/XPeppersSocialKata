package xpeppers.social.xpeppers_social_kata_server.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public Response posting(@RequestBody Command command) {
		Response res = new Response();
		Command post = commandFactory.getCommand( 
				CommandType.POST , command.getSender(), command.getTarget());
		invoker.setCommand(post);
		res.setResponse(invoker.execute());
		return res;
	}

	@CrossOrigin
	@GetMapping(value = "/message")
	@ResponseBody
	public Response read(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Response res = new Response();
		Command read = commandFactory.getCommand( 
				CommandType.READ , sender, target);
		invoker.setCommand(read);
		res.setResponse(invoker.execute());
		return res;
	}

	@CrossOrigin
	@PostMapping(value = "/relation")
	@ResponseBody
	public Response follow(@RequestBody Command command) {
		Response res = new Response();
		Command follow = commandFactory.getCommand( 
				CommandType.FOLLOW , command.getSender(), command.getTarget());
		invoker.setCommand(follow);
		res.setResponse(invoker.execute());
		return res;

	}

	@CrossOrigin
	@GetMapping(value = "/user")
	@ResponseBody
	public Response wall(@RequestParam("sender") String sender, @RequestParam("target") String target) {
		Response res = new Response();
		Command wall = commandFactory.getCommand( 
				CommandType.WALL , sender, null);
		invoker.setCommand(wall);
		res.setResponse(invoker.execute());
		return res;
	}

}

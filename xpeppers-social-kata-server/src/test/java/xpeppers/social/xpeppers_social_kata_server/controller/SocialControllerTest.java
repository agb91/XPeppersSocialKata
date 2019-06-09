package xpeppers.social.xpeppers_social_kata_server.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xpeppers.social.xpeppers_social_kata_server.App;
import xpeppers.social.xpeppers_social_kata_server.command.Command;
import xpeppers.social.xpeppers_social_kata_server.command.CommandFactory;
import xpeppers.social.xpeppers_social_kata_server.models.User;
import xpeppers.social.xpeppers_social_kata_server.repo.UserLogin;
import xpeppers.social.xpeppers_social_kata_server.services.Printer;
import xpeppers.social.xpeppers_social_kata_server.services.SocialServiceReceiver;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { App.class })
@EnableConfigurationProperties
public class SocialControllerTest {

	@Autowired
	SocialController socialController;

	@Autowired
	SocialServiceReceiver receiver;

	private String firstUser = "mario";
	private String secondUser = "luigi";
	private String thirdUser = "carlo";
	private String nonSenseUser = "blabla";
	private String message = "ciao mondo";

	@Test
	public void postingTest() {
		Command postCommand = new Command();
		postCommand.setSender(firstUser);
		postCommand.setTarget(message);

		String result = socialController.posting(postCommand).getResponse();
		assertEquals("Post ciao mondo added by mario", result);

		postCommand.setSender(firstUser);
		postCommand.setTarget(null);
		result = socialController.posting(postCommand).getResponse();
		// write null as a post from my point is view is acceptable, I just
		// wanna avoid
		// nullpointers
		assertEquals("Post null added by mario", result);

		postCommand.setSender(null);
		postCommand.setTarget(null);
		result = socialController.posting(postCommand).getResponse();
		// just wanna avoid nullpointers
		assertEquals("Post null added by null", result);

		// clean after the tests
		receiver.setUsers(new HashMap<String, User>());

	}

	@Test
	public void readTest() {

		// before you post, after you read
		Command postCommand = new Command();
		postCommand.setSender(secondUser);
		postCommand.setTarget(message);

		String result = socialController.posting(postCommand).getResponse();
		result = socialController.read(firstUser, secondUser).getResponse();
		assertThat(result, CoreMatchers.containsString("luigi - ciao mondo"));

		result = socialController.read(firstUser, nonSenseUser).getResponse();
		assertEquals("", result);

		result = socialController.read(firstUser, null).getResponse();
		assertEquals("", result);

		result = socialController.read(null, null).getResponse();
		assertEquals("", result);

		receiver.setUsers(new HashMap<String, User>());
	}

	@Test
	public void followTest() {

		Command postCommand = new Command();
		postCommand.setSender(secondUser);
		postCommand.setTarget(message);
		String result = socialController.posting(postCommand).getResponse();

		postCommand.setSender(firstUser);
		postCommand.setTarget(message);
		result = socialController.posting(postCommand).getResponse();

		Command followCommand = new Command();
		followCommand.setSender(firstUser);
		followCommand.setTarget(secondUser);
		result = socialController.follow(followCommand).getResponse();
		assertEquals("mario now follows : luigi", result);

		followCommand.setTarget(nonSenseUser);
		result = socialController.follow(followCommand).getResponse();
		assertEquals("inexistent user", result);

		followCommand.setTarget(null);
		result = socialController.follow(followCommand).getResponse();
		assertEquals("inexistent user", result);

		receiver.setUsers(new HashMap<String, User>());
	}

	@Test
	public void wallTest() {

		Command postCommand = new Command();
		postCommand.setSender(firstUser);
		postCommand.setTarget(message);
		String result = socialController.posting(postCommand).getResponse();

		postCommand.setSender(secondUser);
		postCommand.setTarget(message);
		result = socialController.posting(postCommand).getResponse();

		postCommand.setSender(thirdUser);
		postCommand.setTarget(message);
		result = socialController.posting(postCommand).getResponse();

		Command followCommand = new Command();
		followCommand.setSender(firstUser);
		followCommand.setTarget(secondUser);
		result = socialController.follow(followCommand).getResponse();
		result = socialController.wall(firstUser, secondUser).getResponse();

		assertThat(result, CoreMatchers.containsString("luigi"));
		assertThat(result, CoreMatchers.containsString("mario"));
		assertFalse(result.contains(thirdUser));

		result = socialController.wall(null, null).getResponse();
		assertEquals("", result);

		receiver.setUsers(new HashMap<String, User>());

	}

	@Test
	public void getAllUsersTest() {
		
		Map users = new HashMap<String, User>();
		receiver.setUsers(users);
		String result = socialController.getAllUsers().getResponse();
		assertEquals("", result);

		users.put("primo", new User());
		receiver.setUsers(users);
		result = socialController.getAllUsers().getResponse();
		assertEquals("primo, ", result);
		
		users.put("secondo", null);
		receiver.setUsers(users);
		result = socialController.getAllUsers().getResponse();
		assertEquals("primo, secondo, ", result);
		
		users.put(null, null);
		receiver.setUsers(users);
		result = socialController.getAllUsers().getResponse();
		assertEquals("primo, secondo, ", result);

		
		// clean after the tests
		receiver.setUsers(new HashMap<String, User>());

	}

}

package xpeppers.social.xpeppers_social_kata_server.controller;

import static org.junit.Assert.*;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xpeppers.social.xpeppers_social_kata_server.command.Command;
import xpeppers.social.xpeppers_social_kata_server.command.CommandFactory;
import xpeppers.social.xpeppers_social_kata_server.services.Printer;
import xpeppers.social.xpeppers_social_kata_server.services.SocialNetworkService;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SocialController.class, Printer.class, SocialNetworkService.class, CommandFactory.class,
		TimeManager.class })
@EnableConfigurationProperties
public class SocialControllerTest {

	@Autowired
	SocialController socialController;

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

		String result = socialController.posting(postCommand);
		assertEquals("Post ciao mondo added by mario", result);

		postCommand.setSender(firstUser);
		postCommand.setTarget(null);
		result = socialController.posting(postCommand);
		// write null as a post from my point is view is acceptable, I just
		// wanna avoid
		// nullpointers
		assertEquals("Post null added by mario", result);

		postCommand.setSender(null);
		postCommand.setTarget(null);
		result = socialController.posting(postCommand);
		// just wanna avoid nullpointers
		assertEquals("Post null added by null", result);
		
		//clean after the tests
		socialController.killer();

	}

	@Test
	public void readTest() {

		//before you post, after you read
		Command postCommand = new Command();
		postCommand.setSender(secondUser);
		postCommand.setTarget(message);

		String result = socialController.posting(postCommand);
		result = socialController.read(firstUser, secondUser);
		assertThat(result, CoreMatchers.containsString("luigi - ciao mondo"));

		result = socialController.read(firstUser, nonSenseUser);
		assertEquals("", result);

		result = socialController.read(firstUser, null);
		assertEquals("", result);

		result = socialController.read(null, null);
		assertEquals("", result);
		
		socialController.killer();
	}

	@Test
	public void followTest() {

		Command postCommand = new Command();
		postCommand.setSender(secondUser);
		postCommand.setTarget(message);
		String result = socialController.posting(postCommand);

		postCommand.setSender(firstUser);
		postCommand.setTarget(message);
		result = socialController.posting(postCommand);

		Command followCommand = new Command();
		followCommand.setSender(firstUser);
		followCommand.setTarget(secondUser);
		result = socialController.follow(followCommand);
		assertEquals("mario now follows : luigi", result);

		followCommand.setTarget(nonSenseUser);
		result = socialController.follow(followCommand);
		assertEquals("inexistent user", result);
		
		followCommand.setTarget(null);
		result = socialController.follow(followCommand);
		assertEquals("inexistent user", result);
		
		socialController.killer();
	}

	@Test
	public void wallTest() {

		Command postCommand = new Command();
		postCommand.setSender(firstUser);
		postCommand.setTarget(message);
		String result = socialController.posting(postCommand);

		postCommand.setSender(secondUser);
		postCommand.setTarget(message);
		result = socialController.posting(postCommand);

		postCommand.setSender(thirdUser);
		postCommand.setTarget(message);
		result = socialController.posting(postCommand);

		Command followCommand = new Command();
		followCommand.setSender(firstUser);
		followCommand.setTarget(secondUser);
		result = socialController.follow(followCommand);
		result = socialController.wall(firstUser, secondUser);

		assertThat(result, CoreMatchers.containsString("luigi"));
		assertThat(result, CoreMatchers.containsString("mario"));
		assertFalse(result.contains(thirdUser));
		
		
		result = socialController.wall(null, null);
		assertEquals("", result);
		
		socialController.killer();

	}

}

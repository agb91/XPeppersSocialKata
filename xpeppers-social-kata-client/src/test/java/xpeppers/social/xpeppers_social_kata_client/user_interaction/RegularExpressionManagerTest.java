package xpeppers.social.xpeppers_social_kata_client.user_interaction;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpeppers.social.xpeppers_social_kata_client.model.Command;
import xpeppers.social.xpeppers_social_kata_client.model.CommandType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RegularExpressionManager.class })
@EnableConfigurationProperties
public class RegularExpressionManagerTest {

	@Autowired
	RegularExpressionManager regExpService;

	private String target = "Mario";
	private String sender = "Luigi";
	private String message = "ciaone mondo";
	private String read = "Franco";
	private String followCommand = sender + " follows " + target;
	private String postCommand = sender + "-> " + message;
	private String wallCommand = sender + " wall";

	private String arrow = "->";
	private String follows = "follow";
	private String wall = "wall$";

	@Test
	public void getAllMatchesTest() {

		List<String> matches = new ArrayList<String>();

		matches = regExpService.getAllMatches("wall ,mario", wall);
		assertEquals(0, matches.size());
		matches = regExpService.getAllMatches("mario wall", wall);
		assertEquals(1, matches.size());

		matches = regExpService.getAllMatches("bla ,mario", arrow);
		assertEquals(0, matches.size());
		matches = regExpService.getAllMatches("mario -> luigi", arrow);
		assertEquals(1, matches.size());

		matches = regExpService.getAllMatches("bla ,mario", follows);
		assertEquals(0, matches.size());
		matches = regExpService.getAllMatches("mario follows luigi", follows);
		assertEquals(1, matches.size());

	}

	@Test
	public void getCommandTest() {
		Command result = regExpService.getCommand(sender, read);
		assertEquals(CommandType.READ, result.getType());
		assertEquals(sender, result.getSender());
		assertEquals(read, result.getTarget());

		result = regExpService.getCommand(sender, followCommand);
		assertEquals(CommandType.FOLLOW, result.getType());
		assertEquals(sender, result.getSender());
		assertEquals(target, result.getTarget());

		result = regExpService.getCommand(sender, postCommand);
		assertEquals(CommandType.POST, result.getType());
		assertEquals(sender, result.getSender());
		assertEquals(message, result.getTarget());

		result = regExpService.getCommand(sender, wallCommand);
		assertEquals(CommandType.WALL, result.getType());
		assertEquals(sender, result.getSender());
		assertEquals(sender, result.getTarget());
		
		

	}

}

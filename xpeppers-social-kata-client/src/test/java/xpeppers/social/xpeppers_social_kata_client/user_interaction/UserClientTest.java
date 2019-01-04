package xpeppers.social.xpeppers_social_kata_client.user_interaction;
import xpeppers.social.xpeppers_social_kata_client.main.App;
import xpeppers.social.xpeppers_social_kata_client.model.Command;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = App.class)
public class UserClientTest {
	
	UserClientServices service = new UserClientServices();
	
	@Test
	public void getCommand() {
		
		String target = "Mario";
		String sender = "Luigi";
		
		ByteArrayInputStream in = new ByteArrayInputStream(target.getBytes());
		System.setIn(in);
		
		Scanner scanner = new Scanner(System.in);
		Command command = service.getCommand(scanner, sender, "chi segui?", true);
		
		assertEquals(sender, command.getSender());
		assertEquals("aaa", "aaa");
	}
	


}

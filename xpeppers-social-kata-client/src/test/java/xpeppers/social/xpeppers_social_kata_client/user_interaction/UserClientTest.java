package xpeppers.social.xpeppers_social_kata_client.user_interaction;

import xpeppers.social.xpeppers_social_kata_client.model.Command;
import xpeppers.social.xpeppers_social_kata_client.model.CommandType;
import xpeppers.social.xpeppers_social_kata_client.rest_client.RESTClient;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UserClientServices.class, RESTClient.class, RestTemplate.class })
@EnableConfigurationProperties
public class UserClientTest {

	@Autowired
	UserClientServices service;

	private String target = "Mario";
	private String sender = "Luigi";

	@Test
	public void getCommand() {

		ByteArrayInputStream in = new ByteArrayInputStream(target.getBytes());
		System.setIn(in);

		Scanner scanner = new Scanner(System.in);
		Command command = service.getCommand(scanner, sender, CommandType.FOLLOW, "chi segui?", true);

		assertEquals(sender, command.getSender());
	}

}

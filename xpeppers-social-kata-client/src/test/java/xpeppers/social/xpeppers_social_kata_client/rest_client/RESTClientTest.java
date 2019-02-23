package xpeppers.social.xpeppers_social_kata_client.rest_client;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

import xpeppers.social.xpeppers_social_kata_client.model.Answer;
import xpeppers.social.xpeppers_social_kata_client.model.Command;
import xpeppers.social.xpeppers_social_kata_client.model.CommandType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RESTClient.class, RestTemplate.class })
@EnableConfigurationProperties
public class RESTClientTest {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RESTClient restClient;

	private String sender = "mario";
	private String target = "luigi";

	@Test
	public void callServerTest() {

		restClient.setRestTemplate(restTemplate);

		Command commandRead = new Command();
		commandRead.setSender(sender);
		commandRead.setType(CommandType.READ);
		commandRead.setTarget(target);
		
		Command commandPost = new Command();
		commandPost.setSender(sender);
		commandPost.setType(CommandType.POST);
		commandPost.setTarget(target);

		Command commandWall = new Command();
		commandWall.setType(CommandType.WALL);
		commandWall.setSender(sender);
		
		Command commandFollow = new Command();
		commandFollow.setSender(sender);
		commandFollow.setType(CommandType.FOLLOW);
		commandFollow.setTarget(target);

		// read
		Answer answer = restClient.callServer(commandRead, "message", "http://blabla:8080");
		assertEquals("http://blabla:8080/message?sender=mario&target=luigi", answer.getUrl());
		assertEquals(0, answer.getParams().size());

		// wall
		answer = restClient.callServer(commandWall, "user", "http://blabla:8080");
		assertEquals("http://blabla:8080/user?sender=mario&target", answer.getUrl());
		assertEquals(0, answer.getParams().size());

		// post
		answer = restClient.callServer(commandPost, "message", "http://blabla:8080");
		assertEquals("http://blabla:8080/message", answer.getUrl());
		assertEquals(sender, answer.getParams().get(0));
		assertEquals(target, answer.getParams().get(1));

		// follow
		answer = restClient.callServer(commandFollow, "relation", "http://blabla:8080");
		assertEquals("http://blabla:8080/relation", answer.getUrl());
		assertEquals(sender, answer.getParams().get(0));
		assertEquals(target, answer.getParams().get(1));


		
	}

}

package xpeppers.social.xpeppers_social_kata_client.rest_client;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

import xpeppers.social.xpeppers_social_kata_client.model.Answer;
import xpeppers.social.xpeppers_social_kata_client.model.Command;

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

		Command command = new Command();
		command.setSender(sender);
		command.setTarget(target);

		Command commandOnlySender = new Command();
		commandOnlySender.setSender(sender);

		// read
		Answer answer = restClient.callServer(command, "read", "http://blabla:8080");
		assertEquals("http://blabla:8080/read?sender=mario&target=luigi", answer.getUrl());
		assertEquals(0, answer.getParams().size());

		// wall
		answer = restClient.callServer(commandOnlySender, "wall", "http://blabla:8080");
		assertEquals("http://blabla:8080/wall?sender=mario&target", answer.getUrl());
		assertEquals(0, answer.getParams().size());

		// post
		answer = restClient.callServer(command, "post", "http://blabla:8080");
		assertEquals("http://blabla:8080/post", answer.getUrl());
		assertEquals(sender, answer.getParams().get(0));
		assertEquals(target, answer.getParams().get(1));

		// follow
		answer = restClient.callServer(command, "follow", "http://blabla:8080");
		assertEquals("http://blabla:8080/follow", answer.getUrl());
		assertEquals(sender, answer.getParams().get(0));
		assertEquals(target, answer.getParams().get(1));


		
	}

}

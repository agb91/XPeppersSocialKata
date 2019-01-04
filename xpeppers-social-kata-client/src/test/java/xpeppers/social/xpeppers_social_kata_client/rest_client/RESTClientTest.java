package xpeppers.social.xpeppers_social_kata_client.rest_client;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

import xpeppers.social.xpeppers_social_kata_client.model.Answer;
import xpeppers.social.xpeppers_social_kata_client.model.Command;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RESTClientTest {

	private RestTemplate restTemplate = new RestTemplate();
	private RESTClient restClient = new RESTClient();
	private String sender = "mario";
	private String target = "luigi";

	@Test
	public void getCommand() {

		restClient.setRestTemplate(restTemplate);

		Command command = new Command();
		command.setSender(sender);
		command.setTarget(target);
		
		Command commandOnlySender = new Command();
		commandOnlySender.setSender(sender);

		//get
		Answer answer = restClient.callServer(command, "read");
		assertEquals("http://localhost:8080/read?sender=mario&target=luigi", answer.getUrl());
		assertEquals(answer.getParams().size(), 0);

		//wall
		answer = restClient.callServer(commandOnlySender, "wall");
		assertEquals("http://localhost:8080/wall?sender=mario&target", answer.getUrl());
		assertEquals(answer.getParams().size(), 0);

		
		//post
		answer = restClient.callServer(command, "post");
		assertEquals("http://localhost:8080/post", answer.getUrl());
		assertEquals(answer.getParams().get(0), sender);
		assertEquals(answer.getParams().get(1), target);

		answer = restClient.callServer(command, "follow");
		assertEquals("http://localhost:8080/follow", answer.getUrl());
		assertEquals(answer.getParams().get(0), sender);
		assertEquals(answer.getParams().get(1), target);

	}

}

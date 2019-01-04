package xpeppers.social.xpeppers_social_kata_client.rest_client;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

import xpeppers.social.xpeppers_social_kata_client.model.Answer;
import xpeppers.social.xpeppers_social_kata_client.model.Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void getCommand() {

		restClient.setRestTemplate(restTemplate);

		Command command = new Command();
		command.setSender(sender);
		command.setTarget(target);

		Command commandOnlySender = new Command();
		commandOnlySender.setSender(sender);

		// get
		Answer answer = restClient.callServer(command, "read");
		assertEquals("http://localhost:8080/read?sender=mario&target=luigi", answer.getUrl());
		assertEquals(0, answer.getParams().size());

		// wall
		answer = restClient.callServer(commandOnlySender, "wall");
		assertEquals("http://localhost:8080/wall?sender=mario&target", answer.getUrl());
		assertEquals(0, answer.getParams().size());

		// post
		answer = restClient.callServer(command, "post");
		assertEquals("http://localhost:8080/post", answer.getUrl());
		assertEquals(sender, answer.getParams().get(0));
		assertEquals(target, answer.getParams().get(1));

		answer = restClient.callServer(command, "follow");
		assertEquals("http://localhost:8080/follow", answer.getUrl());
		assertEquals(sender, answer.getParams().get(0) );
		assertEquals(target, answer.getParams().get(1) );

	}

}

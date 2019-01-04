package xpeppers.social.xpeppers_social_kata_server.services;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.hamcrest.CoreMatchers;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xpeppers.social.xpeppers_social_kata_server.command.CommandFactory;
import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.models.User;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Printer.class, SocialNetworkService.class, CommandFactory.class, TimeManager.class })
@EnableConfigurationProperties
public class SocialNetworkServiceTest {

	@Autowired
	SocialNetworkService socialNetworkService;

	private String user1 = "mario";
	private String user2 = "luigi";
	private String nonsenseUser = "blabla";
	private String nuovo = "right now";
	private String middle = "some time ago";
	private String old = "a lot of time ago..";
	private String message = "some hello world";

	@Before
	public void clean() {
		socialNetworkService.setUsers(new HashMap<String, User>());
	}

	@Test
	public void sorterTest() {
		List<Post> posts = new ArrayList<Post>();
		DateTime nowTime = new DateTime();
		DateTime middleTime = new DateTime(2015, 3, 26, 12, 0, 0, 0);
		DateTime oldTime = new DateTime(2011, 3, 26, 12, 0, 0, 0);

		posts.add(new Post(user2, middle, middleTime));
		posts.add(new Post(user1, old, oldTime));
		posts.add(new Post(user1, nuovo, nowTime));

		posts = socialNetworkService.sorter(posts);

		assertEquals(nuovo, posts.get(0).getText());
		assertEquals(middle, posts.get(1).getText());
		assertEquals(old, posts.get(2).getText());
	}

	@Test
	public void addUserIfNotExistsTest() {

		assertEquals(0, socialNetworkService.getUsers().size());
		socialNetworkService.addUserIfNotExists(user1);
		socialNetworkService.addUserIfNotExists(user1);
		socialNetworkService.addUserIfNotExists(user1);
		assertEquals(1, socialNetworkService.getUsers().size());

		socialNetworkService.addUserIfNotExists(user2);
		socialNetworkService.addUserIfNotExists(user1);
		socialNetworkService.addUserIfNotExists(user2);
		assertEquals(2, socialNetworkService.getUsers().size());

	}

	@Test
	public void followTest() {

		socialNetworkService.addUserIfNotExists(user1);
		socialNetworkService.addUserIfNotExists(user2);

		// remember that everybody at least is a follower of himself
		assertEquals(1, socialNetworkService.getFollowedByUser(user1).size());
		// this user doesn't exist..
		assertEquals(0, socialNetworkService.getFollowedByUser(nonsenseUser).size());
		socialNetworkService.follow(user1, user2);
		assertEquals(2, socialNetworkService.getFollowedByUser(user1).size());
		socialNetworkService.follow(user1, user2);
		socialNetworkService.follow(user2, user1);
		assertEquals(2, socialNetworkService.getFollowedByUser(user1).size());
		assertEquals(2, socialNetworkService.getFollowedByUser(user2).size());

	}

	@Test
	public void getFollowedPosts() {

		socialNetworkService.addUserIfNotExists(user1);
		socialNetworkService.addUserIfNotExists(user2);

		socialNetworkService.addMessageToUser(user1, message);
		socialNetworkService.addMessageToUser(user2, message);

		assertEquals(1, socialNetworkService.getFollowedPosts(user1).size());
		socialNetworkService.follow(user1, user2);
		assertEquals(2, socialNetworkService.getFollowedPosts(user1).size());

		socialNetworkService.follow(user1, user2);
		socialNetworkService.follow(user2, user1);
		assertEquals(2, socialNetworkService.getFollowedPosts(user1).size());
		assertEquals(2, socialNetworkService.getFollowedPosts(user2).size());

	}

}
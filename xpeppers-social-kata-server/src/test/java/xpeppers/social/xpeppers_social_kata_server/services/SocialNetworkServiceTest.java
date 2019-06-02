package xpeppers.social.xpeppers_social_kata_server.services;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xpeppers.social.xpeppers_social_kata_server.App;
import xpeppers.social.xpeppers_social_kata_server.command.Command;
import xpeppers.social.xpeppers_social_kata_server.command.CommandFactory;
import xpeppers.social.xpeppers_social_kata_server.controller.SocialAuthenticator;
import xpeppers.social.xpeppers_social_kata_server.controller.SocialCommandInvoker;
import xpeppers.social.xpeppers_social_kata_server.controller.SocialController;
import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.models.User;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { App.class })
@EnableConfigurationProperties
public class SocialNetworkServiceTest {

	@Autowired
	SocialServiceReceiver socialNetworkService;

	@Autowired
	SocialController socialController;

	private String user1 = "mario";
	private String user2 = "luigi";
	private String nonsenseUser = "blabla";
	private String nuovo = "right now";
	private String middle = "some time ago";
	private String old = "a lot of time ago..";
	private String message = "some hello world";

	// in that case I prefer to have a clean social network for every test
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

		// expect to filter null posts...
		posts.add(new Post(user2, middle, middleTime));
		posts.add(new Post(user1, old, oldTime));
		posts.add(new Post(user1, nuovo, nowTime));
		posts.add(new Post(user1, null, null));

		posts = socialNetworkService.sorter(posts);

		assertEquals(3, posts.size());
		assertEquals(nuovo, posts.get(0).getText());
		assertEquals(middle, posts.get(1).getText());
		assertEquals(old, posts.get(2).getText());
		socialNetworkService.setUsers(new HashMap<String, User>());
	}

	@Test
	public void addUserIfNotExistentTest() {

		assertEquals(0, socialNetworkService.getUsers().size());
		socialNetworkService.addUserIfNotExistent(user1);
		socialNetworkService.addUserIfNotExistent(user1);
		socialNetworkService.addUserIfNotExistent(user1);
		assertEquals(1, socialNetworkService.getUsers().size());

		socialNetworkService.addUserIfNotExistent(user2);
		socialNetworkService.addUserIfNotExistent(user1);
		socialNetworkService.addUserIfNotExistent(user2);
		assertEquals(2, socialNetworkService.getUsers().size());

		// I wanna real names..
		socialNetworkService.addUserIfNotExistent(null);
		socialNetworkService.addUserIfNotExistent("");
		assertEquals(2, socialNetworkService.getUsers().size());
		socialNetworkService.setUsers(new HashMap<String, User>());
	}

	@Test
	public void addMessageToUser() {

		assertEquals(0, socialNetworkService.getUsers().size());
		socialNetworkService.addUserIfNotExistent(user1);
		socialNetworkService.addUserIfNotExistent(user2);
		socialNetworkService.addMessageToUser(user1, message);
		assertEquals(0, socialNetworkService.getUserByName(null).orElse(new User()).getPosts().size());
		assertEquals(0, socialNetworkService.getUserByName("").orElse(new User()).getPosts().size());
		assertEquals(1, socialNetworkService.getUserByName(user1).orElse(new User()).getPosts().size());
		assertEquals(0, socialNetworkService.getUserByName(user2).orElse(new User()).getPosts().size());
		socialNetworkService.addMessageToUser(user1, "");
		socialNetworkService.addMessageToUser(user1, null);
		socialNetworkService.addMessageToUser(user2, null);
		assertEquals(3, socialNetworkService.getUserByName(user1).orElse(new User()).getPosts().size());
		assertEquals(1, socialNetworkService.getUserByName(user2).orElse(new User()).getPosts().size());
		socialNetworkService.setUsers(new HashMap<String, User>());
	}

	@Test
	public void followTest() {

		socialNetworkService.addUserIfNotExistent(user1);
		socialNetworkService.addUserIfNotExistent(user2);

		// remember that everybody at least is a follower of himself
		assertEquals(1, socialNetworkService.getFollowedByUser(user1).size());
		// this user doesn't exist..
		assertEquals(0, socialNetworkService.getFollowedByUser(nonsenseUser).size());
		assertEquals(0, socialNetworkService.getFollowedByUser(null).size());
		socialNetworkService.follow(user1, user2);
		assertEquals(2, socialNetworkService.getFollowedByUser(user1).size());
		socialNetworkService.follow(user1, user2);
		socialNetworkService.follow(user2, user1);
		assertEquals(2, socialNetworkService.getFollowedByUser(user1).size());
		assertEquals(2, socialNetworkService.getFollowedByUser(user2).size());
		socialNetworkService.setUsers(new HashMap<String, User>());
	}

	@Test
	public void getFollowedPosts() {

		socialNetworkService.addUserIfNotExistent(user1);
		socialNetworkService.addUserIfNotExistent(user2);

		socialNetworkService.addMessageToUser(user1, message);
		socialNetworkService.addMessageToUser(user2, message);

		assertEquals(1, socialNetworkService.getFollowedPosts(user1).size());
		socialNetworkService.follow(user1, user2);
		assertEquals(2, socialNetworkService.getFollowedPosts(user1).size());
		assertEquals(0, socialNetworkService.getFollowedPosts(null).size());
		assertEquals(0, socialNetworkService.getFollowedPosts("").size());

		socialNetworkService.follow(user1, user2);
		socialNetworkService.follow(user2, user1);
		assertEquals(2, socialNetworkService.getFollowedPosts(user1).size());
		assertEquals(2, socialNetworkService.getFollowedPosts(user2).size());
		socialNetworkService.setUsers(new HashMap<String, User>());
	}

	@Test
	public void getReadPosts() {

		socialNetworkService.addUserIfNotExistent(user1);
		socialNetworkService.addUserIfNotExistent(user2);

		socialNetworkService.addMessageToUser(user1, message);
		socialNetworkService.addMessageToUser(user2, message);

		assertEquals(1, socialNetworkService.getReadPosts(user1).size());
		assertEquals(0, socialNetworkService.getReadPosts(null).size());
		assertEquals(0, socialNetworkService.getReadPosts("").size());

		socialNetworkService.addMessageToUser(user2, message);

		assertEquals(1, socialNetworkService.getReadPosts(user1).size());
		assertEquals(2, socialNetworkService.getReadPosts(user2).size());
		socialNetworkService.setUsers(new HashMap<String, User>());
	}

}

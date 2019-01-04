package xpeppers.social.xpeppers_social_kata_server.services;

import java.util.ArrayList;
import org.joda.time.DateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.models.User;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;

@Service
public class SocialNetworkService {

	@Autowired
	TimeManager timeManager;

	private Map<String, User> users = new HashMap<String, User>();

	public Map<String, User> getUsers() {
		return this.users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public Optional<User> getUserByName(String name) {
		return Optional.ofNullable(users.get(name));
	}

	public void addMessageToUser(String name, String postText) {
		if (users.containsKey(name)) {
			User thisUser = users.get(name);
			Post post = new Post(name, postText);
			thisUser.addPost(post);
			users.put(name, thisUser);
		}
	}

	// user follows followed
	public boolean follow(String user, String followed) {

		if (users.containsKey(user) && users.containsKey(followed)) {
			User thisUser = users.get(user);
			thisUser.addFollowed(followed);
			users.put(user, thisUser);
			return true;
		} else {
			return false;
		}

	}

	public Set<String> getFollowedByUser(String user) {
		if (users.containsKey(user)) {
			return users.get(user).getFollowed();
		} else {
			return new HashSet<String>();
		}
	}

	public List<Post> getFollowedPosts(String user) {
		List<User> followed = new ArrayList<User>();
		List<Post> posts = new ArrayList<Post>();

		if (users.containsKey(user)) {

			getFollowedByUser(user).forEach(name -> followed.add(getUserByName(name).orElse(new User())));
			followed.forEach(f -> posts.addAll(f.getPosts()));

		}

		return sorter(posts);
	}

	public List<Post> sorter(List<Post> posts) {
		Comparator<Post> postComparator = Comparator.comparing(Post::getTimestamp, (t1, t2) -> {
			return timeManager.compareDates(t1, t2);
		});

		Collections.sort(posts, postComparator);
		return posts;
	}

	public void addUserIfNotExists(String name) {

		if (!users.containsKey(name)) {
			User toAdd = new User(name);
			users.put(name, toAdd);
		}

	}
}

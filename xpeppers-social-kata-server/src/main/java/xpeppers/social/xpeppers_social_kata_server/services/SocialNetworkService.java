package xpeppers.social.xpeppers_social_kata_server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.models.User;

@Service
public class SocialNetworkService {

	private Map<String, User> users = new HashMap<String, User>();

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

	public void addUser(User toAdd) {
		String key = toAdd.getName();
		if (!users.containsKey(key))
			users.put(key, toAdd);
	}

	public Optional<User> getUserByName(String name) {
		return Optional.ofNullable( users.get(name) );
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
	public void follow(String user, String followed) {

		if (users.containsKey(user)) {
			User thisUser = users.get(user);
			thisUser.addFollowed(followed);
			users.put(user, thisUser);
		}

	}

	public List<Post> getFollowedPosts(String user) {
		List<User> followed = new ArrayList<User>();
		List<Post> posts = new ArrayList<Post>();
		
		if (users.containsKey(user)) {

			User thisUser = users.get(user);
			thisUser.getFollowed().forEach(name -> followed.add(getUserByName(name).orElse( new User() ) ));
			followed.forEach(f -> posts.addAll(f.getPosts()));

		}
		Collections.sort(posts);
		return posts;
	}

	public void addUserIfNotExists(String name) {


		if( !users.containsKey(name) )
		{
			User toAdd = new User(name);
			users.put( name , toAdd);
		}
		
	}
}

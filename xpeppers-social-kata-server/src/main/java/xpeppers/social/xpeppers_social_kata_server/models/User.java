package xpeppers.social.xpeppers_social_kata_server.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

	private String name;
	private Set<String> followed = new HashSet<String>();
	private List<Post> posts = new ArrayList<Post>();

	public User(String name) {
		this.name = name;
		//each real user follows at least himself
		followed.add(this.name);
	}

	//just to avoid nullpointer
	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getFollowed() {
		return followed;
	}

	public void setFollowed(Set<String> followed) {
		this.followed = followed;
	}

	public void addFollowed(String followed) {
		this.followed.add(followed);
	}

	public List<Post> getPosts() {
		
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addPost(Post post) {
		this.posts.add(post);
	}

}

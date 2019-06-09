package xpeppers.social.xpeppers_social_kata_server.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.models.User;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;

@Service
public class Printer {

	@Autowired
	TimeManager timeManager;

	public String format(Post post) {
		return "> " + post.getAuthor() + " - " + post.getText() + " ("
				+ timeManager.findTimeAgo(Optional.of(post.getTimestamp())) + " ago) \n";
	}

	public String formatPostToString(List<Post> posts) {

		posts = posts.stream()
				.filter(p -> p != null && p.getTimestamp() != null && p.getAuthor() != null && p.getText() != null)
				.collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		posts.stream().sequential().forEach(p -> sb.append(format(p)));
		return sb.toString();
	}

	public String formatUsersToString(Map<String, User> users) {
		StringBuilder sb = new StringBuilder();
		users.keySet().stream().filter( u -> u != null && !u.trim().equalsIgnoreCase("null")).forEach( u -> sb.append(u + ", ") );
		return sb.toString();
	}

}

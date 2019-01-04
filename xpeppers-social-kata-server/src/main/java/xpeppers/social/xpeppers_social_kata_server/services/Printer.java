package xpeppers.social.xpeppers_social_kata_server.services;

import java.util.List;
import java.util.function.Consumer;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;

@Service
public class Printer {
	
	@Autowired
	TimeManager timeManager;

	public String format(Post post) {
		return "> " + post.getAuthor() + " - " + post.getText() + " (" + timeManager.findTimeAgo( post.getTimestamp()) + " ago) \n";
	}

	public String formatPostToString(List<Post> posts) {

		StringBuilder sb = new StringBuilder();
		posts.stream().sequential().forEach(p -> sb.append(format(p)) );
		return sb.toString();
	}

}

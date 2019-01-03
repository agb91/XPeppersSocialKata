package xpeppers.social.xpeppers_social_kata_server.services;

import java.util.List;
import java.util.function.Consumer;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import xpeppers.social.xpeppers_social_kata_server.models.Post;

@Service
public class Printer {

	public String format(Post p) {
		return p.getAuthor() + " > " + p.getText() + " - ";// + p.getTimestamp().toString();
	}

	public String formatPostToString(List<Post> posts) {

		StringBuilder sb = new StringBuilder();
		posts.stream().sequential().forEach(p -> sb.append(format(p)).append("\n") );
		return sb.toString();
	}

}

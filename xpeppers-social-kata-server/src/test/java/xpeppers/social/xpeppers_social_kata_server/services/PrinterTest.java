package xpeppers.social.xpeppers_social_kata_server.services;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xpeppers.social.xpeppers_social_kata_server.models.Post;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Printer.class, TimeManager.class })
@EnableConfigurationProperties
public class PrinterTest {

	@Autowired
	Printer printer;

	@Autowired
	TimeManager timeManager;

	private String user = "mario";
	private String text = "ciao mondo blabla";
	private String textAlt = "altro testo";
	private String textTer = "ulteriore testo";

	@Test
	public void formatTest() {
		Post post = new Post(user, text);
		DateTime postTime = post.getTimestamp();
		assertNotNull(postTime);
		String formattedTime = timeManager.findTimeAgo(postTime);
		String formatted = printer.format(post);
		String expettedString = "> " + user + " - " + text + " (" + formattedTime + " ago) \n";
		assertEquals(expettedString, formatted);
	}

	@Test
	public void formatPostToString() {
		List<Post> posts = new ArrayList<Post>();
		posts.add(new Post(user, text));
		posts.add(new Post(user, textAlt));
		posts.add(new Post(user, textTer));

		String formatted = printer.formatPostToString(posts);

		assertThat(formatted, CoreMatchers.containsString(text));
		assertThat(formatted, CoreMatchers.containsString(textAlt));
		assertThat(formatted, CoreMatchers.containsString(textTer));
		
		

	}

}

package xpeppers.social.xpeppers_social_kata_server.models;

import org.springframework.beans.factory.annotation.Autowired;

import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;

import org.joda.time.DateTime;

public class Post implements Comparable<Post> {

	private String author;
	private String text;
	private DateTime timestamp;

	@Autowired
	TimeManager timeManager;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getOldnessInMills() {
		return timeManager.getDurationBreakdown(timestamp);

	}

	@Override
	public int compareTo(Post o) {
		return timeManager.compareDates(timestamp, o.getTimestamp());
	}

}

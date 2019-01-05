package xpeppers.social.xpeppers_social_kata_server.models;

import org.joda.time.DateTime;

public class Post {

	private String author;
	private String text;
	private DateTime timestamp;

	public Post(String user, String text) {
		this.author = user;
		this.text = text;
		this.timestamp = new DateTime();
	}

	public Post(String user, String text, DateTime dt) {
		this.author = user;
		this.text = text;
		if (dt != null) {
			this.timestamp = dt;
		} else {
			this.timestamp = new DateTime();
		}

	}

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

}

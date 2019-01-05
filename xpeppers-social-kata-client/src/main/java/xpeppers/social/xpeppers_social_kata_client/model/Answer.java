package xpeppers.social.xpeppers_social_kata_client.model;

import java.util.ArrayList;
import java.util.List;

//I want a string back from servers
public class Answer {

	private String answerText;

	// these two variables are useful just for testing purpose
	private String url;
	private List<String> params = new ArrayList<String>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public void addParams(String param) {
		this.params.add(param);
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	@Override
	public String toString() {
		if (answerText == null) {
			return "";
		}
		return answerText;
	}

}

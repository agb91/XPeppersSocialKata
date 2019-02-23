package xpeppers.social.xpeppers_social_kata_client.rest_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import xpeppers.social.xpeppers_social_kata_client.model.Answer;
import xpeppers.social.xpeppers_social_kata_client.model.Command;
import xpeppers.social.xpeppers_social_kata_client.model.CommandType;

@Component
public class RESTClient {

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// different for GET and POST
	// FOLLOW is idempotent, so should it be PUT? Yes, but because of simplicity
	// I prefer POST
	public Answer callServer(Command command, String offsetUrl, String baseUrl) {
		Answer ans = new Answer();
		String url = baseUrl + "/" + offsetUrl;
		if (command.getType().compareTo( CommandType.READ ) == 0 || command.getType().compareTo( CommandType.WALL ) == 0) // get
		{
			ans = callGet(command, url);

		} else // post
		{
			ans = callPost(command, url);
		}
		System.out.println(ans.toString());
		return ans;

	}

	private Answer callGet(Command command, String url) {
		Answer ans = new Answer();
		String text = "";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("sender", command.getSender())
				.queryParam("target", command.getTarget());
		ans.setUrl(builder.toUriString()); // just to improving testability
		// System.out.println(builder.toUriString());
		try {
			ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
					String.class);
			text = response.getBody();
		} catch (Exception e) {
			System.err.println("problem with request to: " + url + "\n");
		}
		ans.setAnswerText(text);
		return ans;
	}

	private Answer callPost(Command command, String url) {
		Answer ans = new Answer();
		String text = "";
		HttpEntity<Command> request = new HttpEntity<>(command);
		// just to improving testability
		ans.setUrl(url);
		ans.addParams(request.getBody().getSender());
		ans.addParams(request.getBody().getTarget());
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
			text = response.getBody();
		} catch (Exception e) {
			System.err.println("problem with request to: " + url + "\n");
		}
		ans.setAnswerText(text);
		return ans;
	}

}

package xpeppers.social.xpeppers_social_kata_client.rest_client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import xpeppers.social.xpeppers_social_kata_client.model.Answer;
import xpeppers.social.xpeppers_social_kata_client.model.Command;

@Component
public class RESTClient {

	private RestTemplate restTemplate;

	private String baseUrl = "http://localhost:8080";

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Answer callServer(Command command, String action) {
		Answer ans = new Answer();
		String url = baseUrl + "/" + action;
		//System.out.println("calling url: " + url + " with sender: " + command.getSender() + " and target:  "
		//		+ command.getTarget());
		try {
			String text = "";
			if (action.equalsIgnoreCase("read") || action.equalsIgnoreCase("wall")) // get
			{

				UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				        .queryParam("sender", command.getSender() )
				        .queryParam("target", command.getTarget() );
				ans.setUrl( builder.toUriString() ); // just to improving testability
				System.out.println( builder.toUriString() );
				ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
				text = response.getBody();
			} else // post
			{
				HttpEntity<Command> request = new HttpEntity<>(command);
				ans.setUrl( url ); // just to improving testability
				ans.addParams( request.getBody().getSender() );
				ans.addParams( request.getBody().getTarget() );
				ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
				text = response.getBody();
			}

			ans.setAnswerText(text);
		} catch (Exception e) {
			System.err.println("problem with request to: " + url + "\n");
		}
		System.out.println( ans.toString() );
		return ans;

	}

}

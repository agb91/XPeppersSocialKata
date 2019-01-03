package xpeppers.social.xpeppers_social_kata_client.rest_client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
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
		System.out.println("calling url: " + url + " with sender: " + command.getSender() + 
				" and target:  " + command.getTarget());
		try
		{
			String text = restTemplate.postForObject(url, command, String.class);
			
			ans.setText(text);
		}catch( Exception e )
		{
			System.err.println( "problem with request to: " + url );
		}
		ans.toString();
		return ans;
		
	}

}

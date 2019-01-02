package xpeppers.social.xpeppers_social_kata_client.rest_client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import xpeppers.social.xpeppers_social_kata_client.model.Answer;
import xpeppers.social.xpeppers_social_kata_client.model.Command;

@Component
public class RESTClient {

	public void call(RestTemplate restTemplate) {
		String url = "http://localhost:8080/posting";
		Command command = new Command();
		command.setSender("mario");
		command.setTarget("kills mushrooms");
		String text = restTemplate.postForObject(url, command, String.class);
		Answer ans = new Answer();
		ans.setText(text);
		System.out.println(ans.toString());
	}

}

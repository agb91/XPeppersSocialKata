package xpeppers.social.xpeppers_social_kata_client.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import xpeppers.social.xpeppers_social_kata_client.rest_client.RESTClient;
import xpeppers.social.xpeppers_social_kata_client.user_interaction.UserClient;

@SpringBootApplication
@ComponentScan("xpeppers.social.xpeppers_social_kata_client")
public class App {

	@Autowired
	private RESTClient restClient;
	
	@Autowired
	private UserClient userClient;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			restClient.setRestTemplate(restTemplate);
			userClient.setRestClient(restClient);
			userClient.interactWithUser();
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	
}

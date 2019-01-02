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

@SpringBootApplication
@ComponentScan("xpeppers.social.xpeppers_social_kata_client")
public class App {

	@Autowired
	private RESTClient restClient;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			restClient.call(restTemplate);
		};
	}
}

package xpeppers.social.xpeppers_social_kata_client.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import xpeppers.social.xpeppers_social_kata_client.user_interaction.UserClient;

/*
 * The responsability of this class is just to initialize the client, and to call interactWithUser of userClient,
 * that begins to ask commands to to user
 */
@SpringBootApplication
@ComponentScan("xpeppers.social.xpeppers_social_kata_client")
public class App {

	@Autowired
	private UserClient userClient;

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			userClient.interactWithUser();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

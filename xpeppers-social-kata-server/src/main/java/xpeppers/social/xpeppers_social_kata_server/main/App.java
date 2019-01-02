package xpeppers.social.xpeppers_social_kata_server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("xpeppers.social.xpeppers_social_kata_server")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

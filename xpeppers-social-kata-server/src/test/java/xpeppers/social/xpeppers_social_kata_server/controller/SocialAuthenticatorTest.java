package xpeppers.social.xpeppers_social_kata_server.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import xpeppers.social.xpeppers_social_kata_server.App;
import xpeppers.social.xpeppers_social_kata_server.command.Command;
import xpeppers.social.xpeppers_social_kata_server.command.CommandFactory;
import xpeppers.social.xpeppers_social_kata_server.repo.AuthRepository;
import xpeppers.social.xpeppers_social_kata_server.repo.UserLogin;
import xpeppers.social.xpeppers_social_kata_server.services.Printer;
import xpeppers.social.xpeppers_social_kata_server.services.SocialServiceReceiver;
import xpeppers.social.xpeppers_social_kata_server.utils.TimeManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { App.class })
@EnableConfigurationProperties
public class SocialAuthenticatorTest {

	@Autowired
	SocialAuthenticator socialAuthenticator;

	@Autowired
	AuthRepository auth;

	@Test
	public void authenticateTest() {

		// fixtures..
		auth.save(new UserLogin(1L, "mario", "mario"));

		UserLogin userOK = new UserLogin(1L, "mario", "mario");
		UserLogin userKO1 = new UserLogin(1L, "mario", "dasdasdas");
		UserLogin userKO2 = new UserLogin(1L, "fdsfdsfds", "mario");
		UserLogin userNull1 = new UserLogin(1L, "mario", null);
		UserLogin userNull2 = new UserLogin(1L, null, "mario");
		UserLogin userNull3 = new UserLogin(1L, null, null);

		assertTrue(socialAuthenticator.authenticate(userOK));
		assertFalse(socialAuthenticator.authenticate(userKO1));
		assertFalse(socialAuthenticator.authenticate(userKO2));
		assertFalse(socialAuthenticator.authenticate(userNull1));
		assertFalse(socialAuthenticator.authenticate(userNull2));
		assertFalse(socialAuthenticator.authenticate(userNull3));

	}

}

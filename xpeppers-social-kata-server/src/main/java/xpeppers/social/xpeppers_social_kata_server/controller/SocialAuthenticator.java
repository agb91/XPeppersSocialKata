package xpeppers.social.xpeppers_social_kata_server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xpeppers.social.xpeppers_social_kata_server.repo.AuthRepository;
//import xpeppers.social.xpeppers_social_kata_server.repo.AuthRepository;
import xpeppers.social.xpeppers_social_kata_server.repo.UserLogin;

@Service
public class SocialAuthenticator {

	@Autowired
	AuthRepository auth;
	
	public Boolean authenticate(UserLogin userInfo) {

		try {
			//just to test:
			auth.save(new UserLogin(1L, "mario", "mario"));
			auth.save(new UserLogin(2L, "luigi", "luigi"));
			
			
			Iterable<UserLogin> acceptable = auth.findAll();
			Map<String, String> userPass = mapper(acceptable);

			String expectedPass = userPass.get(userInfo.getUser());

			if (Optional.of(userInfo).orElse(new UserLogin()).getPassword().equalsIgnoreCase(expectedPass)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private Map<String, String> mapper(Iterable<UserLogin> acceptable) {

		Map<String, String> result = new HashMap<String, String>();

		try {
			for (UserLogin i : acceptable) {
				result.put(i.getUser(), i.getPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}

		return result;
	}

}

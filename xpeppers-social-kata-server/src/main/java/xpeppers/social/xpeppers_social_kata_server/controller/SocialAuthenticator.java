package xpeppers.social.xpeppers_social_kata_server.controller;

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
		
		try
		{
			if (Optional.of(userInfo).orElse(new UserLogin()).getUser().equalsIgnoreCase("mario")
					&& Optional.of(userInfo).orElse(new UserLogin()).getPassword().equalsIgnoreCase("mario")) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

}

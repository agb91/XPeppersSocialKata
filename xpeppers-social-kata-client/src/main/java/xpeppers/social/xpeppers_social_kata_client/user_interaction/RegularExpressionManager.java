package xpeppers.social.xpeppers_social_kata_client.user_interaction;

import org.springframework.stereotype.Service;

import xpeppers.social.xpeppers_social_kata_client.model.Command;
import xpeppers.social.xpeppers_social_kata_client.model.CommandType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

/*
 * post: Alice -> I love the weather today
 * read: Alice
 * follow: Charlie follows Alice
 * wall: Alice wall
 */
@Service
public class RegularExpressionManager {

	private String arrow = "->";
	private String follows = "follows";
	private String wall = "wall$";

	public Command getCommand(String user, String command) {

		List<String> matchWall = getAllMatches(command, wall); // I expect just
																// one match...
		if (matchWall.size() == 1) {
			return manageWall(user, command);
		}

		List<String> matchPost = getAllMatches(command, arrow); // I expect just
																// one match...
		if (matchPost.size() == 1) {
			return managePost(command);
		}

		List<String> matchFollows = getAllMatches(command, follows); // I expect
																		// just
																		// one
																		// match...
		if (matchFollows.size() == 1) {
			return manageFollows(command);
		}

		return manageRead(user, command);

	}

	private Command manageWall(String user, String command) {
		Command result = new Command();
		result.setType(CommandType.WALL);
		result.setSender(user);
		result.setTarget(command.split(wall)[0].trim());
		return result;
	}

	private Command manageRead(String user, String command) {
		Command result = new Command();
		result.setType(CommandType.READ);
		result.setSender(user);
		result.setTarget(command);
		return result;
	}

	private Command manageFollows(String command) {
		Command result = new Command();
		result.setType(CommandType.FOLLOW);
		result.setSender(command.split(follows)[0].trim());
		result.setTarget(command.split(follows)[1].trim());
		return result;
	}

	private Command managePost(String command) {
		Command result = new Command();
		result.setType(CommandType.POST);
		result.setSender(command.split(arrow)[0].trim());
		result.setTarget(command.split(arrow)[1].trim());
		return result;
	}

	public List<String> getAllMatches(String str, String regExp) {
		Pattern pattern = Pattern.compile(regExp);
		List<String> list = new ArrayList<String>();
		Matcher m = pattern.matcher(str);
		while (m.find()) {
			list.add(m.group());
		}
		return list;
	}
}

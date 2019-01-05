package xpeppers.social.xpeppers_social_kata_server.utils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
public class TimeManager {

	/*
	 * it finds how old is the timestamp in a convenient representation
	 */
	public String findTimeAgo(Optional<DateTime> timestamp) {

		DateTime now = new DateTime(); // it initializes in current timestamp
		Long millis = now.getMillis() - timestamp.orElse(new DateTime()).getMillis();

		long days = TimeUnit.MILLISECONDS.toDays(millis);
		millis -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

		StringBuilder sb = new StringBuilder(64);
		sb.append(days);
		sb.append(" Days ");
		sb.append(hours);
		sb.append(" Hours ");
		sb.append(minutes);
		sb.append(" Minutes ");
		sb.append(seconds);
		sb.append(" Seconds");

		return (sb.toString());
	}

	public int compareDates(DateTime t1, DateTime t2) {

		if (t1.isAfter(t2))
			return -1;
		else
			return 1;

	}

}

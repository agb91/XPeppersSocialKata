package xpeppers.social.xpeppers_social_kata_server.utils;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class TimeManager {

	// thanks stack-overflow
	/** 
     * Convert a millisecond duration to a string format
     * 
     * @param timestamp A duration to convert to a string form
     * @return A string of the form "X Days Y Hours Z Minutes A Seconds".
     */
    public String getDurationBreakdown(DateTime timestamp) {
    	
    	DateTime now = new DateTime();
		Long millis = now.getMillis() - timestamp.getMillis();
    	
        if(millis < 0) {
            throw new IllegalArgumentException("oldness must be greater than zero!");
        }

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

        return(sb.toString());
    }

	public int compareDates(DateTime t1, DateTime t2) {

		if( t1.isAfter( t2 ) )
			return 1;
		else
			return -1;
		
	}

}

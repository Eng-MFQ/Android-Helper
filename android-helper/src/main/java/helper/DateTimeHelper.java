package helper;

import java.util.Calendar;

/**
 * Created by Bayan on 5/18/2015.
 */
public class DateTimeHelper {

    public static long getCurrentTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }
}

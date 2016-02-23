package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ajla on 23-Feb-16.
 */
public class CommonHelper {

    /**
     * Returns provided date as a String value
     * @param date
     * @return
     */
    public static String getDateAsString(Date date){

        if (date == null) {
            return "Unknown date";
        }
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        String myDate = dtf.format(date);
        return myDate;
    }
}

package org.backend.staticdata;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertDate {

    public static String convertDateToString(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ts = sdf.format(timestamp);
        return ts;
    }

    public static LocalDateTime convertStringToDate(String ts) {
        LocalDateTime dateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (ts!=null){
            dateTime = LocalDateTime.parse(ts, formatter);
        }
        return dateTime;
    }
}

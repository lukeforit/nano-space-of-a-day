package me.lukeforit.spaceofaday.common;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//TODO replace with more elegant solution
public class Utils {

    @SuppressLint("SimpleDateFormat")
    public static SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDefaultDateAsString() {
        return defaultDateFormat.format(new Date());
    }

    public static int getDateAsInt(String date) {
        return Integer.parseInt(date.replace("-", ""));
    }

    public static int getDefaultDateAsId() {
        return getDateAsInt(getDefaultDateAsString());
    }

    public static List<String> getDatesInRange(String start, String end, int additional) throws ParseException {
        Date startDate = defaultDateFormat.parse(start);
        Date endDate = defaultDateFormat.parse(end);

        List<String> result = new ArrayList<>();
        result.add(end);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);

        while (calendar.getTime().after(startDate)) {
            calendar.add(Calendar.DATE, -1);
            result.add(defaultDateFormat.format(calendar.getTime()));
        }

        for (int i = 0; i < additional; i++) {
            calendar.add(Calendar.DATE, -1);
            result.add(defaultDateFormat.format(calendar.getTime()));
        }
        return result;
    }
}

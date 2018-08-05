package me.lukeforit.spaceofaday.common;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

//TODO replace with more elegant solution
public class Utils {
    @SuppressLint("SimpleDateFormat")
    public static String getDefaultDateAsString() {
        return new SimpleDateFormat("YYYY-MM-dd").format(new Date());
    }

    public static int getDateAsInt(String date) {
        return Integer.parseInt(date.replace("-", ""));
    }

    public static int getDefaultDateAsId() {
        return getDateAsInt(getDefaultDateAsString());
    }
}

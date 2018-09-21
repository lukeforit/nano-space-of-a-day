package me.lukeforit.spaceofaday.common

import android.annotation.SuppressLint

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date

//TODO replace with more elegant solution
object Utils {

    @SuppressLint("SimpleDateFormat")
    var defaultDateFormat = SimpleDateFormat("yyyy-MM-dd")

    val defaultDateAsString: String
        get() = defaultDateFormat.format(Date())

    val defaultDateAsId: Int
        get() = getDateAsInt(defaultDateAsString)

    fun getDateAsInt(date: String): Int {
        return Integer.parseInt(date.replace("-", ""))
    }

    @Throws(ParseException::class)
    fun getDatesInRange(start: String, end: String, additional: Int): List<String> {
        val startDate = defaultDateFormat.parse(start)
        val endDate = defaultDateFormat.parse(end)

        val result = ArrayList<String>()
        result.add(end)

        val calendar = Calendar.getInstance()
        calendar.time = endDate

        while (calendar.time.after(startDate)) {
            calendar.add(Calendar.DATE, -1)
            result.add(defaultDateFormat.format(calendar.time))
        }

        for (i in 0 until additional) {
            calendar.add(Calendar.DATE, -1)
            result.add(defaultDateFormat.format(calendar.time))
        }
        return result
    }
}

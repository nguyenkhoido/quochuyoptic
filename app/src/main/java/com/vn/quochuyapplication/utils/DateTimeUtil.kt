package com.vn.quochuyapplication.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {
    const val DATE_FOTMAT_DD_MM_YYYY = "dd/MM/yyyy"
    const val DATE_FORMAT_YYYY_MM_DD = "yyyy/MM/dd"
    var simpleDateFormatDDMMYY = SimpleDateFormat(DATE_FOTMAT_DD_MM_YYYY, Locale("vi-VN"))

    private fun getCalendarToday(): Calendar {
        return Calendar.getInstance()
    }

    fun getTimeStamp(): String {
        return simpleDateFormatDDMMYY.format(getCalendarToday().time)
    }

    fun getStringDateFromTimeStamp(time: Long): String? {
        val cal = Calendar.getInstance(Locale("vi-VN"))
        val timeD = Date(time)
        return simpleDateFormatDDMMYY.format(timeD)
    }

    /*fun getStringDateFromTimeStamp(t: Long): String? {
        val cal = Calendar.getInstance(Locale("vi-VN"))
        cal.timeInMillis = t * 1000L
        return simpleDateFormatDDMMYY.format(cal.time)
    }*/
}
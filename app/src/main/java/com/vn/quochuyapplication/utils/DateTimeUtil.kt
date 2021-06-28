package com.vn.quochuyapplication.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.internal.Intrinsics

object DateTimeUtil {
    const val DATE_FOTMAT_DD_MM_YYYY = "dd/MM/yyyy"

    var simpleDateFormatDDMMYY = SimpleDateFormat(DATE_FOTMAT_DD_MM_YYYY, Locale("vi-VN"))

    private fun getCalendarToday(): Calendar {
        return Calendar.getInstance()
    }

    fun getTimeStamp(): String {
        return simpleDateFormatDDMMYY.format(getCalendarToday().time)
    }

}
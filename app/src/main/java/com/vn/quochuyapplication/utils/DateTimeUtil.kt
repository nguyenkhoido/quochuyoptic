package com.vn.quochuyapplication.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.internal.Intrinsics

class DateTimeUtil private constructor() {
    val simpleDateFormat: DateFormat
        get() = Companion.simpleDateFormat
    val timeStamp: String
        get() {
            val dateFormat = Companion.simpleDateFormat
            val instance = Calendar.getInstance()
            Intrinsics.checkNotNullExpressionValue(instance, "Calendar.getInstance()")
            val format = dateFormat.format(instance.time)
            Intrinsics.checkNotNullExpressionValue(
                format,
                "simpleDateFormat.format(…endar.getInstance().time)"
            )
            return format
        }

    companion object {
        val INSTANCE = DateTimeUtil()
        val tIMESTAMP_FORMAT = "dd/MM/yyyy HH:mm:ss"
        private val simpleDateFormat = SimpleDateFormat.getDateTimeInstance()
    }
}
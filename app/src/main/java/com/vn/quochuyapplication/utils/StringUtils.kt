package com.vn.quochuyapplication.utils

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*

object StringUtils {
    private var currencyNumberFormat: NumberFormat? = null
    private const val patternVND = "###,###" // VND

    private var otherSymbols: DecimalFormatSymbols? = null // Ép về kiểu ###,###.00

    init {
        setCurrencyNumberFormat()
    }

    private fun setCurrencyNumberFormat() {
        currencyNumberFormat = NumberFormat.getNumberInstance(Locale("en", "US"))
        otherSymbols = DecimalFormatSymbols(Locale("en", "US"))
        otherSymbols?.decimalSeparator = '.'
        otherSymbols?.groupingSeparator = ','
    }

    fun formatCurrency(value: Double?): String? {
        return this.formatValue(value, false)
    }

    private fun formatValue(value: Double?, showPositiveSign: Boolean): String? {
        if (value == null || value == 0.0) {
            return "0"
        }
        var formattedCurrencyValue: String?
        formattedCurrencyValue = customFormatVND(value)
        if (showPositiveSign && value > 0) {
            formattedCurrencyValue = "+$formattedCurrencyValue"
        }
        return formattedCurrencyValue
    }


    fun customFormatVND(value: Double?): String {
        val myFormatter =
            DecimalFormat(patternVND)
        myFormatter.roundingMode = RoundingMode.FLOOR
        return myFormatter.format(value)
    }

}
package com.itneut.jjoo.utils

import android.icu.text.SimpleDateFormat
import java.text.NumberFormat
import java.util.Locale

object Format {
    fun date(
        inputDate: String,
        inputFormat: String = "yyyy-MM-dd",
        outputFormat: String = "EEEE, MMMM d yyyy",
        locale: Locale = Locale("es", "ES")
    ): String? {
        val inputDateFormat = SimpleDateFormat(inputFormat, locale)
        val date = inputDateFormat.parse(inputDate)
        val outputDateFormat = SimpleDateFormat(outputFormat, locale)
        return outputDateFormat.format(date)
    }
    fun amount(price: Double) = NumberFormat.getCurrencyInstance().format(price)

}
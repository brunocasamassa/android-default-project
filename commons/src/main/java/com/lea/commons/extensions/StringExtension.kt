package com.lea.commons.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


fun String.getSplitted(maxCharacters: Int): Array<String> {
    val name = this.trim()
    var first = name.replace("\"", "")
    var last = ""
    if(name.length > maxCharacters) {
        val lastBlank = name.substring(0, maxCharacters).lastIndexOf(" ")
        if (lastBlank > -1) {
            first = name.substring(0, maxCharacters).substring(0, lastBlank).replace("\"", "")
            last = name.substring(lastBlank, name.length).replace("\"", "")
        } else {
            first = name.substring(0, maxCharacters - 3).replace("\"", "") + "..."
            last = name.substring(maxCharacters - 3, name.length).replace("\"", "")
        }
    }
    if(last.length > maxCharacters){
        last = last.substring(0, maxCharacters-3).replace("\"", "") + "..."
    }
    return arrayOf(first, last)
}

fun String.getFirstNumber(): Int {
    val matcher: Matcher = Pattern.compile("\\d+").matcher(this)
    matcher.find()
    val i: Int = Integer.valueOf(matcher.group())
    return i
}

fun String.containsNumber(): Boolean{
    return this.matches(Regex(".*\\d.*"))
}

fun String?.getOrSafe():String{
    return this ?:""
}


fun String?.getCurrentlyData():String{
    val date = Calendar.getInstance().time

    var dateTimeFormat = SimpleDateFormat(this, Locale.getDefault())
    val hourAdjusted = (dateTimeFormat.format(date).split(":")[0].toInt()).toString()

    return "$hourAdjusted:${dateTimeFormat.format(date).split(":")[1]}"

}

fun String.toMoneySimbol(): String {
    val currency: Currency = Currency.getInstance(Locale("pt", "BR"))
    val symbol: String = currency.symbol
    return "$symbol $this"
}



const val DEFAULT_PATTERN_HOUR = "HH:mm - dd/MM/yy"
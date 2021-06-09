package com.avvsoft2050.coincryptoinfo.utils

import java.text.NumberFormat
import java.util.*

class Converter {
    companion object{
        fun toUSCurrency(num: Double?):String{
            if (num != null) {
                if (num < 1.0 ){
                    return "$$num"
                }
            return NumberFormat.getCurrencyInstance(Locale.US).format(num).toString()
            }else{
                return "нет данных"
            }
        }
        fun toSplitNumber(symbol:String, num: Double?):String{
            if (num != null) {
                return symbol + " " + NumberFormat.getNumberInstance(Locale.US).format(num).toString()
            }else{
                return "нет данных"
            }
        }
    }
}

//Use getInstance or getNumberInstance to get the normal number format.
//Use getIntegerInstance to get an integer number format.
//Use getCurrencyInstance to get the currency number format.
//And use getPercentInstance to get a format for displaying percentages.
//With this format, a fraction like 0.53 is displayed as 53%.
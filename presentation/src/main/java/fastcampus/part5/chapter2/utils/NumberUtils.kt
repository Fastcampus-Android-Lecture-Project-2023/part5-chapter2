package fastcampus.part5.chapter2.utils

import java.text.NumberFormat

object NumberUtils {
    fun numberFormatPrice(price: Int?) : String {
        return NumberFormat.getNumberInstance().format(price ?: 0)
    }
}
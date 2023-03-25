package fastcampus.part5.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fastcampus.part5.domain.model.BasketProduct
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class PurchaseHistoryConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromBasketProductList(value: List<BasketProduct>): String {
        return gson.toJson(value, object : TypeToken<List<BasketProduct>>() {}.type)
    }

    @TypeConverter
    fun toBasketProductList(value: String): List<BasketProduct> {
        return gson.fromJson(value, object : TypeToken<List<BasketProduct>>() {}.type)
    }

    @TypeConverter
    fun fromZonedDateTime(value: ZonedDateTime): String {
        return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(value)
    }

    @TypeConverter
    fun toZonedDateTime(value: String): ZonedDateTime {
        return ZonedDateTime.parse(value)
    }
}
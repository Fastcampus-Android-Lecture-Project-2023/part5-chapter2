package fastcampus.part5.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Price
import fastcampus.part5.domain.model.Shop

class BasketConverter {

    private val gson = GsonBuilder().create()

    @TypeConverter
    fun fromPrice(value: Price) : String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPrice(value: String) : Price {
        return gson.fromJson(value, Price::class.java)
    }

    @TypeConverter
    fun fromCategory(value: Category) : String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCategory(value: String) : Category {
        return gson.fromJson(value, Category::class.java)
    }

    @TypeConverter
    fun fromShop(value: Shop) : String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toShop(value: String) : Shop {
        return gson.fromJson(value, Shop::class.java)
    }
}
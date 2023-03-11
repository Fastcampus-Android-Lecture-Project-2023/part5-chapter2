package fastcampus.part5.data.deserializer

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import fastcampus.part5.domain.model.Banner
import fastcampus.part5.domain.model.BannerList
import fastcampus.part5.domain.model.BaseModel
import fastcampus.part5.domain.model.Carousel
import fastcampus.part5.domain.model.ModelType
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.Ranking
import java.lang.reflect.Type

class BaseModelDeserializer : JsonDeserializer<BaseModel> {
    companion object {
        private const val TYPE = "type"
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): BaseModel {
        val root = json?.asJsonObject
        val gson = GsonBuilder().create()

        val typeString = root?.get(TYPE)?.asString ?: ""

        return when(ModelType.valueOf(typeString)) {
            ModelType.BANNER -> gson.fromJson(root, Banner::class.java)
            ModelType.PRODUCT -> gson.fromJson(root, Product::class.java)
            ModelType.BANNER_LIST -> gson.fromJson(root, BannerList::class.java)
            ModelType.CAROUSEL -> gson.fromJson(root, Carousel::class.java)
            ModelType.RANKING -> gson.fromJson(root, Ranking::class.java)
        }
    }
}
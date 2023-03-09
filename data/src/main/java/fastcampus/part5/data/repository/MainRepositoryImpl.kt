package fastcampus.part5.data.repository

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.repository.MainRepository
import java.io.InputStreamReader
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MainRepository{
    override fun getProductList(): List<Product> {
        val inputStream = context.assets.open("product_list.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val jsonString = inputStreamReader.readText()
        val type = object : TypeToken<List<Product>>() { }.type

        return GsonBuilder().create().fromJson(jsonString, type)
    }
}
package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.BasketProduct
import fastcampus.part5.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    fun getBasketProducts() : Flow<List<BasketProduct>>

    suspend fun removeBasketProduct(product: Product)
}
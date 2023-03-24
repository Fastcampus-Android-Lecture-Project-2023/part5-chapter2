package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductDetailRepository {
    fun getProductDetail(productId: String) : Flow<Product>

    suspend fun addBasket(product: Product)
}
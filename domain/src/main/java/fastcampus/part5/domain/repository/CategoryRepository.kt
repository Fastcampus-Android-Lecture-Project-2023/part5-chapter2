package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
    fun getProductsByCategory(category: Category): Flow<List<Product>>

    suspend fun likeProduct(product: Product)
}
package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val repository: CategoryRepository) {

    fun getCategories() : Flow<List<Category>> {
        return repository.getCategories()
    }
    fun getProductsByCategory(category: Category) : Flow<List<Product>> {
        return repository.getProductsByCategory(category)
    }

    suspend fun likeProduct(product: Product) {
        repository.likeProduct(product)
    }
}
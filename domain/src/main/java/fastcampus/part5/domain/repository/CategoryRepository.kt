package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow<List<Category>>
}
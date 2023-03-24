package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    fun getLikeProducts() : Flow<List<Product>>
}
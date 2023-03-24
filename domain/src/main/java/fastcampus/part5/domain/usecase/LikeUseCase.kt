package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val repository: LikeRepository
) {

    fun getLikeProducts(): Flow<List<Product>> {
        return repository.getLikeProducts()
    }
}
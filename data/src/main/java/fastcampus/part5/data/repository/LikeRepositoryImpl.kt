package fastcampus.part5.data.repository

import fastcampus.part5.data.db.dao.LikeDao
import fastcampus.part5.data.db.entity.toDomainModel
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val likeDao: LikeDao
) : LikeRepository {

    override fun getLikeProducts(): Flow<List<Product>> {
        return likeDao.getAll().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }
}
package fastcampus.part5.data.repository

import fastcampus.part5.data.db.dao.PurchaseHistoryDao
import fastcampus.part5.data.db.entity.toDomainModel
import fastcampus.part5.domain.model.PurchaseHistory
import fastcampus.part5.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PurchaseHistoryRepositoryImpl @Inject constructor(
    private val purchaseHistoryDao: PurchaseHistoryDao
)  : PurchaseHistoryRepository{
    override fun getPurchaseHistory(): Flow<List<PurchaseHistory>> {
        return purchaseHistoryDao.getAll().map { list ->
            list.map { it.toDomainModel() }
        }
    }
}
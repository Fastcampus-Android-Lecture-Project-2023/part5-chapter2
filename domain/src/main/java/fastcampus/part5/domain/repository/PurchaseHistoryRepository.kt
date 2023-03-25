package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.PurchaseHistory
import kotlinx.coroutines.flow.Flow

interface PurchaseHistoryRepository {
    fun getPurchaseHistory(): Flow<List<PurchaseHistory>>
}
package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.PurchaseHistory
import fastcampus.part5.domain.repository.PurchaseHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PurchaseHistoryUseCase @Inject constructor(
    private val repository: PurchaseHistoryRepository
) {

    fun getPurchaseHistory() : Flow<List<PurchaseHistory>> {
        return repository.getPurchaseHistory()
    }
}
package fastcampus.part5.chapter2.viewmodel.purchase_history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.domain.usecase.PurchaseHistoryUseCase
import javax.inject.Inject

@HiltViewModel
class PurchaseHistoryViewModel @Inject constructor(
    private val useCase: PurchaseHistoryUseCase
) : ViewModel() {

    val purchaseHistory = useCase.getPurchaseHistory()

}
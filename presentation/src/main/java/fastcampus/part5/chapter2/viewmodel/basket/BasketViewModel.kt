package fastcampus.part5.chapter2.viewmodel.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.domain.model.BasketProduct
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.usecase.BasketUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketUseCase: BasketUseCase
) : ViewModel() {
    val basketProducts = basketUseCase.getBasketProducts()
    private val _eventFlow = MutableSharedFlow<BasketEvent>()
    val eventFlow: SharedFlow<BasketEvent> = _eventFlow

    fun dispatch(action: BasketAction) {
        when (action) {
            is BasketAction.RemoveProduct -> {
                removeBasketProduct(action.product)
            }
            is BasketAction.CheckoutBasket -> { checkoutBasket(action.products) }
        }
    }

    private fun removeBasketProduct(product: Product) {
        viewModelScope.launch {
            basketUseCase.removeBasketProducts(product)
        }
    }

    private fun checkoutBasket(products: List<BasketProduct>) {
        viewModelScope.launch {
            basketUseCase.checkoutBasket(products)
        }
    }

}

sealed class BasketEvent {
    object ShowSnackBar : BasketEvent()
}

sealed class BasketAction {
    data class RemoveProduct(val product: Product) : BasketAction()
    data class CheckoutBasket(val products: List<BasketProduct>) : BasketAction()
}
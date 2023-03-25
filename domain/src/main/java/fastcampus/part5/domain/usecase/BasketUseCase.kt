package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.BasketProduct
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketUseCase @Inject constructor(
    private val basketRepository: BasketRepository
) {

    fun getBasketProducts() :Flow<List<BasketProduct>> {
        return basketRepository.getBasketProducts()
    }

    suspend fun removeBasketProducts(product: Product) {
        basketRepository.removeBasketProduct(product = product)
    }

    suspend fun checkoutBasket(products: List<BasketProduct>) {
        basketRepository.checkoutBasket(products)
    }
}
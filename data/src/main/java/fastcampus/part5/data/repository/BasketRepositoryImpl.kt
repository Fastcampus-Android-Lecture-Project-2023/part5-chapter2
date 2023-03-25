package fastcampus.part5.data.repository

import fastcampus.part5.data.db.dao.BasketDao
import fastcampus.part5.data.db.dao.PurchaseHistoryDao
import fastcampus.part5.data.db.entity.PurchaseHistoryEntity
import fastcampus.part5.data.db.entity.toDomainModel
import fastcampus.part5.domain.model.BasketProduct
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val basketDao: BasketDao,
    private val purchaseHistoryDao: PurchaseHistoryDao,
) : BasketRepository{
    override fun getBasketProducts(): Flow<List<BasketProduct>> {
        return basketDao.getAll().map { list ->
            list.map { BasketProduct(it.toDomainModel(),it.count) }
        }
    }

    override suspend fun removeBasketProduct(product: Product) {
        return basketDao.delete(product.productId)
    }

    override suspend fun checkoutBasket(products: List<BasketProduct>) {
        purchaseHistoryDao.insert(PurchaseHistoryEntity(products, ZonedDateTime.now()))
        basketDao.deleteAll()
    }
}
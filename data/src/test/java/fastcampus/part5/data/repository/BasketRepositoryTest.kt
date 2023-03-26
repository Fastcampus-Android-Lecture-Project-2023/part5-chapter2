package fastcampus.part5.data.repository

import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import fastcampus.part5.data.db.ApplicationDatabase
import fastcampus.part5.data.db.dao.BasketDao
import fastcampus.part5.data.db.dao.PurchaseHistoryDao
import fastcampus.part5.data.db.entity.BasketProductEntity
import fastcampus.part5.domain.model.BasketProduct
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Price
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.SalesStatus
import fastcampus.part5.domain.model.Shop
import fastcampus.part5.domain.repository.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject


@Suppress("NonAsciiCharacters")
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class BasketRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: ApplicationDatabase

    lateinit var basketDao: BasketDao
    lateinit var purchaseHistoryDao: PurchaseHistoryDao
    lateinit var basketRepository: BasketRepository

    private val price = Price(10000, 10000, SalesStatus.ON_SALE)
    private val shop = Shop("0", "0", "")
    private val category = Category.Top

    private val basketProductEntity = BasketProductEntity("",
        "", "",
        price, category, shop,
        false, false,
        false, 1)

    private val basketProduct = BasketProduct(
        Product("",
        "", "",
        price, category, shop,
        false, false,
        false),
        1)

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        hiltRule.inject()

        basketDao = database.basketDao()
        purchaseHistoryDao = database.purchaseHistoryDao()
        basketRepository = BasketRepositoryImpl(basketDao,purchaseHistoryDao)
    }

    @After
    fun close() {
        Dispatchers.resetMain()
        database.close()
    }

    @Test
    fun `결제 테스트`() = runTest {
        basketDao.insert(basketProductEntity)

        basketRepository.checkoutBasket(listOf(basketProduct))

        assertThat(purchaseHistoryDao.get("1")).isNotNull()
        assertThat(basketDao.get(basketProductEntity.productId)).isNull()
    }
}
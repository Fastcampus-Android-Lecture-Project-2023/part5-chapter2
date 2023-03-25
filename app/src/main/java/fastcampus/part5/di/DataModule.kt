package fastcampus.part5.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.data.repository.AccountRepositoryImpl
import fastcampus.part5.data.repository.BasketRepositoryImpl
import fastcampus.part5.data.repository.CategoryRepositoryImpl
import fastcampus.part5.data.repository.LikeRepositoryImpl
import fastcampus.part5.data.repository.MainRepositoryImpl
import fastcampus.part5.data.repository.ProductDetailRepositoryImpl
import fastcampus.part5.data.repository.PurchaseHistoryRepositoryImpl
import fastcampus.part5.data.repository.SearchRepositoryImpl
import fastcampus.part5.data.repository.TempRepositoryImpl
import fastcampus.part5.domain.repository.AccountRepository
import fastcampus.part5.domain.repository.BasketRepository
import fastcampus.part5.domain.repository.CategoryRepository
import fastcampus.part5.domain.repository.LikeRepository
import fastcampus.part5.domain.repository.MainRepository
import fastcampus.part5.domain.repository.ProductDetailRepository
import fastcampus.part5.domain.repository.PurchaseHistoryRepository
import fastcampus.part5.domain.repository.SearchRepository
import fastcampus.part5.domain.repository.TempRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTempRepository(tempRepositoryImpl: TempRepositoryImpl) : TempRepository

    @Binds
    @Singleton
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository

    @Binds
    @Singleton
    fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl) : CategoryRepository

    @Binds
    @Singleton
    fun bindProductDetailRepository(productDetailRepositoryImpl: ProductDetailRepositoryImpl) : ProductDetailRepository

    @Binds
    @Singleton
    fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl) : SearchRepository

    @Binds
    @Singleton
    fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl) : AccountRepository

    @Binds
    @Singleton
    fun bindLikeRepository(likeRepositoryImpl: LikeRepositoryImpl) : LikeRepository

    @Binds
    @Singleton
    fun bindBasketRepository(basketRepositoryImpl: BasketRepositoryImpl) : BasketRepository

    @Binds
    @Singleton
    fun bindPurchaseHistoryRepository(purchaseHistoryRepositoryImpl: PurchaseHistoryRepositoryImpl) : PurchaseHistoryRepository
}
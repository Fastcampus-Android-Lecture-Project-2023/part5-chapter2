package fastcampus.part5.data.repository

import fastcampus.part5.data.datasource.ProductDataSource
import fastcampus.part5.data.db.dao.LikeDao
import fastcampus.part5.data.db.dao.SearchDao
import fastcampus.part5.data.db.entity.SearchKeywordEntity
import fastcampus.part5.data.db.entity.toDomain
import fastcampus.part5.data.db.entity.toLikeProductEntity
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.SearchFilter
import fastcampus.part5.domain.model.SearchKeyword
import fastcampus.part5.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val searchDao: SearchDao,
    private val likeDao: LikeDao,
) : SearchRepository{

    override suspend fun search(searchKeyword: SearchKeyword): Flow<List<Product>> {
        searchDao.insert(SearchKeywordEntity(searchKeyword.keyword))
        return dataSource.getProducts().combine(likeDao.getAll()) { products, likeList ->
            products.map { product -> updateLikeStatus(product, likeList.map { it.productId }) }
        }
    }

    override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
        return searchDao.getAll().map { it.map { entity -> entity.toDomain() } }
    }

    override suspend fun likeProduct(product: Product) {
        if(product.isLike) {
            likeDao.delete(product.productId)
        } else {
            likeDao.insert(product.toLikeProductEntity().copy(isLike = true))
        }
    }

    private fun updateLikeStatus(product: Product, likeProductIds: List<String>) :Product {
        return product.copy(isLike = likeProductIds.contains(product.productId))
    }
}
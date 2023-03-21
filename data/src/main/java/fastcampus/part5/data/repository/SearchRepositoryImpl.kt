package fastcampus.part5.data.repository

import fastcampus.part5.data.datasource.ProductDataSource
import fastcampus.part5.data.db.dao.SearchDao
import fastcampus.part5.data.db.entity.SearchKeywordEntity
import fastcampus.part5.data.db.entity.toDomain
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.SearchFilter
import fastcampus.part5.domain.model.SearchKeyword
import fastcampus.part5.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val searchDao: SearchDao,
) : SearchRepository{

    override suspend fun search(searchKeyword: SearchKeyword, filters: List<SearchFilter>): Flow<List<Product>> {
        searchDao.insert(SearchKeywordEntity(searchKeyword.keyword))
        return dataSource.getProducts().map { list ->
            list.filter { isAvailableProduct(it, searchKeyword, filters) }
        }
    }

    private fun isAvailableProduct(product: Product, searchKeyword: SearchKeyword, filters: List<SearchFilter>): Boolean {
        var isAvailable = true
        filters.forEach { isAvailable = isAvailable && it.isAvailableProduct(product) }

        return isAvailable && product.productName.contains(searchKeyword.keyword)
    }

    override fun getSearchKeywords(): Flow<List<SearchKeyword>> {
        return searchDao.getAll().map { it.map { entity -> entity.toDomain() } }
    }
}
package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.SearchFilter
import fastcampus.part5.domain.model.SearchKeyword
import fastcampus.part5.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend fun search(keyword: SearchKeyword, filters: List<SearchFilter>) : Flow<List<Product>> {
        return searchRepository.search(keyword).map { list ->
            list.filter { isAvailableProduct(it, keyword, filters) }
        }
    }

    fun getSearchKeywords() : Flow<List<SearchKeyword>> {
        return searchRepository.getSearchKeywords()
    }

    suspend fun likeProduct(product: Product) {
        searchRepository.likeProduct(product)
    }

    private fun isAvailableProduct(product: Product, searchKeyword: SearchKeyword, filters: List<SearchFilter>): Boolean {
        var isAvailable = true
        filters.forEach { isAvailable = isAvailable && it.isAvailableProduct(product) }

        return isAvailable && product.productName.contains(searchKeyword.keyword)
    }
}
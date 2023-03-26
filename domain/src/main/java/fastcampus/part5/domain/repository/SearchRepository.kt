package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.SearchFilter
import fastcampus.part5.domain.model.SearchKeyword
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(searchKeyword: SearchKeyword) : Flow<List<Product>>

    fun getSearchKeywords() : Flow<List<SearchKeyword>>

    suspend fun likeProduct(product: Product)
}
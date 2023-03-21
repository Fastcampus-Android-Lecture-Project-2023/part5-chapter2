package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.SearchFilter
import fastcampus.part5.domain.model.SearchKeyword
import fastcampus.part5.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend fun search(keyword: SearchKeyword, filters: List<SearchFilter>) : Flow<List<Product>> {
        return searchRepository.search(keyword, filters)
    }

    fun getSearchKeywords() : Flow<List<SearchKeyword>> {
        return searchRepository.getSearchKeywords()
    }
}
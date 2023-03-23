package fastcampus.part5.chapter2.viewmodel.search

import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.SearchFilter
import fastcampus.part5.domain.model.SearchKeyword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.max
import kotlin.math.min

class SearchManager {
    private val _filters = MutableStateFlow<List<SearchFilter>>(listOf())
    val filters : StateFlow<List<SearchFilter>> = _filters

    var searchKeyword = SearchKeyword("")
        private set

    suspend fun initSearchManager(newSearchKeyword: String, searchResult: List<Product>) {
        val categories = mutableListOf<Category>()
        var minPrice = Int.MAX_VALUE
        var maxPrice = Int.MIN_VALUE

        searchResult.forEach {product ->
            if(categories.find { it.categoryId == product.category.categoryId } == null) {
                categories.add(product.category)
            }

            minPrice = min(minPrice, product.price.finalPrice)
            maxPrice = max(maxPrice, product.price.finalPrice)
        }
        _filters.emit(listOf(
            SearchFilter.PriceFilter(minPrice.toFloat() to maxPrice.toFloat()),
            SearchFilter.CategoryFilter(categories)
        ))

        searchKeyword = SearchKeyword(newSearchKeyword)
    }

    suspend fun updateFilter(filter: SearchFilter) {
        val currentFilter = filters.value

        _filters.emit(currentFilter.map {
            if(it is SearchFilter.PriceFilter && filter is SearchFilter.PriceFilter) {
                it.selectedRange = filter.selectedRange
            }
            if(it is SearchFilter.CategoryFilter && filter is SearchFilter.CategoryFilter) {
                it.selectedCategory = filter.selectedCategory
            }
            it
        })
    }

    fun clearFilter() {
        filters.value.forEach {
            it.clear()
        }
    }

    fun currentFilters(): List<SearchFilter> = filters.value
}
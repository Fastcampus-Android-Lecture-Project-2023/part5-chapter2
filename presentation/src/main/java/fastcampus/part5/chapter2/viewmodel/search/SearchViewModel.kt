package fastcampus.part5.chapter2.viewmodel.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.chapter2.model.ProductVM
import fastcampus.part5.chapter2.ui.NavigationRouteName
import fastcampus.part5.chapter2.utils.NavigationUtils
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.SearchKeyword
import fastcampus.part5.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchUseCase
) : ViewModel(), ProductDelegate{

    private val _searchResult = MutableStateFlow<List<ProductVM>>(listOf())
    val searchResult : StateFlow<List<ProductVM>> = _searchResult
    val searchKeywords = useCase.getSearchKeywords()

    fun search(keyword: String) {
        viewModelScope.launch {
            useCase.search(SearchKeyword(keyword = keyword)).collectLatest {
                _searchResult.emit(it.map(::convertToProductVM))
            }
        }
    }

    private fun convertToProductVM(product: Product) : ProductVM {
        return ProductVM(product, this)
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtils.navigate(navHostController, NavigationRouteName.PRODUCT_DETAIL, product)
    }
}
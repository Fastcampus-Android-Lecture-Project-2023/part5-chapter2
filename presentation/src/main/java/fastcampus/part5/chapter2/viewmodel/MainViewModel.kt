package fastcampus.part5.chapter2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.chapter2.delegate.BannerDelegate
import fastcampus.part5.chapter2.delegate.CategoryDelegate
import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.chapter2.model.BannerListVM
import fastcampus.part5.chapter2.model.BannerVM
import fastcampus.part5.chapter2.model.CarouselVM
import fastcampus.part5.chapter2.model.PresentationVM
import fastcampus.part5.chapter2.model.ProductVM
import fastcampus.part5.chapter2.model.RankingVM
import fastcampus.part5.chapter2.ui.NavigationItem
import fastcampus.part5.chapter2.ui.NavigationRouteName
import fastcampus.part5.chapter2.utils.NavigationUtils
import fastcampus.part5.domain.model.Banner
import fastcampus.part5.domain.model.BannerList
import fastcampus.part5.domain.model.BaseModel
import fastcampus.part5.domain.model.Carousel
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.ModelType
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.Ranking
import fastcampus.part5.domain.usecase.CategoryUseCase
import fastcampus.part5.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(mainUseCase: MainUseCase, categoryUseCase: CategoryUseCase)
    : ViewModel(), ProductDelegate, BannerDelegate, CategoryDelegate {
    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount: StateFlow<Int> = _columnCount
    val modelList = mainUseCase.getModelList().map(::convertToPresentationVM)
    val categories = categoryUseCase.getCategories()

    fun openSearchForm() {
        println("openSearchForm")
    }

    fun updateColumnCount(count: Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtils.navigate(navHostController,NavigationRouteName.PRODUCT_DETAIL, product)
    }

    override fun openBanner(bannerId: String) {

    }

    override fun openCategory(navHostController: NavHostController, category: Category) {
        NavigationUtils.navigate(navHostController,NavigationRouteName.CATEGORY, category)
    }

    private fun convertToPresentationVM(list: List<BaseModel>) : List<PresentationVM<out BaseModel>> {
        return list.map { model ->
            when(model) {
                is Product -> ProductVM(model,this)
                is Ranking -> RankingVM(model,this)
                is Carousel -> CarouselVM(model,this)
                is Banner -> BannerVM(model,this)
                is BannerList -> BannerListVM(model,this)
            }
        }
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }

}
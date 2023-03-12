package fastcampus.part5.chapter2.viewmodel.category

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.chapter2.model.ProductVM
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.usecase.CategoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: CategoryUseCase
) : ViewModel(), ProductDelegate {
    private val _products = MutableStateFlow<List<ProductVM>>(listOf())
    val products : StateFlow<List<ProductVM>> = _products

    suspend fun updateCategory(category: Category) {
        useCase.getProductsByCategory(category).collectLatest {
            _products.emit(convertToPresentationVM(it))
        }
    }

    override fun openProduct(product: Product) {

    }

    private fun convertToPresentationVM(list: List<Product>) : List<ProductVM> {
        return list.map {
            ProductVM(it, this)
        }
    }
}
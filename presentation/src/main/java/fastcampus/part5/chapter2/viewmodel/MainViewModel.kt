package fastcampus.part5.chapter2.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.domain.usecase.MainUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(useCase: MainUseCase) : ViewModel() {
    val productList = useCase.getProductList()

    fun openSearchForm() {
        println("openSearchForm")
    }
}
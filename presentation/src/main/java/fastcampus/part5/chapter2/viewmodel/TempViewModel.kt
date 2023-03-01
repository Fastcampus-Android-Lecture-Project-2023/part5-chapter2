package fastcampus.part5.chapter2.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.domain.model.TempModel
import fastcampus.part5.domain.usecase.TempUseCase
import fastcampus.part5.domain.usecase.TempUseCaseInterface
import javax.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(
    private val tempUseCase: TempUseCase
) : ViewModel() {

    fun getTempModel(): TempModel {
        return tempUseCase.getTempModel()
    }
}
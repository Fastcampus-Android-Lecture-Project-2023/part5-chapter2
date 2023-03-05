package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.TempModel
import fastcampus.part5.domain.repository.TempRepository
import javax.inject.Inject

class TempUseCase @Inject constructor(private val repository : TempRepository) {

    fun getTempModel() : TempModel {
        return repository.getTempModel()
    }
}

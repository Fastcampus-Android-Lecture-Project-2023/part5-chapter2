package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.TempModel

interface TempUseCaseInterface {
    fun getTempModel() : TempModel
}
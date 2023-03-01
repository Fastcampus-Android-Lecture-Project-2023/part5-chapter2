package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.TempModel

interface TempRepository {
    fun getTempModel() : TempModel
}
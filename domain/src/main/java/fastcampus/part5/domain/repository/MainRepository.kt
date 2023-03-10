package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.BaseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getModelList() : Flow<List<BaseModel>>
}
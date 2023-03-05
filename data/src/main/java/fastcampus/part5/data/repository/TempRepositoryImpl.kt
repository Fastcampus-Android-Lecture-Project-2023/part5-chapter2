package fastcampus.part5.data.repository

import fastcampus.part5.data.datasource.TempDataSource
import fastcampus.part5.domain.model.TempModel
import fastcampus.part5.domain.repository.TempRepository
import javax.inject.Inject

class TempRepositoryImpl @Inject constructor(private val dataSource : TempDataSource) : TempRepository {
    override fun getTempModel(): TempModel {
        return dataSource.getTempModel()
    }
}
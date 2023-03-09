package fastcampus.part5.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.data.repository.MainRepositoryImpl
import fastcampus.part5.data.repository.TempRepositoryImpl
import fastcampus.part5.domain.repository.MainRepository
import fastcampus.part5.domain.repository.TempRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTempRepository(tempRepositoryImpl: TempRepositoryImpl) : TempRepository

    @Binds
    @Singleton
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository
}
package fastcampus.part5.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import fastcampus.part5.domain.usecase.TempUseCase
import fastcampus.part5.domain.usecase.TempUseCaseInterface


@Module
@InstallIn(ActivityRetainedComponent::class)
interface DomainModule {

}
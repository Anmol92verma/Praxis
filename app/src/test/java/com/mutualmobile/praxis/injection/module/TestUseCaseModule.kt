package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.data.repository.JokesRepo
import com.mutualmobile.praxis.domain.usecases.GetFiveRandomJokesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestUseCaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideGetFiveRandomJokesUseCase(jokesRepo: JokesRepo) = GetFiveRandomJokesUseCase(jokesRepo)
}
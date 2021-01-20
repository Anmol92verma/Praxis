package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.data.repository.JokesRepo
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestRepositoryModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideJokesRepository(networkSource: IJokesRemoteSource): JokesRepo {
        return JokesRepo(networkSource)
    }
}
package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.sources.IJokesRemoteSource
import com.mutualmobile.praxis.data.sources.JokesRemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object TestSourcesModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideJokesNetworkSource(apiService: JokeApiService): IJokesRemoteSource {
        return JokesRemoteSource(
                apiService
        )
    }


}
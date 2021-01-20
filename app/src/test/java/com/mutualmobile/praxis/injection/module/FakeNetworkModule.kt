package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.data.remote.JokeApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object FakeNetworkModule {

    @Provides
    @Singleton
    @JvmStatic
    @Throws(NullPointerException::class)
    fun provideMockWebServer(): MockWebServer {
        var mockWebServer: MockWebServer? = null
        val thread = Thread {
            mockWebServer = MockWebServer()
            mockWebServer?.start()
        }
        thread.start()
        thread.join()
        return mockWebServer ?: throw NullPointerException("Mock Web Server Could not be started")
    }

    @Provides
    @Singleton
    @JvmStatic
    @Named("baseUrl")
    fun provideBaseUrl(mockWebServer: MockWebServer): String {
        return mockWebServer.url("/").toString()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient, @Named("baseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideJokesApiService(retrofit: Retrofit): JokeApiService {
        return JokeApiService.createRetrofitService(retrofit)
    }

}
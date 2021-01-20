package com.mutualmobile.praxis.injection.component

import android.content.Context
import com.mutualmobile.praxis.TestApplication
import com.mutualmobile.praxis.injection.module.FakeNetworkModule
import com.mutualmobile.praxis.injection.module.TestRepositoryModule
import com.mutualmobile.praxis.injection.module.TestSourcesModule
import com.mutualmobile.praxis.injection.module.TestUseCaseModule
import com.mutualmobile.praxis.useCaseTest.GetFiveRandomJokesUseCaseTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            FakeNetworkModule::class,
            TestRepositoryModule::class,
            TestSourcesModule::class,
            TestUseCaseModule::class,
            AndroidSupportInjectionModule::class
        ]
)
interface TestAppComponent: AndroidInjector<TestApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TestApplication>() {
        @BindsInstance
        abstract fun appContext(context: Context)

        override fun seedInstance(instance: TestApplication?) {
            appContext(instance!!.applicationContext)
        }
    }

    fun inject(getFiveRandomJokesUseCase: GetFiveRandomJokesUseCaseTest)
}
package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.manager.PushNotificationService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
  @Singleton
  @Provides
  fun providePushNotificationService() = PushNotificationService()
}

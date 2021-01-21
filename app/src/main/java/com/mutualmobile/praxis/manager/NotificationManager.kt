package com.mutualmobile.praxis.manager

import android.content.Context
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobile.config.AWSConfiguration
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class NotificationManager {
  private lateinit var pinpointManager: PinpointManager

  fun getPinpointManager(applicationContext: Context?): PinpointManager {
    if (!::pinpointManager.isInitialized) {
      val awsConfig = AWSConfiguration(applicationContext)
      AWSMobileClient.getInstance()
          .initialize(applicationContext, awsConfig, object : Callback<UserStateDetails?> {
            override fun onResult(result: UserStateDetails?) {
              TODO("Not yet implemented")
            }

            override fun onError(e: Exception?) {
              TODO("Not yet implemented")
            }
          })
      val pinpointConfig = PinpointConfiguration(
          applicationContext,
          AWSMobileClient.getInstance(),
          awsConfig
      )
      pinpointManager = PinpointManager(pinpointConfig)
      FirebaseInstanceId.getInstance().instanceId
          .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
              return@OnCompleteListener
            }
            val token = task.result?.token
            pinpointManager.notificationClient
                .registerDeviceToken(token)
          })
    }
    return pinpointManager
  }

}
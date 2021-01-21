package com.mutualmobile.praxis.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

fun NotificationManager.createNotificationChannel(channel: Channel) {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    val name = channel.name
    val descriptionText = channel.desc
    val importance = NotificationManager.IMPORTANCE_HIGH
    val notificationChannel =
      NotificationChannel(channel.id, name, importance).apply {
        enableLights(true)
        enableVibration(true)
        description = descriptionText
      }

    createNotificationChannel(notificationChannel)
  }
}

data class Channel(
  val id: String,
  val name: String,
  val desc: String,
  val category: String
)
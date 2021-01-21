package com.mutualmobile.praxis.manager

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.model.NotificationData

class PraxisNotificationBuilder(
  private val context: Context,
  private val notificationManager: NotificationManager
) {

  fun showNotification(data: NotificationData) {

    val channel =
      Channel("1", "Sample Notification", "Sample Notification Description", "General")
    notificationManager.createNotificationChannel(channel)

    val notification = buildNotification(
        title = data.title,
        content = data.body,
        channelId = channel.id,
        category = channel.category,
    )

    notify(1, notification)
  }

  private fun buildNotification(
    title: String?,
    content: String?,
    channelId: String,
    category: String
  ): Notification {
    return NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_notification)
        .setContentTitle(title)
        .setContentText(content)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setCategory(category)
        .setAutoCancel(true)
        .build()
  }

  private fun notify(
    notificationId: Int,
    notification: Notification
  ) {
    with(NotificationManagerCompat.from(context)) {
      notify(
          notificationId,
          notification
      )
    }
  }
}
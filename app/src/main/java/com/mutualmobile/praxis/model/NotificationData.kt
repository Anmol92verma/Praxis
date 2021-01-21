package com.mutualmobile.praxis.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationData(
  val title: String? = null,
  val body: String? = null,
) : Parcelable
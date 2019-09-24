package com.pagetyler.nasadaillypict.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
 data class NasaPicture (
    val copyright : String = "",
    val date: String,
    val explanation: String,
    @Json(name = "hdurl") val hdiSrcUrl: String,
    @Json(name = "media_type") val mediaType: String = "image",
    @Json(name = "service_version") val serviceVersion: String = "",
    val title: String = "",
    @Json(name = "url") val imgSrcUrl: String
) : Parcelable {}


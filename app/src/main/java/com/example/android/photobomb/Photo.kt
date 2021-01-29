package com.example.android.photobomb

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Photo(

        val id: String,
        val description: String?,
        val urls: UnsplashPhotoUrls,
        val user: UnsplashUser
) : Parcelable {

    @Parcelize
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    ) : Parcelable

    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String
    ) : Parcelable {

    }
}
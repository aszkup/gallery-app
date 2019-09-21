package com.android.galleryapp.domain.gallery

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GalleryItem(
    val id: String,
    val title: String? = null,
    val published: String? = null,
    val taken: String? = null,
    val link: String? = null,
    val author: Author? = null
) : Parcelable

@Parcelize
data class Author(
    val id: String? = null,
    val name: String? = null,
    val url: String? = null
) : Parcelable

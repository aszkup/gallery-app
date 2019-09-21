package com.android.galleryapp.domain.gallery

data class GalleryItem(
    val id: String,
    val title: String? = null,
    val published: String? = null,
    val taken: String? = null,
    val link: String? = null,
    val author: Author? = null
)

data class Author(
    val id: String? = null,
    val name: String? = null,
    val url: String? = null
)

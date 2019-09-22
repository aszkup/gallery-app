package com.android.galleryapp.domain.feed

import com.android.galleryapp.domain.gallery.GalleryItem
import com.android.galleryapp.repository.gallery.GalleryApi
import com.android.galleryapp.repository.gallery.toGalleryItem
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetFeedUseCase(
    private val galleryApi: GalleryApi
) {

    operator fun invoke(tags: List<String>): Single<List<GalleryItem>> {
        return galleryApi.getFeed(tags.joinToString(separator = ","))
            .subscribeOn(Schedulers.io())
            .map { it.entries ?: listOf() }
            .map { it.map { it.toGalleryItem() } }
    }
}

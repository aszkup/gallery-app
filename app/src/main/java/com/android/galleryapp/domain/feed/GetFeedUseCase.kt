package com.android.galleryapp.domain.feed

import com.android.galleryapp.domain.gallery.GalleryItem
import com.android.galleryapp.repository.gallery.GalleryApi
import com.android.galleryapp.repository.gallery.toGalleryItem
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetFeedUseCase(
    private val galleryApi: GalleryApi
) {

    operator fun invoke(): Single<List<GalleryItem>> {
        return galleryApi.getFeed()
            .subscribeOn(Schedulers.io())
            .map { it.entries }
            .map { it.map { it.toGalleryItem() } }
    }
}

package com.android.galleryapp.viewmodel.gallery

import com.android.galleryapp.repository.gallery.GalleryApi
import com.android.galleryapp.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GalleryViewModel(
    private val galleryApi: GalleryApi
) : BaseViewModel() {

    init {
        galleryApi.getFeed()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { Timber.d("Feed: $it") },
                onError = Timber::e
            ).addTo(disposables)
    }
}

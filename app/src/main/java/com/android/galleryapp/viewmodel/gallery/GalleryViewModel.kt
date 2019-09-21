package com.android.galleryapp.viewmodel.gallery

import androidx.lifecycle.ViewModel
import com.android.galleryapp.repository.gallery.GalleryApi
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GalleryViewModel(
    private val galleryApi: GalleryApi
) : ViewModel() {

    init {
        galleryApi.getFeed()
            .subscribeOn(Schedulers.io())
            .subscribe({}, Timber::e)
    }
}

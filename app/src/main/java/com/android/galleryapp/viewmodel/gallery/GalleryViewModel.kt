package com.android.galleryapp.viewmodel.gallery

import androidx.lifecycle.MutableLiveData
import com.android.galleryapp.domain.feed.Entry
import com.android.galleryapp.platform.extension.readOnly
import com.android.galleryapp.repository.gallery.GalleryApi
import com.android.galleryapp.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class GalleryViewModel(
    private val galleryApi: GalleryApi
) : BaseViewModel() {

    private val _galleryItems = MutableLiveData<List<Entry>>()
    val galleryItems = _galleryItems.readOnly

    init {
        galleryApi.getFeed()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    Timber.d("Feed: $it")
                    _galleryItems.postValue(it.entries)
                },
                onError = Timber::e
            ).addTo(disposables)
    }
}

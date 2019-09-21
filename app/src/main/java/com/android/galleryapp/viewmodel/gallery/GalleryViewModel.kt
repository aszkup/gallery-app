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

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress = _inProgress.readOnly

    private val _hasData = MutableLiveData<Boolean>()
    val hasData = _hasData.readOnly

    init {
        getFeed()
    }

    fun refresh() {
        getFeed()
    }

    private fun getFeed() {
        galleryApi.getFeed()
            .doOnSubscribe { _inProgress.postValue(true) }
            .doAfterTerminate { _inProgress.postValue(false) }
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    Timber.d("Feed: $it")
                    _galleryItems.postValue(it.entries)
                    _hasData.postValue(!it.entries.isNullOrEmpty())
                },
                onError = Timber::e
            ).addTo(disposables)
    }
}

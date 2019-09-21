package com.android.galleryapp.viewmodel.gallery

import androidx.lifecycle.MutableLiveData
import com.android.galleryapp.domain.feed.GetFeedUseCase
import com.android.galleryapp.domain.gallery.GalleryItem
import com.android.galleryapp.platform.extension.readOnly
import com.android.galleryapp.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class GalleryViewModel(
    private val getFeedUseCase: GetFeedUseCase
) : BaseViewModel() {

    private val _galleryItems = MutableLiveData<List<GalleryItem>>()
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
        getFeedUseCase()
            .doOnSubscribe { _inProgress.postValue(true) }
            .doAfterTerminate { _inProgress.postValue(false) }
            .subscribeBy(
                onSuccess = {
                    Timber.d("Feed: $it")
                    _galleryItems.postValue(it)
                    _hasData.postValue(!it.isNullOrEmpty())
                },
                onError = Timber::e
            ).addTo(disposables)
    }
}

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

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing = _isRefreshing.readOnly

    private val _hasData = MutableLiveData<Boolean>()
    val hasData = _hasData.readOnly

    private val _isLoadingMore = MutableLiveData<Boolean>()
    val isLoadingMore = _isLoadingMore.readOnly

    init {
        // load first page
        refresh()
    }

    fun refresh() {
        _isRefreshing.postValue(true)
        getFeed { _galleryItems.postValue(it) }
    }

    fun loadMore() {
        _isLoadingMore.postValue(true)
        getFeed {
            val items = _galleryItems.value!!.toMutableList()
            items.addAll(it)
            _galleryItems.postValue(items)
        }
    }

    private fun getFeed(onSuccess: ((List<GalleryItem>) -> Unit)) {
        getFeedUseCase()
            .doAfterTerminate { _isRefreshing.postValue(false) }
            .doAfterTerminate { _isLoadingMore.postValue(false) }
            .subscribeBy(
                onSuccess = {
                    Timber.d("Feed: $it")
                    onSuccess(it)
                    _hasData.postValue(!it.isNullOrEmpty())
                },
                onError = Timber::e
            ).addTo(disposables)
    }
}

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

    private var sortByPublishDate = true

    init {
        // load first page
        refresh()
    }

    fun refresh() {
        _isRefreshing.postValue(true)
        getFeed {
            val sortedItems = sortItems(it)
            _galleryItems.postValue(sortedItems)
        }
    }

    fun loadMore() {
        _isLoadingMore.postValue(true)
        getFeed {
            val items = _galleryItems.value!!.toMutableList()
            items.addAll(it)
            val sortedItems = sortItems(items.distinct())
            _galleryItems.postValue(sortedItems)
        }
    }

    fun sortByPublishDate() {
        if (!sortByPublishDate) {
            sortByPublishDate = true
            updateItems()
        }
    }

    fun sortByTakenDate() {
        if (sortByPublishDate) {
            sortByPublishDate = false
            updateItems()
        }
    }

    private fun updateItems() {
        val sortedItems = sortItems(_galleryItems.value!!)
        _galleryItems.postValue(sortedItems)
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

    private fun sortItems(items: List<GalleryItem>): List<GalleryItem> {
        return if (sortByPublishDate) {
            items.sortedBy { it.published }
        } else {
            items.sortedBy { it.taken }
        }
    }
}

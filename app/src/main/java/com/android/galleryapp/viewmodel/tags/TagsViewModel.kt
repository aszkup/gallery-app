package com.android.galleryapp.viewmodel.tags

import androidx.lifecycle.MutableLiveData
import com.android.galleryapp.domain.tags.GetTagsUseCase
import com.android.galleryapp.platform.extension.readOnly
import com.android.galleryapp.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class TagsViewModel(
    private val getTagsUseCase: GetTagsUseCase
) : BaseViewModel() {

    private val _tags = MutableLiveData<String>()
    val tags = _tags.readOnly

    init {
        getTagsUseCase()
            .subscribeBy(
                onSuccess = {
                    Timber.d("$it")
                },
                onError = Timber::e
            ).addTo(disposables)
    }
}

package com.android.galleryapp.domain.tags

import com.android.galleryapp.repository.tags.TagsApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class GetTagsUseCase(
    private val tagsApi: TagsApi
) {

    operator fun invoke(): Single<ResponseBody> {
        return tagsApi.getTags()
            .subscribeOn(Schedulers.io())
    }
}

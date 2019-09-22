package com.android.galleryapp.repository.tags

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET

interface TagsApi {

    @GET("tags")
    fun getTags(): Single<ResponseBody>
}

fun getTagsApi(retrofit: Retrofit): TagsApi = retrofit.create(TagsApi::class.java)

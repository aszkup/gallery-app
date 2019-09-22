package com.android.galleryapp.repository.gallery

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryApi {

    @GET(".")
    fun getFeed(@Query("tags") tags: String): Single<Feed>
}

fun getGalleryApi(retrofit: Retrofit): GalleryApi = retrofit.create(GalleryApi::class.java)

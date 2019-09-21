package com.android.galleryapp.repository.gallery

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET

interface GalleryApi {

    @GET(".")
    fun getFeed(): Single<Feed>
}

fun getGalleryApi(retrofit: Retrofit): GalleryApi = retrofit.create(GalleryApi::class.java)

package com.android.galleryapp.repository.gallery

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET

interface GalleryApi {

    @GET(".")
    fun getFeed(): Single<ResponseBody>
}

fun getGalleryApi(retrofit: Retrofit): GalleryApi = retrofit.create(GalleryApi::class.java)

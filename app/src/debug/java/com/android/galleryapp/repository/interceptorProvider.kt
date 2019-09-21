package com.android.galleryapp.repository

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor

fun provideChuckInterceptor(context: Context): Interceptor? = ChuckInterceptor(context)

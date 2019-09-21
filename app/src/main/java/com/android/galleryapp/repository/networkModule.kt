package com.android.galleryapp.repository

import android.content.Context
import com.android.galleryapp.BuildConfig
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit


private const val DISK_CACHE_SIZE = 10 * 1024 * 1024

val networkModule = module {

    single<Retrofit> { provideRetrofit(okHttpClient = get()) }
    single { provideHttpLoggingInterceptor() }
    single { provideHttpCacheDir(context = get()) }
    single { provideOkHttpClient(loggingInterceptor = get(), context = get(), cacheDir = get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder().apply {
    baseUrl(BuildConfig.BACKEND_URL)
    val tikRules = TikXml.Builder().exceptionOnUnreadXml(false).build()
    addConverterFactory(TikXmlConverterFactory.create(tikRules))
    addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    client(okHttpClient)
}.build()

private fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Timber.d(message)
    }
}).apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    context: Context,
    cacheDir: File
) = OkHttpClient.Builder().apply {
    addInterceptor(loggingInterceptor)
    if (BuildConfig.DEBUG) {
        addInterceptor(provideChuckInterceptor(context)!!)
    }
    writeTimeout(20, TimeUnit.SECONDS)
    connectTimeout(20, TimeUnit.MINUTES)
    readTimeout(20, TimeUnit.MINUTES)
    cache(Cache(cacheDir, DISK_CACHE_SIZE.toLong()))
}.build()

private fun provideHttpCacheDir(context: Context) = File(context.cacheDir, "http")

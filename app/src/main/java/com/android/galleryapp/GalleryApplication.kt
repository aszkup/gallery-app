package com.android.galleryapp

import android.app.Application
import android.content.Context
import com.android.galleryapp.repository.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber

class GalleryApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        setupDependencyInjection()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupDependencyInjection() {
        startKoin {
            androidLogger()
            androidContext(this@GalleryApplication)
            modules(appModules)
        }
    }
}

val appModules = mutableListOf<Module>(networkModule)

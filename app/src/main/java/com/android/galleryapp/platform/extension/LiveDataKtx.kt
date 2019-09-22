package com.android.galleryapp.platform.extension

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

inline val <T> MutableLiveData<T>.readOnly: LiveData<T> get() = this

fun <T> LiveData<T>.debounce(duration: Long = 500) = MediatorLiveData<T>().also { mld ->
    val source = this
    val handler = Handler(Looper.getMainLooper())

    val runnable = Runnable {
        mld.value = source.value
    }

    mld.addSource(source) {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, duration)
    }
}

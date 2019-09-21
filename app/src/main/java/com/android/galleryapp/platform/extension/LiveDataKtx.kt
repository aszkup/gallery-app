package com.android.galleryapp.platform.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

inline val <T> MutableLiveData<T>.readOnly: LiveData<T> get() = this

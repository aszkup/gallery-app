package com.android.galleryapp.platform

import androidx.annotation.StringRes

interface StringProvider {

    fun getString(@StringRes stringResId: Int): String

    fun getString(@StringRes stringResId: Int, vararg params: Any): String
}

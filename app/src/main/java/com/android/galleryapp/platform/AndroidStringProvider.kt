package com.android.galleryapp.platform

import android.content.res.Resources
import androidx.annotation.StringRes

class AndroidStringProvider(private val resources: Resources) : StringProvider {

    override fun getString(stringResId: Int, vararg params: Any): String =
        resources.getString(stringResId, *params)

    override fun getString(@StringRes stringResId: Int): String = resources.getString(stringResId)
}

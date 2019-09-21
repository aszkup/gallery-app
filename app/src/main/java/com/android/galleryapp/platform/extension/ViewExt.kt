package com.android.galleryapp.platform.extension

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.BindingAdapter

val View.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)


@BindingAdapter("isVisible")
fun setVisibilityWithGone(view: View, show: Boolean) {
    view.visibility = show or View.GONE
}

private infix fun Boolean.or(invisibility: Int) = if (this) View.VISIBLE else invisibility

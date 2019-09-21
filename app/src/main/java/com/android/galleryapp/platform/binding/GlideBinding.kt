package com.android.galleryapp.platform.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("src")
fun setImageUrl(view: ImageView, url: String?) {
    setImageUrl(view, url, null)
}

@BindingAdapter(value = ["src", "placeholder"])
fun setImageUrl(view: ImageView, url: String?, placeholder: Drawable?) {
    Glide.with(view.context)
        .load(url)
        .placeholder(placeholder)
        .into(view)
}

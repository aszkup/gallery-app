package com.android.galleryapp.platform.binding

import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("thumb")
fun setThumb(imageView: ImageView, path: String) {
    val thumb = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND)
    imageView.setImageBitmap(thumb)
}

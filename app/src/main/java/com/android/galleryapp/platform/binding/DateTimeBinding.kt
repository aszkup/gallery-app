package com.android.galleryapp.platform.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

private val simpleDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

@BindingAdapter("simpleDate")
fun setDate(view: TextView, date: OffsetDateTime?) {
    view.text = date?.format(simpleDateTimeFormatter)
}

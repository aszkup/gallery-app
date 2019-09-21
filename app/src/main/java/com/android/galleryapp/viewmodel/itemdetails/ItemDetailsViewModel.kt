package com.android.galleryapp.viewmodel.itemdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.galleryapp.domain.gallery.GalleryItem

class ItemDetailsViewModel() : ViewModel() {

    val item = MutableLiveData<GalleryItem>()

}

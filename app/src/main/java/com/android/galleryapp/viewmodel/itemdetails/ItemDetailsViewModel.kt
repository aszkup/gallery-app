package com.android.galleryapp.viewmodel.itemdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.galleryapp.platform.extension.readOnly

class ItemDetailsViewModel() : ViewModel() {

    val _itemDetails = MutableLiveData<String>()
    val title = _itemDetails.readOnly

}

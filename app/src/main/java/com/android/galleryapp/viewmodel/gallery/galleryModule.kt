package com.android.galleryapp.viewmodel.gallery

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val galleryModule = module {

    viewModel { GalleryViewModel() }
}

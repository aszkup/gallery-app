package com.android.galleryapp.viewmodel.gallery

import com.android.galleryapp.repository.gallery.getGalleryApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val galleryModule = module {

    viewModel { GalleryViewModel(galleryApi = get()) }

    single { getGalleryApi(retrofit = get()) }
}

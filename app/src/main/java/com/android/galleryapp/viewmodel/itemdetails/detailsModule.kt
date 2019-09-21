package com.android.galleryapp.viewmodel.itemdetails

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    viewModel {
        ItemDetailsViewModel()
    }
}

package com.android.galleryapp.viewmodel.tags

import com.android.galleryapp.domain.tags.GetTagsUseCase
import com.android.galleryapp.repository.tags.getTagsApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tagsModule = module {

    viewModel {
        TagsViewModel(getTagsUseCase = get())
    }

    single { getTagsApi(retrofit = get()) }
    factory { GetTagsUseCase(tagsApi = get()) }
}

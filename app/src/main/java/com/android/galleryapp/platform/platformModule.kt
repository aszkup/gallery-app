package com.android.galleryapp.platform

import android.content.Context
import android.content.res.Resources
import org.koin.dsl.bind
import org.koin.dsl.module

val platformModule = module {

    factory<Resources> { get<Context>().resources }
    factory { AndroidStringProvider(resources = get()) } bind StringProvider::class
}

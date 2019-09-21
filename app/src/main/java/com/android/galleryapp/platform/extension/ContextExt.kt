package com.android.galleryapp.platform.extension

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.showFragment(fragment: Fragment, @IdRes viewId: Int, tag: String? = null) =
    supportFragmentManager.beginTransaction().apply {
        replace(viewId, fragment, tag)
        commit()
    }

fun Fragment.showFragment(fragment: Fragment, @IdRes viewId: Int, tag: String? = null) =
    requireFragmentManager().beginTransaction().apply {
        replace(viewId, fragment, tag)
        commit()
    }

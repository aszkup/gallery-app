package com.android.galleryapp.view.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.galleryapp.databinding.GalleryFragmentBinding
import com.android.galleryapp.viewmodel.gallery.GalleryViewModel
import com.android.galleryapp.viewmodel.gallery.galleryModule
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.context.loadKoinModules

private fun injectFeatures() {
    loadKoinModules(galleryModule)
}

class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by sharedViewModel()
    private lateinit var binding: GalleryFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures() // TODO move to base fragment in case of more fragments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        GalleryFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = galleryViewModel
            binding = this
        }.root
}

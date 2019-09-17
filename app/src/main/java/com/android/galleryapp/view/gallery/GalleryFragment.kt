package com.android.galleryapp.view.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.galleryapp.databinding.GalleryFragmentBinding
import com.android.galleryapp.viewmodel.gallery.GalleryViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by sharedViewModel()
    private lateinit var binding: GalleryFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        GalleryFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = galleryViewModel
            binding = this
        }.root
}

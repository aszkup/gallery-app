package com.android.galleryapp.view.itemdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.galleryapp.databinding.ItemDetailsFragmentBinding
import com.android.galleryapp.viewmodel.itemdetails.ItemDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ItemDetailsFragment : Fragment() {

    private val itemDetailsViewModel: ItemDetailsViewModel by sharedViewModel()
    private lateinit var binding: ItemDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        ItemDetailsFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = itemDetailsViewModel
            lifecycleOwner = viewLifecycleOwner
            binding = this
        }.root
}

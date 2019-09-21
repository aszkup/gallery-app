package com.android.galleryapp.view.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.galleryapp.databinding.GalleryFragmentBinding
import com.android.galleryapp.view.itemdetails.ItemDetailsActivity
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
    private val galleryAdapter = GalleryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures() // TODO move to base fragment in case of more fragments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        GalleryFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = galleryViewModel
            lifecycleOwner = viewLifecycleOwner
            binding = this
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeLayout.setOnRefreshListener { galleryViewModel.refresh() }
        binding.galleryRecyclerView.apply {
            adapter = galleryAdapter
        }
        galleryViewModel.galleryItems.observe(viewLifecycleOwner, Observer {
            galleryAdapter.setItems(it)
        })
        galleryAdapter.onItemClick = {
            val intent = Intent(requireActivity(), ItemDetailsActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }
    }
}

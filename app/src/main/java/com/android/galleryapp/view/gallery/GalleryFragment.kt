package com.android.galleryapp.view.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.galleryapp.databinding.GalleryFragmentBinding
import com.android.galleryapp.domain.gallery.GalleryItem
import com.android.galleryapp.view.itemdetails.ItemDetailsActivity
import com.android.galleryapp.view.itemdetails.ItemDetailsActivity.Companion.GALLERY_ITEM
import com.android.galleryapp.viewmodel.gallery.GalleryViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class GalleryFragment : Fragment() {

    private val galleryViewModel: GalleryViewModel by sharedViewModel()
    private lateinit var binding: GalleryFragmentBinding
    private val galleryAdapter = GalleryAdapter()

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
            addOnScrollListener(getOnScrollListener())
        }
        galleryViewModel.galleryItems.observe(viewLifecycleOwner, Observer {
            galleryAdapter.setItems(it)
        })
        galleryAdapter.onItemClick = { showDetails(it) }
    }

    private fun loadMore() {
        if (!galleryViewModel.isLoadingMore.value!!) {
            galleryViewModel.loadMore()
        }
    }

    private fun showDetails(galleryItem: GalleryItem) {
        val intent = Intent(requireActivity(), ItemDetailsActivity::class.java)
        intent.putExtra(GALLERY_ITEM, galleryItem)
        startActivity(intent)
    }

    private fun getOnScrollListener() = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val lastCompletelyVisibleItemPosition =
                (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()

            val totalItems = recyclerView.adapter!!.itemCount
            if (lastCompletelyVisibleItemPosition >= totalItems - 4) {
                loadMore()
            }
        }
    }
}

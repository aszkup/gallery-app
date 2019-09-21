package com.android.galleryapp.view.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.galleryapp.databinding.GalleryItemBinding
import com.android.galleryapp.domain.gallery.GalleryItem
import com.android.galleryapp.platform.extension.layoutInflater

class GalleryAdapter(
    private val items: MutableList<GalleryItem> = mutableListOf(),
    var onItemClick: (GalleryItem) -> Unit = {}
) : RecyclerView.Adapter<GalleryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        val binding = GalleryItemBinding.inflate(parent.layoutInflater, parent, false)
        return GalleryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount() = items.size

    fun setItems(entries: List<GalleryItem>) {
        items.clear()
        items.addAll(entries)
        notifyDataSetChanged()
    }
}

class GalleryItemViewHolder(
    private val binding: GalleryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(galleryItem: GalleryItem, onItemClick: (GalleryItem) -> Unit) {
        binding.item = galleryItem
        binding.executePendingBindings()
        itemView.setOnClickListener { onItemClick.invoke(galleryItem) }
    }
}

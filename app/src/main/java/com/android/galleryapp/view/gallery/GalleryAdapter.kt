package com.android.galleryapp.view.gallery

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.galleryapp.databinding.GalleryItemBinding
import com.android.galleryapp.domain.feed.Entry
import com.android.galleryapp.domain.gallery.GalleryItem
import com.android.galleryapp.platform.extension.layoutInflater

class GalleryAdapter(
    private val items: MutableList<Entry> = mutableListOf(),
    var onItemClick: (Entry) -> Unit = {}
) : RecyclerView.Adapter<GalleryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        val binding = GalleryItemBinding.inflate(parent.layoutInflater, parent, false)
        return GalleryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(items[position], onItemClick)
    }

    override fun getItemCount() = items.size

    fun setItems(entries: List<Entry>) {
        items.clear()
        items.addAll(entries)
        notifyDataSetChanged()
    }
}

class GalleryItemViewHolder(
    private val binding: GalleryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(galleryItem: Entry, onItemClick: (Entry) -> Unit) {
        val item = GalleryItem(galleryItem.id ?: "", galleryItem.title ?: "", galleryItem.link?.url ?: "")
        binding.item = item
        binding.executePendingBindings()
        itemView.setOnClickListener { onItemClick.invoke(galleryItem) }
    }
}

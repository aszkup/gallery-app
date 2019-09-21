package com.android.galleryapp.repository.gallery

import com.android.galleryapp.domain.gallery.GalleryItem
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Feed(
    @PropertyElement val id: String? = null,
    @PropertyElement val title: String? = null,
    @PropertyElement val subtitle: String? = null,
    @Element val entries: List<Entry>? = null
)

@Xml
data class Entry(
    @PropertyElement val title: String? = null,
    @Element val link: Link? = null,
    @PropertyElement val id: String,
    @Element val author: Author? = null,
    @PropertyElement val published: String? = null,
    @PropertyElement val updated: String? = null,
    @PropertyElement(name = "flickr:date_taken") val dateTakenFl: String? = null,
    @PropertyElement(name = "dc:date.Taken") val dateTakenDc: String? = null
)

@Xml
data class Author(
    @PropertyElement(name = "flickr:nsid") val id: String? = null,
    @PropertyElement val name: String? = null,
    @PropertyElement val uri: String? = null,
    @PropertyElement(name = "flickr:buddyico") val buddyIcon: String? = null
)

@Xml
data class Link(
    @Attribute(name = "href") val url: String? = null
)

fun Entry.toGalleryItem() = GalleryItem(id, title, published, dateTakenFl, link?.url, author?.toAuthor())

fun Author.toAuthor() = com.android.galleryapp.domain.gallery.Author(id, name, uri)

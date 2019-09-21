package com.android.galleryapp.domain.feed

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Feed(
    @PropertyElement val id: String? = null,
    @PropertyElement val title: String? = null,
    @PropertyElement val subtitle: String? = null
)

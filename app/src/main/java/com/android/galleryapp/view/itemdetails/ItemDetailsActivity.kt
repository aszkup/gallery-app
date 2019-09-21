package com.android.galleryapp.view.itemdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.galleryapp.R
import com.android.galleryapp.databinding.ItemDetailsActivityBinding
import com.android.galleryapp.platform.extension.showFragment
import com.android.galleryapp.viewmodel.itemdetails.ItemDetailsViewModel
import com.android.galleryapp.viewmodel.itemdetails.detailsModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

private val loadFeatures by lazy { loadKoinModules(detailsModule) }
private fun injectFeatures() = loadFeatures

class ItemDetailsActivity : AppCompatActivity() {

    private val itemDetailsViewModel: ItemDetailsViewModel by viewModel()
    private lateinit var binding: ItemDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()

        DataBindingUtil.setContentView<ItemDetailsActivityBinding>(this, R.layout.item_details_activity).apply {
            lifecycleOwner = this@ItemDetailsActivity
            binding = this
        }

        itemDetailsViewModel.item.postValue(intent.extras!!.getParcelable(GALLERY_ITEM))
        showFragment(ItemDetailsFragment(), R.id.fragment)
    }

    companion object {
        const val GALLERY_ITEM = "gallery_item"
    }
}

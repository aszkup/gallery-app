package com.android.galleryapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.galleryapp.R
import com.android.galleryapp.databinding.ActivityMainBinding
import com.android.galleryapp.viewmodel.gallery.GalleryViewModel
import com.android.galleryapp.viewmodel.gallery.galleryModule
import com.android.galleryapp.viewmodel.tags.tagsModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

private val loadFeatures by lazy { loadKoinModules(listOf(galleryModule, tagsModule)) }
private fun injectFeatures() = loadFeatures

class MainActivity : AppCompatActivity() {

    private val galleryViewModel: GalleryViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            galleryVM = galleryViewModel
            binding = this
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.date_sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_publish_date_sort -> {
                galleryViewModel.sortByPublishDate()
                true
            }
            R.id.menu_taken_date_sort -> {
                galleryViewModel.sortByTakenDate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

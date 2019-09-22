package com.android.galleryapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.galleryapp.R
import com.android.galleryapp.viewmodel.gallery.GalleryViewModel
import com.android.galleryapp.viewmodel.gallery.galleryModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

private val loadFeatures by lazy { loadKoinModules(galleryModule) }
private fun injectFeatures() = loadFeatures

class MainActivity : AppCompatActivity() {

    private val galleryViewModel: GalleryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
        setContentView(R.layout.activity_main)
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

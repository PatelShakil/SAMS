package com.shakilpatel.sams.users.ui.gallery.photos

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.LinearLayout
import android.widget.TextView
import com.shakilpatel.sams.databinding.ActivityPhotosDetailBinding
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.data.models.PhotoModel
import com.shakilpatel.sams.users.data.adapters.ImageSliderAdapter
import com.shakilpatel.sams.users.data.repo.GalleryRepo

class PhotosDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosDetailBinding
    private lateinit var viewModel: PhotosDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotosDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val colId = intent.getStringExtra("colId")!!
        val title = intent.getStringExtra("title")!!
        supportActionBar?.title = title
        viewModel = PhotosDetailViewModel(GalleryRepo())
        viewModel.getPhotosList(colId)

        viewModel.photoList.observe(this){
            binding.viewPager.adapter= ImageSliderAdapter(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
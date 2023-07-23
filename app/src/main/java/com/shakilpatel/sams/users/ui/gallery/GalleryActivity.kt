package com.shakilpatel.sams.users.ui.gallery

import android.os.Bundle
import com.shakilpatel.sams.admin.data.models.PhotoCollection
import com.shakilpatel.sams.databinding.ActivityGalleryBinding
import com.shakilpatel.sams.users.data.adapters.PhotosAdapter
import com.shakilpatel.sams.users.data.repo.GalleryRepo
import com.shakilpatel.sams.utils.BaseActivity

class GalleryActivity : BaseActivity() {
    lateinit var binding: ActivityGalleryBinding
    lateinit var viewModel: GalleryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Gallery"

        viewModel = GalleryViewModel(GalleryRepo())

        viewModel.photosCol.observe(this){
            val adapter = PhotosAdapter(it as ArrayList<PhotoCollection>)
            binding.galleryPhotosList.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }
}
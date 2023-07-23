package com.shakilpatel.sams.admin.ui.addphoto

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.data.adapters.AddPhotoAdapter
import com.shakilpatel.sams.admin.data.models.PhotoCollection
import com.shakilpatel.sams.admin.data.models.PhotoModel
import com.shakilpatel.sams.admin.data.repo.AddPhotoRepo
import com.shakilpatel.sams.admin.ui.main.AdminMainActivity
import com.shakilpatel.sams.databinding.ActivityAddPhotoBinding
import com.shakilpatel.sams.utils.Constants

class AddPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPhotoBinding
    private lateinit var pList: ArrayList<PhotoModel>
    private lateinit var viewModel: AddPhotoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Add Photos"
        viewModel = AddPhotoViewModel(AddPhotoRepo())

        pList = arrayListOf()

        val imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val selectedImages = mutableListOf<Uri>()

                    val data = result.data
                    if (data?.clipData != null) {
                        // Multiple images are selected
                        val clipData = data.clipData
                        for (i in 0 until clipData!!.itemCount) {
                            val imageUri: Uri = clipData.getItemAt(i).uri
                            selectedImages.add(imageUri)
                        }
                    } else if (data?.data != null) {
                        // Single image is selected
                        val imageUri: Uri = data.data!!
                        selectedImages.add(imageUri)
                    }

                    // Handle the selected images
                    handleSelectedImages(selectedImages)
                }
            }
        binding.collectionName.doOnTextChanged { text, start, before, count ->
            binding.addPhotosBtn.isEnabled = binding.collectionName.text.toString().isNotEmpty()
        }
        binding.addPhotosBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            imagePickerLauncher.launch(Intent.createChooser(intent, "Select Images"))
        }

    }

    private fun handleSelectedImages(images: List<Uri>) {
        // Process the selected images as per your requirement
        // For example, display them in an ImageView or upload them to a server

        for (imageUri in images) {
            // Do something with each image Uri
            Constants.createBitmapFromUri(this, imageUri)?.let {
                pList.add(
                    PhotoModel(
                        Constants.generateRandomValue(),
                        Constants.encodeImage(it),
                        System.currentTimeMillis()
                    )
                )
            }
        }
        binding.totalPhotosCount.visibility = View.VISIBLE
        binding.totalPhotosCount.text = pList.size.toString() + " photos selected"
        binding.photosCard.visibility = View.VISIBLE
        binding.pictureList.layoutManager = GridLayoutManager(this, 3)
        val adapter = AddPhotoAdapter(pList)
        binding.pictureList.adapter = adapter
        adapter.notifyDataSetChanged()

        binding.clearPhotosBtn.setOnClickListener {
            binding.totalPhotosCount.visibility = View.GONE
            pList.clear()
            adapter.notifyDataSetChanged()
        }

        binding.uploadPhotosBtn.setOnClickListener {
            val title = binding.collectionName.text.toString().trim()
            if (pList.isNotEmpty()) {
                viewModel.uploadPhotos(PhotoCollection("", title, pList)) {
                    if (it) {
                        Toast.makeText(this, "Photos Added Successfully", Toast.LENGTH_SHORT).show()
                        finishActivity(0)
                        startActivity(Intent(this, AdminMainActivity::class.java))
                    } else {
                        Toast.makeText(this, "Some Error Occured", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Photos List Was Empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.shakilpatel.sams.users.data.adapters

import android.provider.SyncStateContract.Constants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.data.models.PhotoModel
import com.shakilpatel.sams.databinding.SamplePhotosFullscreenBinding

class ImageSliderAdapter(private val images: List<PhotoModel>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sample_photos_fullscreen, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setData(images[position])
    }

    override fun getItemCount(): Int = images.size

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SamplePhotosFullscreenBinding.bind(itemView)
        fun setData(data : PhotoModel){
            binding.photoView.setImageBitmap(com.shakilpatel.sams.utils.Constants.decodeImage(data.data))
        }
    }
}
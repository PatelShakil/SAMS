package com.shakilpatel.sams.users.data.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.data.models.PhotoCollection
import com.shakilpatel.sams.databinding.SampleGalleryPhotosBinding
import com.shakilpatel.sams.users.ui.gallery.photos.PhotosDetailActivity
import com.shakilpatel.sams.utils.Constants

class PhotosAdapter(val pList : ArrayList<PhotoCollection>) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>(){
    class PhotosViewHolder(view : View):RecyclerView.ViewHolder(view){
        val binding = SampleGalleryPhotosBinding.bind(view)
        fun setData(data : PhotoCollection){
            binding.galleryImage.setImageBitmap(Constants.decodeImage(data.plist[0].data))
            binding.collectionTitle.text = data.title
            binding.galleryImage.setOnClickListener {
            val intent = Intent(binding.root.context,PhotosDetailActivity::class.java)
                intent.putExtra("title",data.title)
            intent.putExtra("colId",data.colId)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sample_gallery_photos,parent,false))
    }

    override fun getItemCount(): Int {
        return pList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.setData(pList[position])
    }
}
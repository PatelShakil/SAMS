package com.shakilpatel.sams.admin.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.data.models.PhotoModel
import com.shakilpatel.sams.databinding.SampleAddPictureviewBinding
import com.shakilpatel.sams.utils.Constants

class AddPhotoAdapter(val pList: ArrayList<PhotoModel>) :RecyclerView.Adapter<AddPhotoAdapter.AddPhotoViewHolder>() {
    class AddPhotoViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding : SampleAddPictureviewBinding
        init {
            binding = SampleAddPictureviewBinding.bind(view)
        }
        fun setData(data : PhotoModel){
            binding.pictureImage.setImageBitmap(Constants.decodeImage(data.data))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPhotoViewHolder {
        return AddPhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sample_add_pictureview,parent,false))
    }

    override fun getItemCount(): Int {
        return pList.size
    }

    override fun onBindViewHolder(holder: AddPhotoViewHolder, position: Int) {
        holder.setData(pList[position])
    }
}
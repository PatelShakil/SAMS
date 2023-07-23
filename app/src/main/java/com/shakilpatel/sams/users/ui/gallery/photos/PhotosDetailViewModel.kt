package com.shakilpatel.sams.users.ui.gallery.photos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shakilpatel.sams.admin.data.models.PhotoModel
import com.shakilpatel.sams.users.data.repo.GalleryRepo

class PhotosDetailViewModel(
    val repo : GalleryRepo
): ViewModel() {

    private val _photoList = MutableLiveData<List<PhotoModel>>()
    val photoList : MutableLiveData<List<PhotoModel>>
        get() = _photoList

    fun getPhotosList(colId : String) {
        repo.getPhotosList(colId){
            _photoList.value = it
        }
    }

}
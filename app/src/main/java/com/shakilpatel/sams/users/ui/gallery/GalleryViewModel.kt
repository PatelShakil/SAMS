package com.shakilpatel.sams.users.ui.gallery

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shakilpatel.sams.admin.data.models.PhotoCollection
import com.shakilpatel.sams.users.data.repo.GalleryRepo

class GalleryViewModel(
    val repo : GalleryRepo
) : ViewModel(){

    private val _photosCol = MutableLiveData<List<PhotoCollection>>()
    val photosCol : MutableLiveData<List<PhotoCollection>>
        get() = _photosCol
    init {
        getPhotosCollection()
    }

    fun getPhotosCollection(){
        repo.getPhotosCollection {
            _photosCol.value = it
        }
    }
}
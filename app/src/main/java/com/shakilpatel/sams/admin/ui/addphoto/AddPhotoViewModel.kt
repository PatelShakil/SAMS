package com.shakilpatel.sams.admin.ui.addphoto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shakilpatel.sams.admin.data.models.PhotoCollection
import com.shakilpatel.sams.admin.data.repo.AddPhotoRepo

class AddPhotoViewModel(
    val repo : AddPhotoRepo
): ViewModel() {

    fun uploadPhotos(data : PhotoCollection,callback:(Boolean)->Unit)  {
        repo.uploadPhotos(data){
            callback(it)
        }
    }

}
package com.shakilpatel.sams.admin.data.repo

import com.google.firebase.firestore.FirebaseFirestore
import com.shakilpatel.sams.admin.data.models.PhotoCollection
import com.shakilpatel.sams.admin.data.models.PhotoModel
import com.shakilpatel.sams.utils.Constants

class AddPhotoRepo {
    val db = FirebaseFirestore.getInstance()
    fun uploadPhotos(data : PhotoCollection,callback : (Boolean)-> Unit){
        data.colId = Constants.generateRandomValue()
        db.collection("photos")
            .document(data.colId)
            .set(data)
            .addOnSuccessListener {
                callback(true)
            }
    }
}
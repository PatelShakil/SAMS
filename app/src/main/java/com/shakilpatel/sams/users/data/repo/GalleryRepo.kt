package com.shakilpatel.sams.users.data.repo

import android.util.Log
import com.google.android.play.core.integrity.p
import com.google.firebase.firestore.FirebaseFirestore
import com.shakilpatel.sams.admin.data.models.PhotoCollection
import com.shakilpatel.sams.admin.data.models.PhotoModel

class GalleryRepo {
    val db = FirebaseFirestore.getInstance()

    fun getPhotosCollection(callback: (List<PhotoCollection>) -> Unit) {
        db.collection("photos")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }
                val pList: MutableList<PhotoCollection> = mutableListOf()
                if (value != null && !value.isEmpty) { // Check if the result is not empty
                    pList.clear()
                    for (document in value.documents) {
                        document.toObject(PhotoCollection::class.java)?.let { pList.add(it) }
                    }
                }
                callback(pList)
            }
    }
    fun getPhotosList(colId : String,callback: (List<PhotoModel>) -> Unit) {
        db.collection("photos")
            .document(colId)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }
                var pList: MutableList<PhotoModel> = mutableListOf()
                if (value != null && value.exists()) { // Check if the result is not empty
                    val photoCol = value.toObject(PhotoCollection::class.java)
                    pList = photoCol?.plist as MutableList<PhotoModel>
                }
                callback(pList)
            }
    }

}
package com.shakilpatel.sams.users.data.repo

import com.google.firebase.firestore.FirebaseFirestore
import com.shakilpatel.sams.admin.data.models.TeacherModel

class TeacherRepo {
    val db = FirebaseFirestore.getInstance()

    fun getTeacherData(callback: (List<TeacherModel>) -> Unit) {
        db.collection("teacher")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }
                val tList: MutableList<TeacherModel> = mutableListOf()
                if (value != null) {
                    if (value.documents.isNotEmpty()) {
                        for (t in value.documents) {
                            t.toObject(TeacherModel::class.java)?.let { tList.add(it) }
                        }
                    }
                }
                callback(tList)
            }
    }
}
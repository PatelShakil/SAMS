package com.shakilpatel.sams.admin.data.repo

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shakilpatel.sams.admin.data.models.TeacherModel
import com.shakilpatel.sams.utils.Constants
import com.shakilpatel.sams.utils.Constants.Companion.generateRandomValue
import java.util.Random

class AddTeacherRepo {
    val db = FirebaseFirestore.getInstance()

    fun addTeacher(data : TeacherModel,context : Context){
        data.teacherId = generateRandomValue()
        db.collection("teacher")
            .document(data.teacherId)
            .set(data).addOnSuccessListener {
                Toast.makeText(context, "Added Details Successfully", Toast.LENGTH_SHORT).show()
            }
    }

}
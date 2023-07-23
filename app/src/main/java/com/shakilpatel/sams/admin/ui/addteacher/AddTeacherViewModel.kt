package com.shakilpatel.sams.admin.ui.addteacher

import android.content.Context
import androidx.lifecycle.ViewModel
import com.shakilpatel.sams.admin.data.models.TeacherModel
import com.shakilpatel.sams.admin.data.repo.AddTeacherRepo

class AddTeacherViewModel(
    private val repo : AddTeacherRepo
):ViewModel() {

    fun addTeacher(data : TeacherModel,context : Context){
        repo.addTeacher(data, context)
    }
}
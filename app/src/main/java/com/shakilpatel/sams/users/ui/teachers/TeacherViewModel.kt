package com.shakilpatel.sams.users.ui.teachers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shakilpatel.sams.admin.data.models.TeacherModel
import com.shakilpatel.sams.users.data.repo.TeacherRepo

class TeacherViewModel(
    val repo: TeacherRepo
) : ViewModel() {

    private val _teacherList = MutableLiveData<List<TeacherModel>>()
    val teacherList: LiveData<List<TeacherModel>>
        get() = _teacherList
    init {
        getTeacherData()
    }
    fun getTeacherData() {
        repo.getTeacherData {
            _teacherList.value = it
        }
    }
}
package com.shakilpatel.sams.admin.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakilpatel.sams.admin.data.repo.LoginRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val repo = LoginRepo()
    fun doLogin(email : String,pass: String) : LiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
                val result = repo.doLogin(email, pass)
                isSuccess.postValue(result)
        }
        return isSuccess
    }
}
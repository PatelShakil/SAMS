package com.shakilpatel.sams.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shakilpatel.sams.users.data.repo.SplashRepo

class SplashViewModel(
    val repo : SplashRepo
) : ViewModel() {

    private val _isSubscribed = MutableLiveData<Boolean>()
    val isSubscribed : MutableLiveData<Boolean>
        get() = _isSubscribed
    init {
        doSubscribe()
    }

    fun doSubscribe(){
        repo.doSubscribe {
            _isSubscribed.value = it
        }
    }
}
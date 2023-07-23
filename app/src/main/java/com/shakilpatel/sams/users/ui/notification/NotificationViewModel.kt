package com.shakilpatel.sams.users.ui.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shakilpatel.sams.users.data.repo.NotificationRepo
import com.shakilpatel.sams.utils.notification.PushNotification

class NotificationViewModel(
    private val repo : NotificationRepo
) : ViewModel() {

    private val _notiList = MutableLiveData<List<PushNotification>>()
    val notiList : MutableLiveData<List<PushNotification>>
        get() = _notiList

    init {
        getNotificationList()
    }

    private fun getNotificationList(){
        repo.getNotificationList { list ->
            _notiList.value = list
            _notiList.value?.sortedBy { it.data.time }
        }
    }
}
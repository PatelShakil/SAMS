package com.shakilpatel.sams.admin.ui.addreminder

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.shakilpatel.sams.admin.data.repo.AddReminderRepo
import com.shakilpatel.sams.utils.notification.PushNotification
import retrofit2.Response

class AddReminderViewModel(
    val repo: AddReminderRepo
) : ViewModel() {

    fun sendNotification(
        notification: PushNotification,
        callback: (Task<Void>, Response<PushNotification?>) -> Unit
    ) {
        repo.sendNotification(notification) { task, result ->
            print(result.message())
            callback(task, result)
        }
    }
}
package com.shakilpatel.sams.admin.data.repo

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.play.integrity.internal.t
import com.google.firebase.firestore.FirebaseFirestore
import com.shakilpatel.sams.utils.notification.ApiUtils
import com.shakilpatel.sams.utils.notification.PushNotification
import retrofit2.Response

class AddReminderRepo {
    val db = FirebaseFirestore.getInstance()
    fun sendNotification(
        notification: PushNotification,
        callback: (Task<Void>, result: Response<PushNotification?>) -> Unit
    ) {
        ApiUtils.client.sendNotification(notification)
            ?.enqueue(object : retrofit2.Callback<PushNotification?> {
                override fun onResponse(
                    call: retrofit2.Call<PushNotification?>,
                    response: retrofit2.Response<PushNotification?>
                ) {
                    //handle on successfully sending notification
                    Log.d("OnSuccess", response.body().toString())
                    storeNotification(notification){
                        callback(it,response)
                    }
                }

                override fun onFailure(call: retrofit2.Call<PushNotification?>, t: Throwable) {
                    //handle on sending notification failure
                    Log.d("OnFailure",t.message.toString())
                }
            })
    }

    fun storeNotification(notification: PushNotification, callback: (Task<Void>) -> Unit) {
        db.collection("reminders")
            .document(notification.notiId)
            .set(notification)
            .addOnCompleteListener {
                callback(it)
            }
    }
}
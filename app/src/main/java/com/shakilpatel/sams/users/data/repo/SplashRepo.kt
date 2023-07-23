package com.shakilpatel.sams.users.data.repo

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.shakilpatel.sams.utils.notification.Cons.ALL
import com.shakilpatel.sams.utils.notification.Cons.TOPIC_ALL

class SplashRepo {
    val messaging = FirebaseMessaging.getInstance()
    fun doSubscribe(topic : String = ALL,callback:(Boolean) -> Unit){
        messaging.subscribeToTopic(topic)
            .addOnSuccessListener {
                Log.d("Subscribe Task",it.toString()+ "Success")
                callback(true)
            }
            .addOnFailureListener {
                Log.d("Subscribe Error",it.message.toString())
            }
    }
}
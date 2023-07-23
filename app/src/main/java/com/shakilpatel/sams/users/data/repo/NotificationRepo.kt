package com.shakilpatel.sams.users.data.repo

import com.google.firebase.firestore.FirebaseFirestore
import com.shakilpatel.sams.utils.notification.PushNotification

class NotificationRepo {
    val db = FirebaseFirestore.getInstance()

    fun getNotificationList(callback: (List<PushNotification>) -> Unit){
        val list = mutableListOf<PushNotification>()
        db.collection("reminders")
            .addSnapshotListener { value, error ->
                if(error != null){
                    return@addSnapshotListener
                }
                if(value != null && !value.isEmpty){
                    for (notification in value.documents){
                        list.add(notification.toObject(PushNotification::class.java)!!)
                    }
                }
                callback(list)
            }
    }
}
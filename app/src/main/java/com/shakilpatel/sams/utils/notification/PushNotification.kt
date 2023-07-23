package com.shakilpatel.sams.utils.notification

import com.shakilpatel.sams.utils.Constants

data class PushNotification(val notiId : String,var data: NotificationData, var to: String){
    constructor():this("",NotificationData(),"")
}
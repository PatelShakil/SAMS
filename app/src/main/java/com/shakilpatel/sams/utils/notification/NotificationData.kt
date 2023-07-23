package com.shakilpatel.sams.utils.notification

data class NotificationData(var title: String, var message: String,var time:Long){
    constructor(): this("","",0)
}
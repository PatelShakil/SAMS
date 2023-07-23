package com.shakilpatel.sams.admin.data.models

data class Notification(
    val notiId: String,
    val message: String,
    val time: Long
){
    constructor():this("","",0)
}
package com.shakilpatel.sams.admin.data.models

data class PhotoModel(
    val pId : String,
    val data : String,
    val time : Long
){
    constructor():this("","",0)
}
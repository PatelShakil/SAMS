package com.shakilpatel.sams.admin.data.models

data class PhotoCollection(
    var colId : String,
    val title : String,
    val plist: List<PhotoModel>
){
    constructor():this("","", listOf())
}
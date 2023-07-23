package com.shakilpatel.sams.admin.data.models

data class TeacherModel(
    var teacherId : String,
    var name : String,
    var des : String,
    var qualification : String,
    val gender:String
){
    constructor() : this("","","","","")
}
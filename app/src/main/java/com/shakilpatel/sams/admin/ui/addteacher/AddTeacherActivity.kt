package com.shakilpatel.sams.admin.ui.addteacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.data.models.TeacherModel
import com.shakilpatel.sams.admin.data.repo.AddTeacherRepo
import com.shakilpatel.sams.databinding.ActivityAddTeacherBinding

class AddTeacherActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddTeacherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Add Teacher"

        val viewModel = AddTeacherViewModel(AddTeacherRepo())

        binding.saveTeacherBtn.setOnClickListener {
            val name = binding.addTeacherName.text.toString().trim()
            val qua = binding.addTeacherQua.text.toString().trim()
            val des = binding.addTeacherDes.text.toString().trim()
            var gender = "Male"
            gender = if (binding.female.isChecked) {
                "Female"
            } else {
                "Male"
            }
            viewModel.addTeacher(TeacherModel("", name, des, qua, gender), this)
            binding.addTeacherDes.setText("")
            binding.addTeacherName.setText("")
            binding.addTeacherQua.setText("")
        }
    }
}
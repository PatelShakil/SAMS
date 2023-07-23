package com.shakilpatel.sams.admin.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.ui.addphoto.AddPhotoActivity
import com.shakilpatel.sams.admin.ui.addreminder.AddReminderActivity
import com.shakilpatel.sams.admin.ui.addteacher.AddTeacherActivity
import com.shakilpatel.sams.databinding.ActivityAdminMainBinding

class AdminMainActivity : AppCompatActivity() {
    lateinit var binding : ActivityAdminMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Welcome Sir"

        binding.addTeacher.setOnClickListener {
            startActivity(Intent(this,AddTeacherActivity::class.java))
        }
        binding.addPhoto.setOnClickListener {
            startActivity(Intent(this,AddPhotoActivity::class.java))
        }
        binding.addReminder.setOnClickListener {
            startActivity(Intent(this, AddReminderActivity::class.java))
        }
    }
}
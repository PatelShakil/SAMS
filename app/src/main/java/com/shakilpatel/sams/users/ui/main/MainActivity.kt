package com.shakilpatel.sams.users.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.ui.auth.LoginActivity
import com.shakilpatel.sams.admin.ui.main.AdminMainActivity
import com.shakilpatel.sams.databinding.ActivityMainBinding
import com.shakilpatel.sams.users.ui.classes.ClassesActivity
import com.shakilpatel.sams.users.ui.gallery.GalleryActivity
import com.shakilpatel.sams.users.ui.notification.NotificationActivity
import com.shakilpatel.sams.users.ui.teachers.TeachersActivity
import com.shakilpatel.sams.utils.BaseActivity
import render.animations.Render
import render.animations.Slide

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Welcome to SAMS"


        val r = Render(this)
        r.setAnimation(Slide.InUp(binding.root))
        r.start()
        binding.mainTeachers.setOnClickListener {
            startActivity(Intent(this, TeachersActivity::class.java))
        }
        binding.mainGallery.setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
            }
        binding.mainReminder.setOnClickListener {
            startActivity(Intent(this,NotificationActivity::class.java))
        }


    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.admin_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.admin_login -> {
                if(FirebaseAuth.getInstance().uid?.isNotEmpty() == true){
                    startActivity(Intent(this, AdminMainActivity::class.java))
                }else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                true
            }
            else -> {
                true
            }
        }
    }
}
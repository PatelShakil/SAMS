package com.shakilpatel.sams.utils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.shakilpatel.sams.databinding.ActivitySplashBinding
import com.shakilpatel.sams.users.data.repo.SplashRepo
import com.shakilpatel.sams.users.ui.main.MainActivity
import render.animations.Render
import render.animations.Zoom

class SplashActivity : BaseActivity() {
    lateinit var binding : ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = SplashViewModel(SplashRepo())

        viewModel.isSubscribed.observe(this){
                Log.d("Splash",it.toString())
        }

        val r = Render(this)
        r.setAnimation(Zoom.In(binding.splashLogo))
        r.start()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        thread.start()
    }
}
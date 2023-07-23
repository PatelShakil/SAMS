package com.shakilpatel.sams.admin.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shakilpatel.sams.admin.ui.main.AdminMainActivity
import com.shakilpatel.sams.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Admin Login"

        viewModel = LoginViewModel()

        binding.loginBtn.setOnClickListener {
            val email = binding.emailLogin.text.toString().trim()
            val pass = binding.passLogin.text.toString().trim()
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                viewModel.doLogin(email, pass)
                if (viewModel.repo.auth.uid?.isNotEmpty() == true) {
                    startActivity(Intent(this, AdminMainActivity::class.java))
                } else {
                    Toast.makeText(this, "Provide Correct Details", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "provide appropriate details", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
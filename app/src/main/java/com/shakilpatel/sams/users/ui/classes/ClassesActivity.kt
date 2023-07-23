package com.shakilpatel.sams.users.ui.classes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shakilpatel.sams.databinding.ActivityClassesBinding
import com.shakilpatel.sams.utils.BaseActivity

class ClassesActivity : BaseActivity() {
    lateinit var binding : ActivityClassesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
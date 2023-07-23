package com.shakilpatel.sams.users.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shakilpatel.sams.R
import com.shakilpatel.sams.databinding.ActivityNotificationBinding
import com.shakilpatel.sams.users.data.adapters.NotificationAdapter
import com.shakilpatel.sams.users.data.repo.NotificationRepo

class NotificationActivity : AppCompatActivity() {
    lateinit var binding : ActivityNotificationBinding
    lateinit var viewModel: NotificationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Notifications"

        viewModel = NotificationViewModel(NotificationRepo())

        viewModel.notiList.observe(this){
            val adapter = NotificationAdapter(it)
            binding.notiList.adapter = adapter
            adapter.notifyDataSetChanged()
        }


    }
}
package com.shakilpatel.sams.admin.ui.addreminder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.shakilpatel.sams.R
import com.shakilpatel.sams.admin.data.repo.AddReminderRepo
import com.shakilpatel.sams.databinding.ActivityAddReminderBinding
import com.shakilpatel.sams.utils.Constants
import com.shakilpatel.sams.utils.notification.ApiUtils
import com.shakilpatel.sams.utils.notification.Cons.TOPIC_ALL
import com.shakilpatel.sams.utils.notification.NotificationData
import com.shakilpatel.sams.utils.notification.PushNotification
import retrofit2.Call
import retrofit2.Response

class AddReminderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddReminderBinding
    private lateinit var viewModel: AddReminderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Add Reminder"

        viewModel = AddReminderViewModel(AddReminderRepo())

        binding.layout.notiTime.text = Constants.convertLongToDate(System.currentTimeMillis(),"hh:mm a dd/MMM/yy")

        binding.titleReminder.doOnTextChanged { text, start, before, count ->
            binding.layout.notiTitle.text = binding.titleReminder.text
            binding.publishReminderBtn.isEnabled =
                !binding.messageReminder.text.isNullOrEmpty() && !binding.titleReminder.text.isNullOrEmpty()
            binding.messageReminder.doOnTextChanged { text, start, before, count ->
                binding.layout.notiMessage.text = binding.messageReminder.text
                binding.publishReminderBtn.isEnabled =
                    !binding.messageReminder.text.isNullOrEmpty() && !binding.titleReminder.text.isNullOrEmpty()
            }
        }

        binding.publishReminderBtn.setOnClickListener {
            val title = binding.titleReminder.text.toString().trim()
            val message = binding.messageReminder.text.toString().trim()
            viewModel.sendNotification(
                PushNotification(
                    Constants.generateRandomValue(),
                    NotificationData(title, message, System.currentTimeMillis()), TOPIC_ALL
                )
            ) { task, result ->
                print(result.message())
                if (task.isSuccessful && result.isSuccessful) {
                    binding.titleReminder.setText("")
                    binding.messageReminder.setText("")
                    Toast.makeText(this, "Reminder Published Successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    task.exception?.printStackTrace()
                    Log.d("AddReminderActivity", result.message().toString())
                    Toast.makeText(this, "Some Error Occured", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
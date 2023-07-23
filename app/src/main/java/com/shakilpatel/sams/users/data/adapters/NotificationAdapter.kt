package com.shakilpatel.sams.users.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shakilpatel.sams.R
import com.shakilpatel.sams.databinding.SampleNotificationBinding
import com.shakilpatel.sams.utils.Constants
import com.shakilpatel.sams.utils.notification.PushNotification

class NotificationAdapter(val list : List<PushNotification>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    class NotificationViewHolder(view : View) : RecyclerView.ViewHolder(view){
        lateinit var binding : SampleNotificationBinding
        init {
            binding = SampleNotificationBinding.bind(view)
        }
        fun setData(data : PushNotification){
            binding.notiTitle.text = data.data.title
            binding.notiMessage.text = data.data.message
            binding.notiTime.text = Constants.convertLongToDate(data.data.time,"hh:mm a dd/MMM/yy")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sample_notification,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.setData(list[position])
    }
}
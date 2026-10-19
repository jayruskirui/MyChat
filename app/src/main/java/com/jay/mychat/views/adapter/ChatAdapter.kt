package com.jay.mychat.views.adapter

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.jay.mychat.R
import com.jay.mychat.data.model.ChatMessage
import com.jay.mychat.databinding.ItemChatBinding

class ChatAdapter(private var list: List<ChatMessage>) :
    RecyclerView.Adapter<ChatAdapter.VH>() {

    inner class VH(val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        val binding = ItemChatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {
        val message = list[position]
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        val isSentByMe = message.senderId == currentUserId

        holder.binding.tvMessage.text = message.message
        holder.binding.tvSender.text = if (isSentByMe) "You" else message.senderId

        if (isSentByMe) {
            holder.binding.layoutMain.gravity = Gravity.END
            holder.binding.messageContainer.setBackgroundResource(R.drawable.bg_message_sent)
            holder.binding.tvMessage.setTextColor(Color.WHITE)
            holder.binding.tvSender.setTextColor(Color.parseColor("#E0E0E0"))
        } else {
            holder.binding.layoutMain.gravity = Gravity.START
            holder.binding.messageContainer.setBackgroundResource(R.drawable.bg_message_received)
            holder.binding.tvMessage.setTextColor(Color.BLACK)
            holder.binding.tvSender.setTextColor(Color.parseColor("#333333"))
        }
    }

    override fun getItemCount() = list.size

    fun update(newList: List<ChatMessage>) {
        list = newList
        notifyDataSetChanged()
    }
}
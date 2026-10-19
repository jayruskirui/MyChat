package com.jay.mychat.views.chat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.mychat.databinding.ActivityChatBinding
import com.jay.mychat.views.adapter.ChatAdapter

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var receiverId: String
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiverId = intent.getStringExtra("receiverId") ?: ""

        adapter = ChatAdapter(emptyList())
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        binding.recyclerview.adapter = adapter

        if (receiverId.isNotEmpty()) {
            viewModel.startChat(receiverId)
        }

        binding.btnsend.setOnClickListener {
            val messageText = binding.etmessage.text.toString().trim()
            if (messageText.isNotEmpty() && receiverId.isNotEmpty()) {
                viewModel.sendMessage(
                    receiverId,
                    messageText
                )
                binding.etmessage.text.clear()
            }
        }

        viewModel.messages.observe(this) {
            adapter.update(it)
        }

    }
}
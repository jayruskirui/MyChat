package com.jay.mychat.views.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.jay.mychat.data.model.ChatMessage
import com.jay.mychat.data.repository.ChatRepository

class ChatViewModel : ViewModel()  {

    private val repository = ChatRepository()
    val messages = MutableLiveData<List<ChatMessage>>()

    fun startChat(receiverId: String) {
        val senderId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        repository.listenMessages(receiverId, senderId) {
            messages.postValue(it)
        }
    }

    fun sendMessage(receiverId: String, message: String) {
        val senderId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        repository.sendMessage(message, receiverId, senderId)
    }

}
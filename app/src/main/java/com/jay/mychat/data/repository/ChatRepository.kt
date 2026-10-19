package com.jay.mychat.data.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jay.mychat.data.model.ChatMessage

class ChatRepository {

    private val db = FirebaseDatabase.getInstance().getReference("chats")

    fun sendMessage(message: String, receiverId: String, senderId: String) {
        val chatId = getChatId(receiverId, senderId)

        val ref = db.child(chatId)
        ref.push().setValue(
            ChatMessage(
                senderId = senderId,
                receiverId = receiverId,
                message = message
            )
        )
    }

    fun listenMessages(receiverId: String, senderId: String, onMessageReceived: (List<ChatMessage>) -> Unit) {

        val chatId = getChatId(senderId, receiverId)
        db.child(chatId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<ChatMessage>()

                    snapshot.children.forEach {
                        it.getValue(ChatMessage::class.java)
                            ?.let { msg -> list.add(msg) }
                    }
                    onMessageReceived(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Error:", error.message)
                }

            })
    }

    private fun getChatId(receiverId: String, senderId: String): String {
        return if (senderId < receiverId)
            "${senderId}_${receiverId}"
        else
            "${receiverId}_${senderId}"
    }
}
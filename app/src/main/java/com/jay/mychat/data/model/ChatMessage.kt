package com.jay.mychat.data.model

data class ChatMessage(
    val senderId: String = "",
    val receiverId: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val message: String = ""
)

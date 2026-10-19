package com.jay.mychat.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jay.mychat.data.model.User

class UserRepository {

    private val ref = FirebaseDatabase.getInstance().getReference("users")

    fun getUsers(callback: (List<User>) -> Unit) {
        val currentUid = FirebaseAuth.getInstance().uid
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<User>()
                snapshot.children.forEach {
                    val user = it.getValue(User::class.java)
                    if (user != null && user.uid != currentUid) {
                        list.add(user)
                    }
                    callback(list)
                }
            }

            override fun onCancelled(p0: DatabaseError) {}
        })
    }

}
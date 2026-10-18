package com.jay.mychat.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    fun login(email: String, password: String, result: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result(true, null)
                } else {
                    result(false, it.exception?.message)
                }
            }
    }

    fun register(email: String, password: String, result: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result(true, null)
                } else {
                    result(false, it.exception?.message)
                }
            }
    }

    fun logout() = auth.signOut()
}


package com.jay.mychat.views.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jay.mychat.data.model.User
import com.jay.mychat.data.repository.AuthRepository
import com.jay.mychat.data.repository.UserRepository

class UsersViewModel : ViewModel() {

    private val repo = UserRepository()
    private val authRepository = AuthRepository()

    val users = MutableLiveData<List<User>>()

    init {
        repo.getUsers {
            users.postValue(it)
        }
    }

    fun logout() = authRepository.logout()
}
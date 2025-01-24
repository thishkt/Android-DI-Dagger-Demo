package com.hkt.daggerdemo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hkt.daggerdemo.data.UserRepository
import javax.inject.Inject

private const val TAG = "UserViewModel"

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    
    fun addUser(name: String) {
        Log.d(TAG, "Adding user: $name")
        userRepository.addUser(name)
    }
    
    fun getUsers(): List<String> {
        val users = userRepository.getUsers()
        Log.d(TAG, "Retrieved ${users.size} users")
        return users
    }
} 
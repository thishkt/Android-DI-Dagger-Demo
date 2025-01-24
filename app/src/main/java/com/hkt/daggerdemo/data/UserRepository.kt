package com.hkt.daggerdemo.data

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "UserRepository"

@Singleton
class UserRepository @Inject constructor() {
    private val users = mutableListOf<String>()
    
    fun addUser(name: String) {
        Log.d(TAG, "Adding user to repository: $name")
        users.add(name)
        Log.d(TAG, "User added successfully. Total users in repository: ${users.size}")
    }
    
    fun getUsers(): List<String> {
        Log.d(TAG, "Getting all users. Current count: ${users.size}")
        return users.toList()
    }
} 
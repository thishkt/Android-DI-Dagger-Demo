package com.hkt.daggerdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hkt.daggerdemo.ui.UserViewModel
import javax.inject.Inject

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            // 在 super.onCreate 之前注入依賴
            (application as DaggerDemoApplication).appComponent.inject(this)
            Log.d(TAG, "Dependency injection successful")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to inject dependencies", e)
        }
        
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserScreen(userViewModel)
                }
            }
        }
    }
}

@Composable
fun UserScreen(viewModel: UserViewModel) {
    var newUserName by remember { mutableStateOf("") }
    var users by remember { mutableStateOf(viewModel.getUsers()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()  // 確保內容不會被系統 UI 遮擋
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = newUserName,
                onValueChange = { newUserName = it },
                modifier = Modifier.weight(1f),
                label = { Text("使用者名稱") },
                singleLine = true  // 限制單行輸入
            )
            
            Button(
                onClick = {
                    try {
                        if (newUserName.isNotBlank()) {
                            Log.d(TAG, "Adding new user: $newUserName")
                            viewModel.addUser(newUserName)
                            users = viewModel.getUsers()  // 更新使用者列表
                            newUserName = ""
                            Log.d(TAG, "User added successfully. Total users: ${users.size}")
                        } else {
                            Log.w(TAG, "Attempted to add empty username")
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Failed to add user", e)
                    }
                }
            ) {
                Text("新增")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(users) { user ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = user,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
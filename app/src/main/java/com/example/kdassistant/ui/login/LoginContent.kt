package com.example.kdassistant.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kdassistant.utils.Async

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val u = ""  // FIXME: Delete
    val p = ""  // FIXME: Delete
    var username by remember { mutableStateOf(u) }
    var password by remember { mutableStateOf(p) }
    val loginState by loginViewModel.loginState.collectAsState()
    val courseState by loginViewModel.courseState.collectAsState()

    Column(modifier) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("username") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("password") }
        )

        Row {
            Button(onClick = { loginViewModel.login(username, password) }) {
                Text("Login")
            }

            Button(onClick = { loginViewModel.getCurrentSemesterCourses("14") }) {
                Text("Get Course")
            }
        }

        when (loginState) {
            is Async.Loading -> {
                Text("Login...")
            }
            is Async.Success -> {
                val r = (loginState as Async.Success).data
                Text("Login success")
            }
            is Async.Error -> {
                Text("Error: ${(loginState as Async.Error).errorMessage}")
            }
        }

        when (courseState) {
            is Async.Loading -> {
                Text("Getting Course...")
            }
            is Async.Success -> {
                val r = (courseState as Async.Success).data
                Text("$r")
            }
            is Async.Error -> {
                Text("Error: ${(courseState as Async.Error).errorMessage}")
            }
        }
    }
}

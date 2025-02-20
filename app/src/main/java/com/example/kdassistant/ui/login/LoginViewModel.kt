package com.example.kdassistant.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kdassistant.server.CurrentSemesterCoursesData
import com.example.kdassistant.server.LoginData
import com.example.kdassistant.server.LoginReq
import com.example.kdassistant.server.TokenPrefs
import com.example.kdassistant.server.repository.AuthRepository
import com.example.kdassistant.server.repository.CourseRepository
import com.example.kdassistant.utils.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val courseRepository: CourseRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _loginState: MutableStateFlow<Async<LoginData>> = MutableStateFlow(Async.Loading)
    val loginState = _loginState.asStateFlow()

    private val _courseState: MutableStateFlow<Async<CurrentSemesterCoursesData>> =
        MutableStateFlow(Async.Loading)
    val courseState = _courseState.asStateFlow()
    
    fun login(username: String, password: String) {
        _loginState.value = Async.Loading;
        val loginInfo = LoginReq(username, password)
        
        viewModelScope.launch {
            try {
                val response = authRepository.login(loginInfo)
                _loginState.value = Async.Success(response.data)
                TokenPrefs.saveToken(context, response.data.token)
            } catch (e: Exception) {
                _loginState.value = Async.Error(e.toString())
            }
        }
    }

    fun getCurrentSemesterCourses(week: String) {
        _courseState.value = Async.Loading;

        viewModelScope.launch {
            try {
                val response = courseRepository.getCurrentSemesterCourses(week)
                _courseState.value = Async.Success(response.data)
            } catch (e: Exception) {
                _courseState.value = Async.Error(e.toString())
            }
        }
    }
}

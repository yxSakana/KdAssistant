package com.example.kdassistant.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kdassistant.server.CurrentSemesterCoursesData
import com.example.kdassistant.server.repository.CourseRepository
import com.example.kdassistant.utils.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
) : ViewModel() {
    private val _courseState: MutableStateFlow<Async<CurrentSemesterCoursesData>> =
        MutableStateFlow(Async.Loading)
    val courseState = _courseState.asStateFlow()

    fun getCurrentSemesterCourses(week: String) {
        _courseState.value = Async.Loading;

        viewModelScope.launch {
            try {
                val response = courseRepository.getCurrentSemesterCourses(week)
                _courseState.value = Async.Success(response.data)
            } catch (e: Exception) {
                _courseState.value = Async.Error("")
            }
        }
    }
}

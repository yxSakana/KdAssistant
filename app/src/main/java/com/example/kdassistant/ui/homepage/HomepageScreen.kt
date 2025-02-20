package com.example.kdassistant.ui.homepage

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kdassistant.data.Course
import com.example.kdassistant.ui.login.LoginContent
import com.example.kdassistant.ui.timetable.TimetableContent

@Composable
fun HomepageScreen(
    modifier: Modifier = Modifier,
//    viewModel: HomepageViewModel = hiltViewModel(),
) {
    Scaffold(
        modifier = modifier,
    ) { paddingValues ->
        LoginContent(modifier = modifier.padding(paddingValues))
//        TimetableContent(modifier = modifier.padding(paddingValues), courses = listOf(Course(
//            1,2,"示例", "哈哈哈", "D212", 14, "14", "1-2"
//        )))
    }
}

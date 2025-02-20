package com.example.kdassistant.ui.timetable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.kdassistant.data.Course
import com.example.kdassistant.R

@Composable
fun TimetableContent(
    modifier: Modifier,
    courses: List<Course>,
) {
    Column(modifier = modifier) {
        Text(stringResource(R.string.today_school_timetable))
        HorizontalDivider()
        for (course in courses) {
            TimetableItem(course = course)
        }
    }
}

@Composable
fun TimetableItem(
    modifier: Modifier = Modifier,
    course: Course,
) {
    Column(modifier = modifier) {
        Column {
            Text(course.name)
            Text(course.classroom)
            Text(course.times)
        }
    }
}

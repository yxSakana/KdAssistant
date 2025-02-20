package com.example.kdassistant.server.repository

import com.example.kdassistant.server.ApiRes
import com.example.kdassistant.server.ApiServer
import com.example.kdassistant.server.CurrentSemesterCoursesData
import com.example.kdassistant.server.LoginData
import com.example.kdassistant.server.LoginReq
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiServer: ApiServer
) {
    suspend fun login(req: LoginReq): ApiRes<LoginData> {
        return apiServer.login(req)
    }
}

class CourseRepository @Inject constructor(
    private val apiServer: ApiServer
) {
    suspend fun getCurrentSemesterCourses(week: String): ApiRes<CurrentSemesterCoursesData> {
        return apiServer.getCurrentSemesterCourses(week)
    }
}

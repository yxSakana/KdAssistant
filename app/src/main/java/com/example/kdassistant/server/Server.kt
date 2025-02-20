package com.example.kdassistant.server

// std
import android.content.Context
import com.example.kdassistant.data.Course
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

// okhttp
import okhttp3.Interceptor
import okhttp3.Response
// retrofit
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

data class ApiRes<T>(
    val code: Int,
    val msg: String,
    val data: T
)

data class LoginReq(
    val studentId: String,
    val password: String
)
data class LoginData(
    val token: String
)

data class CurrentSemesterCoursesData(
    val items: List<Course>,
    val count: Int
)

//class AuthInterceptor(private val context: Context): Interceptor {
class AuthInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            TokenPrefs.getTokenFlow(context).first()
        }
        val request = chain.request().newBuilder()
            .apply {
                if (token != "") {
                    header("Authorization", token)
                }
            }
            .build()
        return chain.proceed(request)
    }
}

interface ApiServer {
    @POST("user/login")
    suspend fun login(@Body loginReq: LoginReq): ApiRes<LoginData>

    @GET("course/{week}")
    suspend fun getCurrentSemesterCourses(
        @Path("week") week: String): ApiRes<CurrentSemesterCoursesData>
}

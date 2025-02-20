package com.example.kdassistant

import com.example.kdassistant.server.ApiServer
import com.example.kdassistant.server.RetrofitInstance
import org.junit.Test

import org.junit.Assert.*

import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class ServerApiTest() {
    private val apiServer = mock(ApiServer::class.java)

    @Test
    fun login_success() {
        RetrofitInstance.api.login("2208020322", "@SunXinYang0306")
        apiServer.login("2208020322", "@SunXinYang0306")
    }
}
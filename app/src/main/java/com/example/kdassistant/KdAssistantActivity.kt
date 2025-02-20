package com.example.kdassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.kdassistant.ui.homepage.HomepageScreen
import com.example.kdassistant.ui.login.LoginContent
import com.example.kdassistant.ui.theme.KdAssistantTheme
import dagger.hilt.android.AndroidEntryPoint

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class KdAssistantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val context: Context = this
//        val ds = context.dataStore
        enableEdgeToEdge()
        setContent {
            KdAssistantTheme {
                HomepageScreen()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Row(modifier = Modifier.padding(innerPadding)) {
//                        LoginContent()
//                    }
//                }
            }
        }
    }
}

//@Composable
//fun LoginButton(modifier: Modifier = Modifier) {
//    var resStr by remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//    Column(modifier = modifier) {
//        Button(onClick = {
//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val res = RetrofitInstance.api.login(LoginReq("2208020322", "@SunXinYang0306"))
//                    resStr = res.data.token
//                    withContext(Dispatchers.Main) {
//                        Log.i("main", "Res: $res")
//                    }
//                    val t = TokenPrefs(context.dataStore)
//                    t.saveToken(res.data.token)
//
//                } catch (e: Exception) {
//                    Log.e("main", "${e.message}")
//                    resStr = "${e.message}"
//                }
//            }
//        }) {
//            Text("Login")
//        }
//        Text(resStr)
//    }
//}
//
//@Composable
//fun TeButton(modifier: Modifier = Modifier) {
//    var resStr by remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//    Column(modifier = modifier) {
//        Button(onClick = {
//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    val t = TokenPrefs(context.dataStore)
//                    val token = t.tokenFlow.first()
//                    val res = RetrofitInstance.api.getCurrentSemesterCourses("14", token)
//                    resStr = "$res"
//                    withContext(Dispatchers.Main) {
//                        Log.i("main", "Res: $res")
//                    }
//
//                } catch (e: Exception) {
//                    Log.e("main", "${e.message}")
//                    resStr = "${e.message}"
//                }
//            }
//        }) {
//            Text("Server Api Login")
//        }
//        Text(resStr)
//    }
//}

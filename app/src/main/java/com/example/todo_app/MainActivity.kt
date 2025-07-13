package com.example.todo_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo_app.ui.theme.TodoappTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //basic connection usage with no HTTP/S casting
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://dummyjson.com/todos")
                val connection = url.openConnection()

                val inputStream = connection.getInputStream()
                val response = inputStream.bufferedReader().use { it.readText() }
                Log.d("API_RESPONSE", response)

            } catch (e: Exception) {
                Log.e("API_EXCEPTION", e.message ?: "Unknown Error")
            }
        }

        // using casting as HttpsURLConnection
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val url = URL("https://dummyjson.com/todos")
//                val connection = url.openConnection() as HttpsURLConnection
//                connection.requestMethod = "GET"
//
//                val responseCode = connection.responseCode
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//                    val response = connection.inputStream.bufferedReader().use { it.readText() }
//                    // Log on main thread
//                    withContext(Dispatchers.Main) {
//                        Log.d("API_RESPONSE", response)
//                        // You can update UI here
//                    }
//                } else {
//                    Log.e("API_ERROR", "HTTP error code: $responseCode")
//                }
//
//                connection.disconnect()
//            } catch(e: Exception) {
//                Log.e("API_EXCEPTION", e.message?: "Unknown Error")
//            }
//        }

        enableEdgeToEdge()
        setContent {
            TodoappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoappTheme {
        Greeting("Android")
    }
}
package com.jidouauto.testapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.jidouauto.testapp.ui.theme.TestAppTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(name: String) {

    Column {
        Text(text = "column")
        Text(text = "Hello $name!")
        Image(bitmap = ImageBitmap(50, 50), contentDescription = "这是一张图片")
        Row {
            PaddingValues(Dp(50f))
            Text(text = "row")
            Text(text = "Hello $name!")
        }
    }
    CoroutineScope(Dispatchers.Default).launch {

        println("")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestAppTheme {
        Greeting("Android")
    }
}
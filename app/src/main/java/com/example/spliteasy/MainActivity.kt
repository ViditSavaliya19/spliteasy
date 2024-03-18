package com.example.spliteasy

import GroupScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.spliteasy.ui.screen.NavScreen
import com.example.spliteasy.ui.theme.SpliteasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpliteasyTheme {
                NavScreen()
            }
        }
    }
}

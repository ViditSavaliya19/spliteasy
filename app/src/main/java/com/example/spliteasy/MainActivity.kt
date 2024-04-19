package com.example.spliteasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.spliteasy.ui.screen.NavScreen
import com.example.spliteasy.ui.screen.login.viewmodel.LoginViewModel
import com.example.spliteasy.ui.theme.SpliteasyTheme
import com.example.spliteasy.viewmodel.SplitViewModel
import com.example.spliteasy.viewmodel.SplitViewModelFactory

class MainActivity : ComponentActivity() {

    lateinit var viewModel:SplitViewModel
    val loginViewModel:LoginViewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,SplitViewModelFactory(this))[SplitViewModel::class.java]

        setContent {
            SpliteasyTheme {
                NavScreen(viewModel,loginViewModel)
            }
        }
    }
}

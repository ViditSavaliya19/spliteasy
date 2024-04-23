//package com.example.spliteasy.ui.screen.splash
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.example.spliteasy.R
//import com.example.spliteasy.ui.screen.login.viewmodel.LoginViewModel
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
//@Composable
//fun SplashScreen(loginViewModel: LoginViewModel, navController: NavHostController) {
//
//    rememberCoroutineScope().launch {
//        delay(3000)
//        if(loginViewModel.isLogin)
//        {
//            navController.navigate("group")
//        }
//        else
//        {
//            navController.navigate("login")
//        }
//
//    }
//
//    Scaffold {
//        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_background),
//                contentDescription = "",
//                Modifier
//                    .clip(RoundedCornerShape(10.dp))
//                    .height(100.dp)
//                    .width(100.dp),
//                contentScale = ContentScale.Crop
//            )
//        }
//    }
//}
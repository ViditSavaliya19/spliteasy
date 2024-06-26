//package com.example.spliteasy.ui.screen.login.view
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.SideEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.example.spliteasy.R
//import com.example.spliteasy.ui.componet.CustomButton
//import com.example.spliteasy.ui.screen.login.viewmodel.LoginViewModel
//import kotlinx.coroutines.async
//import kotlinx.coroutines.launch
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun LoginScreen(viewModel: LoginViewModel, navController: NavHostController) {
//
//    var coroutineScope = rememberCoroutineScope()
//    var context:Context
//    var txtEmail = remember {
//        mutableStateOf("")
//    }
//    var txtPassword = remember {
//        mutableStateOf("")
//    }
//
//
//    Scaffold() {
//        context =LocalContext.current
//        Column(
//            modifier = Modifier
//                .padding(10.dp)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_background),
//                contentDescription = "",
//                Modifier
//                    .clip(RoundedCornerShape(10.dp))
//                    .height(100.dp)
//                    .width(100.dp),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Text("Login with Split Easy", style = TextStyle(fontSize = 25.sp))
//            Spacer(modifier = Modifier.height(20.dp))
//            OutlinedTextField(
//                value = txtEmail.value,
//                onValueChange = { txtEmail.value = it },
//                label = { Text("Email") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            OutlinedTextField(
//                value = txtPassword.value,
//                onValueChange = { txtPassword.value = it },
//                label = { Text("Password") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(10.dp))
//            CustomButton(s = "Login") {
//                 coroutineScope.launch { viewModel.signIn(txtEmail.value, txtPassword.value)
//
//                }
//            }
//
//            TextButton(onClick = {
//                navController.navigate("signup")
//            }) {
//                Text("Create new account ? Sign Up")
//            }
//        }
//    }
//}
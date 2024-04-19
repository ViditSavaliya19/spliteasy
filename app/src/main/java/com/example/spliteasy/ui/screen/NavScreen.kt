package com.example.spliteasy.ui.screen

import DashBoardScreen
import GroupScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spliteasy.ui.screen.group.BottomScreen
import com.example.spliteasy.ui.screen.login.view.LoginScreen
import com.example.spliteasy.ui.screen.login.view.SignUpScreen
import com.example.spliteasy.ui.screen.login.viewmodel.LoginViewModel
import com.example.spliteasy.ui.screen.splash.SplashScreen
import com.example.spliteasy.viewmodel.SplitViewModel


@Composable
fun NavScreen(viewModel: SplitViewModel, loginViewModel: LoginViewModel) {
   var navController = rememberNavController()
   NavHost(navController = navController, startDestination = "splash")
   {
      composable("splash")
      {
         SplashScreen(loginViewModel,navController)
      }
      composable("login")
      {
         LoginScreen(loginViewModel,navController)
      }
      composable("signup")
      {
         SignUpScreen(loginViewModel,navController)
      }
      composable("group")
      {
            GroupScreen(navController,viewModel)
      }
      composable("account")
      {
         AccountScreen(navController,viewModel)
      }
      composable("profile")
      {
         ProfileScreen(navController,viewModel)
      }
      composable("newTrip")
      {
         NewTripScreen(navController,viewModel)
      }
      composable("contact")
      {
         ContactScreen(navController,viewModel)
      }
      composable("dashboard")
      {
         DashBoardScreen(navController,viewModel)
      }
      composable("bottom")
      {
         BottomScreen(navController,viewModel)
      }
      composable("expense")
      {
         ExpenseScreen(navController,viewModel)
      }
   }
}
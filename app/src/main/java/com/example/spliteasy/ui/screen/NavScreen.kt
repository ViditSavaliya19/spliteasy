package com.example.spliteasy.ui.screen

import DashBoardScreen
import GroupScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun NavScreen() {
   var navController = rememberNavController()
   NavHost(navController = navController, startDestination = "bottom")
   {
      composable("group")
      {
            GroupScreen(navController)
      }
      composable("account")
      {
         AccountScreen(navController)
      }
      composable("profile")
      {
         ProfileScreen(navController)
      }
      composable("newTrip")
      {
         NewTripScreen(navController)
      }
      composable("contact")
      {
         ContactScreen(navController)
      }
      composable("dashboard")
      {
         DashBoardScreen(navController)
      }
      composable("bottom")
      {
         BottomScreen(navController)
      }
   }
}
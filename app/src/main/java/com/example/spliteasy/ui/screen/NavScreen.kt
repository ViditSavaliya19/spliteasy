package com.example.spliteasy.ui.screen

import DashBoardScreen
import GroupScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spliteasy.ui.screen.group.BottomScreen
import com.example.spliteasy.viewmodel.SplitViewModel


@Composable
fun NavScreen(viewModel: SplitViewModel) {
   var navController = rememberNavController()
   NavHost(navController = navController, startDestination = "group")
   {
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
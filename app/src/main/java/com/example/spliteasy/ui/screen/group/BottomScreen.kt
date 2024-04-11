package com.example.spliteasy.ui.screen.group

import DashBoardScreen
import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.spliteasy.ui.theme.Purple40
import com.example.spliteasy.ui.theme.PurpleGrey40
import com.example.spliteasy.viewmodel.SplitViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomScreen(navController: NavHostController, viewModel: SplitViewModel) {
    val screens = listOf("Report", "Dashboard", "Settlement")
    var selectedScreen by remember { mutableStateOf(screens[1]) }

    Scaffold(bottomBar = {
        NavigationBar {
            screens.forEach { screen ->
                NavigationBarItem(
                    icon = { Icon(getIconForScreen(screen), contentDescription = screen) },
                    label = { Text(screen) },
                    alwaysShowLabel = false,
                    selected = screen == selectedScreen,
                    onClick = { selectedScreen = screen },
                    interactionSource = MutableInteractionSource(),
                    colors = NavigationBarItemDefaults.colors(
                        unselectedTextColor = PurpleGrey40, selectedTextColor = Purple40
                    ),
                )
            }
        }
    }, content = {

        when (selectedScreen) {
            "Report" -> ReportScreen(navController = navController,viewModel)
            "Dashboard" -> DashBoardScreen(navController = navController,viewModel)
            "Settlement" -> SettlementScreen(navController = navController,viewModel)
            else -> Icons.Default.Home
        }
    })
}

@Composable
fun getIconForScreen(screen: String): ImageVector {
    return when (screen) {
        "Report" -> Icons.Filled.Info
        "Dashboard" -> Icons.Filled.Home
        "Settlement" -> Icons.Filled.Done
        else -> Icons.Default.Home
    }
}
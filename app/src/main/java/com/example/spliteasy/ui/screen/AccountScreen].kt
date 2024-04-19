package com.example.spliteasy.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.spliteasy.ui.componet.TopBar
import com.example.spliteasy.ui.theme.Gray
import com.example.spliteasy.ui.theme.Purple40
import com.example.spliteasy.ui.theme.PurpleGrey40
import com.example.spliteasy.ui.theme.White
import com.example.spliteasy.viewmodel.SplitViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountScreen(navController: NavHostController, viewModel: SplitViewModel) {

    Scaffold {
        Column {
            TopBar(navController, Icons.Filled.ArrowBack, "Account"){

            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier.padding(10.dp)) {
                Box(modifier = Modifier.clip(shape = RoundedCornerShape(20f))) {
                    Box(
                        Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .background(color = PurpleGrey40)
                            .clickable {
                                navController.navigate("profile")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(80.dp)
                                    .width(80.dp)
                                    .background(color = Gray),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Outlined.Person, contentDescription = null, tint = White)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("Name", style = TextStyle(color = Purple40, fontSize = 18.sp))
                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.clip(shape = RoundedCornerShape(20f))) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = PurpleGrey40),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Privacy Policy",
                                style = TextStyle(color = Purple40, fontSize = 18.sp)
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            Icon(Icons.Outlined.Info, contentDescription = null, tint = Purple40)

                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.clip(shape = RoundedCornerShape(20f))) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = PurpleGrey40),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Rate us", style = TextStyle(color = Purple40, fontSize = 18.sp))
                            Spacer(modifier = Modifier.height(10.dp))

                            Icon(Icons.Outlined.Star, contentDescription = null, tint = Purple40)

                        }

                    }
                }
            }
        }
    }

}
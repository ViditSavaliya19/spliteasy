package com.example.spliteasy.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.spliteasy.ui.componet.CustomButton
import com.example.spliteasy.ui.componet.TopBar
import com.example.spliteasy.ui.theme.Gray
import com.example.spliteasy.ui.theme.Purple40
import com.example.spliteasy.ui.theme.PurpleGrey40

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactScreen(navController: NavHostController) {

    var txtName = remember {
        mutableStateOf("")
    }

    Scaffold {
        Column {
            TopBar(
                navController = navController,
                icon = Icons.Filled.ArrowBack,
                s = "Add a new contact"
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Row {
                    Text(
                        "NAME",
                        style = TextStyle(color = Gray, fontSize = 15.sp)
                    )
                    Text(
                        "*",
                        style = TextStyle(color = Color.Red, fontSize = 15.sp)
                    )
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

                        BasicTextField(
                            value = txtName.value,
                            onValueChange = {
                                txtName.value = it
                            },
                            textStyle = TextStyle(color = Purple40, fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth()

                        )

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "MOBILE",
                    style = TextStyle(color = Gray, fontSize = 15.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.clip(shape = RoundedCornerShape(20f))) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = PurpleGrey40),
                        contentAlignment = Alignment.Center
                    ) {

                        BasicTextField(
                            value = txtName.value,
                            onValueChange = {
                                txtName.value = it
                            },
                            textStyle = TextStyle(color = Purple40, fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth()

                        )

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "EMAIL",
                    style = TextStyle(color = Gray, fontSize = 15.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.clip(shape = RoundedCornerShape(20f))) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = PurpleGrey40),
                        contentAlignment = Alignment.Center
                    ) {

                        BasicTextField(
                            value = txtName.value,
                            onValueChange = {
                                txtName.value = it
                            },
                            textStyle = TextStyle(color = Purple40, fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .fillMaxWidth()

                        )

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                CustomButton("Submit")
            }
        }
    }

}

package com.example.spliteasy.ui.screen

import NameBox
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.spliteasy.ui.componet.CustomButton
import com.example.spliteasy.ui.componet.CustomDivider
import com.example.spliteasy.ui.componet.TopBar
import com.example.spliteasy.ui.theme.Gray
import com.example.spliteasy.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewTripScreen(navController: NavHostController) {
    var txtTripName = remember {
        mutableStateOf("")
    }
    Scaffold {
        Column {
            TopBar(navController, Icons.Filled.ArrowBack, "Account")
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier.padding(10.dp)) {
                TextField(
                    value = txtTripName.value,
                    placeholder = {
                        Text(
                            "Ex. Trip Name",
                            style = TextStyle(
                                fontSize = 30.sp,
                                color = Gray,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    },
                    onValueChange = {
                        txtTripName.value = it
                    },
                    textStyle = TextStyle(
                        fontSize = 30.sp,
                        color = Purple40,
                        fontWeight = FontWeight.Bold
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),

                    )
                Spacer(modifier = Modifier.height(10.dp))
                CustomDivider()
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(content = {
                    items(count = 3) {

                        NameBox(50, 50)

                    }
                })
                Spacer(modifier = Modifier.height(10.dp))
                CustomDivider()
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.clickable {
                    navController.navigate("contact")

                }) {
                    CustomButton(s = "Add New Contact", fs = 15)
                }
                Spacer(modifier = Modifier.weight(1.0f))
                CustomButton(s = "Submit")
            }

        }
    }

}
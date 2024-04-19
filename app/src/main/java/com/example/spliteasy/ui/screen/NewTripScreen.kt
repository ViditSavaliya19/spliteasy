package com.example.spliteasy.ui.screen

import NameBox
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.spliteasy.db.model.Trip
import com.example.spliteasy.db.model.TripUser
import com.example.spliteasy.db.model.User
import com.example.spliteasy.ui.componet.CustomButton
import com.example.spliteasy.ui.componet.CustomDivider
import com.example.spliteasy.ui.componet.TopBar
import com.example.spliteasy.ui.theme.Gray
import com.example.spliteasy.ui.theme.Purple40
import com.example.spliteasy.ui.theme.PurpleGrey40
import com.example.spliteasy.viewmodel.SplitViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewTripScreen(navController: NavHostController, viewModel: SplitViewModel) {
    var txtTripName = remember {
        mutableStateOf("")
    }

    var user = viewModel.users.observeAsState()
    val selectedList = remember { user.value!!.map { false }.toMutableStateList() }
    var selectedUserList = remember { mutableStateListOf<User>() }
    var contextCoroutine = rememberCoroutineScope()


    Scaffold {
        Column {
            TopBar(navController, Icons.Filled.ArrowBack, "Account"){}
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier.padding(10.dp)) {
                TextField(
                    value = txtTripName.value,
                    placeholder = {
                        Text(
                            "Ex. Trip Name",
                            style = TextStyle(
                                fontSize = 30.sp, color = Gray, fontWeight = FontWeight.Bold
                            ),
                        )
                    },
                    onValueChange = {
                        txtTripName.value = it
                    },
                    textStyle = TextStyle(
                        fontSize = 30.sp, color = Purple40, fontWeight = FontWeight.Bold
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
                    itemsIndexed(selectedUserList) { index, it ->

                        NameBox(50, 50, it.name.substring(0, 1))

                    }
                })
                Spacer(modifier = Modifier.height(10.dp))
                CustomDivider()
                Spacer(modifier = Modifier.height(10.dp))

                CustomButton(s = "Add New Contact", fs = 15) {
                    navController.navigate("contact")

                }

                Spacer(modifier = Modifier.height(10.dp))


                LazyColumn(Modifier.weight(1f), content = {
                    items(count = selectedList.size) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(PurpleGrey40)

                        ) {

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "${user.value!!.get(it).name}",
                                    style = TextStyle(fontSize = 18.sp, color = Purple40)
                                )
                                Checkbox(
                                    checked = selectedList[it],
                                    onCheckedChange = { value ->
                                        selectedList[it] = !selectedList[it]
                                        selectedUserList.clear()
                                        selectedUserList.addAll(user.value!!.filterIndexed { index, _ -> selectedList[index] })
                                    },
                                )
                            }

                        }
                    }
                })

                CustomButton(s = "Submit") {

                    contextCoroutine.launch {
                        var tripId = viewModel.repo.addTrip(Trip(name = txtTripName.value))
                        var tripList = selectedUserList.map {
                            TripUser(
                                trip_id = tripId.toInt(),
                                user_id = it.user_id
                            )
                        }
                        viewModel.repo.addTripUsers(tripList)
                        viewModel.getTrip()
                        navController.popBackStack()
                    }

                }
            }

        }
    }

}
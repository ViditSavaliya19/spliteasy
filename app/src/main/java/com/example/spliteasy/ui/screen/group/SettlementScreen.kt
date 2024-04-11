package com.example.spliteasy.ui.screen.group

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
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
import com.example.spliteasy.ui.componet.TopBar
import com.example.spliteasy.ui.theme.Gray
import com.example.spliteasy.ui.theme.Purple40
import com.example.spliteasy.viewmodel.SplitViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettlementScreen(navController: NavHostController, viewModel: SplitViewModel) {

    var list = remember {
            viewModel.finalSettlementList.value
        }

    Scaffold(

    ) {
        Scaffold {
            TopBar(navController = navController, icon = Icons.Filled.ArrowBack, s = "Settlement")
            Column(
                modifier = Modifier.padding(
                    top = 70.dp, start = 10.dp, end = 10.dp, bottom = 10.dp
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("SETTLE UP", style = TextStyle(color = Gray, fontSize = 15.sp))
                    Spacer(modifier = Modifier.weight(1f))
                    Text("All", style = TextStyle(color = Purple40, fontSize = 15.sp))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                }
                LazyColumn(content = {
                    list?.let { it1 ->
                        items(count = it1.count()) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .border(
                                    1.dp,
                                    Purple40,
                                    shape = RoundedCornerShape(8.dp),
                                ), content = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp, vertical = 5.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "${it1[it].from} pay ₹${it1[it].amount} to ${it1[it].to}",
                                        style = TextStyle(color = Purple40, fontSize = 18.sp)
                                    )
                                    Checkbox(checked = true, onCheckedChange = {})
                                }
                            })
                        }
                    }
                })
            }
        }
    }
}
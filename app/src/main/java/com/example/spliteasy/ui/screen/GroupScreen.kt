import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.spliteasy.viewmodel.SplitViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupScreen(navController: NavHostController, viewModel: SplitViewModel) {
    val trips = viewModel.trip.observeAsState(null)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var coroutineContext = rememberCoroutineScope()

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        Drawer(navController)
    }) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate("newTrip")
                }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            },
        ) {
            Column {
                TopBar(navController, Icons.Filled.Menu, "spliteasy", "account") {
                    coroutineContext.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
                Spacer(
                    Modifier.height(10.dp)
                )

                LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    content = {
                        trips.value?.let { it1 ->
                            items(it1.size)
                            {
                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    Modifier
                                        .clip(shape = RoundedCornerShape(20f))
                                        .clickable {
                                            coroutineContext.launch {
                                                viewModel.selectedTrip = it1[it]
                                                viewModel.getTripWithUsers()
                                                viewModel.getExpense()
                                                //                                            viewModel.getExpenseWithUser()
                                                navController.navigate("bottom")
                                            }
                                        }
                                ) {
                                    Box(
                                        Modifier
                                            .background(color = PurpleGrey40)
                                            .height(100.dp)
                                            .fillMaxWidth()

                                    ) {
                                        Column(
                                            Modifier
                                                .padding(10.dp)
                                                .fillMaxHeight(),
                                            verticalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            Row(
                                                Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                            ) {

                                                Text(
                                                    "${trips.value!![it].name}",
                                                    style = TextStyle(
                                                        fontSize = 18.sp,
                                                        color = Purple40
                                                    )
                                                )
                                                Text("16 Mar 2024", style = TextStyle(color = Gray))

                                            }
                                            Divider(color = Gray, thickness = 0.5.dp)
                                            Row(
                                                Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Row {
                                                    NameBox(25, 25, "V")
                                                    NameBox(25, 25, "V")
                                                    NameBox(25, 25, "V")
                                                }
                                                Icon(
                                                    Icons.Filled.ArrowForward,
                                                    tint = Gray,
                                                    contentDescription = null,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    },
                )

            }
        }
    }

}

@Composable
fun NameBox(h: Int, w: Int, lable: String) {
    Box(Modifier.clip(shape = RoundedCornerShape(30f))) {
        Box(
            modifier = Modifier
                .height(h.dp)
                .width(w.dp)
                .background(color = Purple40.copy(alpha = 0.5f))
                .border(width = 1.dp, color = PurpleGrey40),
            contentAlignment = Alignment.Center
        ) {
            Text("$lable", style = TextStyle(color = PurpleGrey40))

        }
    }
}

@Composable
fun Drawer(navController: NavHostController) {

    ModalDrawerSheet {

        Text("Drawer title", modifier = Modifier.padding(16.dp))
//        HorizontalDivider()
//        NavigationDrawerItem(
//            label = { Text(text = "Logout") },
//            selected = false,
//            onClick = {
//                FireAuthHelper.helper.signOut()
//                navController.navigate("login")
//            }
//        )

    }

}
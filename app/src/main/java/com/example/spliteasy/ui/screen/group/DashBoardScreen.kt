import android.annotation.SuppressLint
import android.widget.Space
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.spliteasy.ui.componet.TopBar
import com.example.spliteasy.ui.theme.Gray
import com.example.spliteasy.ui.theme.Purple40
import com.example.spliteasy.viewmodel.SplitViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashBoardScreen(navController: NavHostController, viewModel: SplitViewModel) {

    var expenses = viewModel.expenses.observeAsState()
    var coroutineContext = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.selectedExpense=null
                navController.navigate("expense")
            }, content = { Icon(Icons.Filled.Add, contentDescription = null) })
        },
        modifier = Modifier.height((LocalConfiguration.current.screenHeightDp - 90).dp)
    ) {
        TopBar(navController = navController, icon = Icons.Filled.ArrowBack, s = "Dashboard"){}
        Column(
            modifier = Modifier.padding(
                top = 70.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("SPLIT", style = TextStyle(color = Gray, fontSize = 15.sp))
                Spacer(modifier = Modifier.weight(1f))
                Text("All", style = TextStyle(color = Purple40, fontSize = 15.sp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
            }
            LazyColumn(content = {
                expenses.value?.let { it1 ->
                    items(count = it1.size) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .border(
                                    1.dp,
                                    Purple40,
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .clickable {
                                    coroutineContext.launch {
                                        viewModel.selectedExpense = expenses.value!![it]
                                        viewModel.selectedPaidBy = viewModel.payByUserList.value!![it]
                                        viewModel.getExpenseTransaction()
                                        navController.navigate("expense")

                                    }
                                },
                            content = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            "${viewModel.payByUserList.value!![it].name} paid for ${expenses.value!![it].remark}",
                                            style = TextStyle(color = Purple40, fontSize = 18.sp)
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            "25-Mar-2024",
                                            style = TextStyle(color = Purple40, fontSize = 12.sp)
                                        )

                                    }
                                    Text(
                                        "₹ ${expenses.value!![it].totalAmount}",
                                        style = TextStyle(
                                            color = Purple40,
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )

                                }
                            }
                        )
                    }
                }
            })

        }
    }
}
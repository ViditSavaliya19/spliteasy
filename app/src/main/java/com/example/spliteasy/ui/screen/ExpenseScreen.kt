package com.example.spliteasy.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.map
import androidx.navigation.NavHostController
import com.example.spliteasy.db.model.ExpenseData
import com.example.spliteasy.db.model.TransactionData
import com.example.spliteasy.ui.componet.CustomButton
import com.example.spliteasy.ui.componet.TopBar
import com.example.spliteasy.ui.model.ExpenseModel
import com.example.spliteasy.ui.theme.Gray
import com.example.spliteasy.ui.theme.Purple40
import com.example.spliteasy.ui.theme.PurpleGrey40
import com.example.spliteasy.viewmodel.SplitViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExpenseScreen(navController: NavHostController, viewModel: SplitViewModel) {

    val amountList = arrayListOf<String>("50", "100", "200", "500")
    val categoryList = arrayListOf<String>("food", "drink", "medicine", "clothes", "toys")
    val expanded = remember { mutableStateOf(false) }

    val selectedOptionText = remember {
        mutableStateOf(
            if (viewModel.selectedExpense != null) {
                viewModel.selectedPaidBy!!.name
            } else {
                viewModel.tripUser.value?.get(0)!!.name ?: "Name"
            }
        )
    }
    val txtAmount = remember {
        mutableStateOf(
            if (viewModel.selectedExpense != null) {
                "${viewModel.selectedExpense!!.totalAmount}"
            } else {
                "0.0"
            }
        )
    }
    val txtCategory = remember {
        mutableStateOf(
            if (viewModel.selectedExpense != null) {
                viewModel.selectedExpense!!.remark
            } else {
                ""
            }
        )
    }

    var payByUserId = viewModel.tripUser.value?.get(0)!!.user_id


    val coroutineContext = rememberCoroutineScope()

    var selectSplitUserList = remember {
        if (viewModel.selectedExpense != null) {
            viewModel.tripUser.value!!.mapIndexed { index, user ->
                ExpenseModel(
                    user = user,
                    selected = viewModel.transactionList.value!![index].amount != 0.0,
                    amount = viewModel.transactionList.value!![index].amount
                )
            }.toMutableStateList()
        } else {
            viewModel.tripUser.value!!.map {
                ExpenseModel(
                    user = it, selected = true, amount = 0.0
                )
            }.toMutableStateList()
        }
    }

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            TopBar(
                navController = navController, icon = Icons.Default.ArrowBack, s = "Add Expense"
            ) {}

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                Text("EXPENSE AMOUNT", style = TextStyle(color = Gray))
                Spacer(modifier = Modifier.height(10.dp))

                TextField(value = txtAmount.value,
                    onValueChange = {
                        txtAmount.value = it

                        //Split Calculator
                        if (txtAmount.value.isNotEmpty()) {
                            var data = viewModel.splitCalculator(
                                selectSplitUserList, txtAmount.value.toDouble()
                            )
                            selectSplitUserList.clear()
                            selectSplitUserList.addAll(data)
                        }
                    },
                    placeholder = {
                        Text(text = "$200.00", style = TextStyle(color = Gray, fontSize = 20.sp))
                    },
                    textStyle = TextStyle(
                        color = Purple40, fontWeight = FontWeight.Bold, fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))

                LazyRow(
                    content = {
                        items(count = amountList.size) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .width(60.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .border(
                                    1.dp,
                                    Purple40,
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .clickable {
                                    txtAmount.value = amountList[it]
                                    //Split Calculator

                                    var data = viewModel.splitCalculator(
                                        selectSplitUserList, txtAmount.value.toDouble()
                                    )
                                    selectSplitUserList.clear()
                                    selectSplitUserList.addAll(data)

                                }

                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "₹ ${amountList[it]}",
                                    color = Purple40,
                                )
                            }
                        }
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = PurpleGrey40)
                ) {
                    TextField(
                        value = txtCategory.value,
                        onValueChange = {
                            txtCategory.value = it
                        },
                        placeholder = {
                            Text(
                                text = "Add Remark",
                                style = TextStyle(color = Purple40, fontSize = 20.sp)
                            )
                        },
                        textStyle = TextStyle(
                            color = Purple40, fontWeight = FontWeight.Bold, fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),

                        )
                }

                Spacer(modifier = Modifier.height(5.dp))
                LazyRow(
                    content = {
                        items(count = categoryList.size) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .border(
                                    1.dp,
                                    Purple40,
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .clickable {
                                    txtCategory.value = categoryList[it]
                                }

                            ) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(4.dp),
                                    text = "${categoryList[it]}",
                                    color = Purple40,
                                )
                            }
                        }
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))

                Text("PAYER", style = TextStyle(color = Gray, fontSize = 15.sp))

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = PurpleGrey40)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        expanded.value = !expanded.value
                    }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "${selectedOptionText.value}",
                            style = TextStyle(color = Purple40, fontSize = 20.sp),

                            )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                    DropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                    ) {
                        viewModel.tripUser.value!!.forEach { label ->
                            DropdownMenuItem(
                                text = { Text(text = label.name) },
                                onClick = {
                                    expanded.value = !expanded.value
                                    selectedOptionText.value = label.name
                                    payByUserId = label.user_id
                                },
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("SPLIT", style = TextStyle(color = Gray, fontSize = 15.sp))
                    Spacer(modifier = Modifier.weight(1f))
                    Checkbox(checked = true, onCheckedChange = {})
                    Text("All", style = TextStyle(color = Gray, fontSize = 15.sp))
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = PurpleGrey40)
                ) {
                    Column(content = {
//                    items(count = selectSplitUserList.size) {
//
//                    }

                        selectSplitUserList.mapIndexed { it, data ->
                            Row(
                                modifier = Modifier.padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(checked = selectSplitUserList[it].selected,
                                    onCheckedChange = { value ->
                                        selectSplitUserList[it] = ExpenseModel(
                                            user = selectSplitUserList[it].user,
                                            selected = !selectSplitUserList[it].selected,
                                            amount = selectSplitUserList[it].amount
                                        )

                                        //Split Calculator
                                        val data = viewModel.splitCalculator(
                                            selectSplitUserList, txtAmount.value.toDouble()
                                        )
                                        selectSplitUserList.clear()
                                        selectSplitUserList.addAll(data)

                                    })
                                Text(
                                    "${viewModel.tripUser.value!![it].name}",
                                    style = TextStyle(color = Purple40, fontSize = 15.sp)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Box(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(100.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.White)
                                        .border(
                                            1.dp,
                                            Purple40,
                                            shape = RoundedCornerShape(8.dp),
                                        )
                                ) {
                                    TextField(
                                        value = "${selectSplitUserList[it].amount}",
                                        onValueChange = { value ->
                                            selectSplitUserList[it].amount = value.toDouble()
                                        },
                                        placeholder = {
                                            Text(
                                                text = "0.0", style = TextStyle(
                                                    color = Purple40, fontSize = 20.sp
                                                )
                                            )
                                        },
                                        textStyle = TextStyle(
                                            color = Purple40,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp
                                        ),
                                        modifier = Modifier.width(100.dp),
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            disabledIndicatorColor = Color.Transparent
                                        ),
                                    )
                                }
                            }
                        }
                    })
                }
                Spacer(modifier = Modifier.height(10.dp))

                CustomButton(s = "Submit") {
                    coroutineContext.launch {


                        if (viewModel.selectedExpense != null) {
                            //Update Data
                            viewModel.repo.updateExpense(
                                ExpenseData(
                                    expense_id = viewModel.selectedExpense!!.expense_id,
                                    totalAmount = txtAmount.value.toInt(),
                                    paidByUserId = payByUserId,
                                    remark = txtCategory.value,
                                    group_id = viewModel.selectedTrip!!.trip_id!!
                                )
                            )

                            var transactionList = selectSplitUserList.mapIndexed { index, it ->
                                TransactionData(
                                    transaction_id = viewModel.transactionList.value!![index].transaction_id,
                                    group_id = viewModel.selectedTrip!!.trip_id,
                                    expense_id = viewModel.selectedExpense!!.expense_id,
                                    user_id = it.user.user_id,
                                    amount = it.amount
                                )
                            }
                            viewModel.repo.updateTransaction(transactionList)

                        } else {

                            //InsertData
                            var expense_id = viewModel.repo.addExpense(
                                ExpenseData(
                                    totalAmount = txtAmount.value.toInt(),
                                    paidByUserId = payByUserId,
                                    remark = txtCategory.value,
                                    group_id = viewModel.selectedTrip!!.trip_id!!
                                )
                            )

                            var transactionList = selectSplitUserList.map {
                                TransactionData(
                                    group_id = viewModel.selectedTrip!!.trip_id!!,
                                    expense_id = expense_id.toInt(),
                                    user_id = it.user.user_id,
                                    amount = it.amount
                                )
                            }
                            viewModel.repo.addTransaction(transactionList)

                        }

                        viewModel.getTripWithUsers()
                        viewModel.getExpense()
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}
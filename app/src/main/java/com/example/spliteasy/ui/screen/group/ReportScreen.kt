package com.example.spliteasy.ui.screen.group

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.spliteasy.ui.componet.TopBar
import com.example.spliteasy.ui.theme.Blue
import com.example.spliteasy.ui.theme.Green
import com.example.spliteasy.ui.theme.Magenta
import com.example.spliteasy.ui.theme.Orange
import com.example.spliteasy.ui.theme.Pink
import com.example.spliteasy.ui.theme.Red
import com.example.spliteasy.ui.theme.Yellow
import com.example.spliteasy.viewmodel.SplitViewModel
import me.bytebeats.views.charts.pie.PieChart
import me.bytebeats.views.charts.pie.PieChartData
import me.bytebeats.views.charts.pie.render.SimpleSliceDrawer


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReportScreen(navController: NavHostController, viewModel: SplitViewModel) {

    var list = remember {
        viewModel.finalSettlementList.value
    }

    var colorList = arrayOf(Red, Green, Yellow, Blue, Pink, Orange, Magenta)

    Scaffold(

    ) {
        Scaffold {
            TopBar(navController = navController, icon = Icons.Filled.ArrowBack, s = "Report"){}
            Box(
                modifier = Modifier
                    .padding(top = 60.dp, start = 10.dp, end = 10.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                PieChart(
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp),
                    pieChartData = PieChartData(
                        slices = list!!.mapIndexed() {index,it ->
                            PieChartData.Slice(
                                it.amount.toFloat(),
                                colorList[index],
                            )
                        }
                    ),
                    sliceDrawer = SimpleSliceDrawer()
                )
            }
        }
    }
}
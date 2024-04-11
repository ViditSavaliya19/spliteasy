package com.example.spliteasy.ui.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spliteasy.ui.theme.Purple40
import com.example.spliteasy.ui.theme.White


@Composable
fun CustomButton( s: String, fs: Int = 18,onClick: () -> Unit) {
    Box(modifier = Modifier
        .clip(shape = RoundedCornerShape(20f)).clickable { onClick() }
       ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Purple40),
            contentAlignment = Alignment.Center
        ) {

            Text(
                "$s",
                style = TextStyle(color = White, fontSize = fs.sp)
            )
        }
    }
}
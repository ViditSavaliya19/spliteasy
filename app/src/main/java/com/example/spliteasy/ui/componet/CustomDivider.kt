package com.example.spliteasy.ui.componet

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.spliteasy.ui.theme.Purple40


@Composable
 fun CustomDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 10.dp), thickness = 1.dp, color = Purple40
    )
}
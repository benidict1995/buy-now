package com.benidict.buy_now.default

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DefaultScreen(name: String) {
    Text(text = name, fontSize = 36.sp, fontWeight = FontWeight.Bold)
}
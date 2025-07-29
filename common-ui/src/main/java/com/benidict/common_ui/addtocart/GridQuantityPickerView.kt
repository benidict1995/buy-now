package com.benidict.common_ui.addtocart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GridQuantityPickerView(quantity: Int = 1,
                           modifier: Modifier,
                           onUpdateQuantity: (Int) -> Unit,
                           onAddToCart: (Int) -> Unit) {
    val newQuantity = remember { mutableIntStateOf(quantity) }

    LaunchedEffect(quantity) {
        newQuantity.intValue = quantity
    }

    Box(
        modifier = modifier.clip(CircleShape).size(30.dp).background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = newQuantity.intValue.toString(), fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}
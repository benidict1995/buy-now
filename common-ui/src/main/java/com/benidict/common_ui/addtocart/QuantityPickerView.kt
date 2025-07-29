package com.benidict.common_ui.addtocart

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.common_ui.icon.CircleMinus
import com.benidict.common_ui.icon.CirclePlus

@Composable
fun QuantityPickerView(
    quantity: Int = 1,
    modifier: Modifier,
    onUpdateQuantity: (Int) -> Unit,
    onAddToCart: (Int) -> Unit
) {
    val newQuantity = remember { mutableIntStateOf(quantity) }

    LaunchedEffect(quantity) {
        newQuantity.intValue = quantity
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onAddToCart(newQuantity.intValue)
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(48.dp)
                .width(200.dp)
        ) {
            Text(
                "Add to cart",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd).padding(end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleMinus {
                if (newQuantity.intValue >= 2) {
                    newQuantity.intValue -= 1
                    onUpdateQuantity(newQuantity.intValue)
                }
            }
            Text(text = newQuantity.intValue.toString(), fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.width(20.dp))
            CirclePlus {
                newQuantity.intValue += 1
                onUpdateQuantity(newQuantity.intValue)
            }
        }
    }
}
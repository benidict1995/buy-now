package com.benidict.common_ui.addtocart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.common_ui.icon.CircleMinus
import com.benidict.common_ui.icon.CirclePlus

@Composable
fun QuantityPickerView(
    modifier: Modifier,
    quantity: Int,
    onClickMinus: (Int) -> Unit,
    onClickPlus: (Int) -> Unit,
    onAddToCart: (Int) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onAddToCart(quantity)
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
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            CircleMinus {
                onClickMinus(quantity)
            }
            Text(text = "1", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            CirclePlus {
                onClickPlus(quantity)
            }
        }
    }
}
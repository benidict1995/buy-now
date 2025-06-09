package com.benidict.common_ui.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.common_ui.R

@Composable
fun SubMenuView(title: String, description: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .background(color = Color.White)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart).padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Image(
            painter = painterResource(R.drawable.baseline_keyboard_arrow_right_24),
            contentDescription = "",
            modifier = Modifier.align(alignment = Alignment.CenterEnd).padding(end = 10.dp)
        )
    }
}
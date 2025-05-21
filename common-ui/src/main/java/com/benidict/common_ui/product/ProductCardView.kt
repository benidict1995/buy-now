package com.benidict.common_ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.benidict.common_ui.R

@Composable
fun ProductCardView(item: String) {
    Column {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_image_24),
                modifier = Modifier.size(200.dp),
                contentDescription = ""
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp))
            Text(text = item)
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(10.dp))
        }
    }
}
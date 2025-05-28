package com.benidict.common_ui.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.benidict.buy_now.category.Category
import com.benidict.common_ui.image.ImageLoader

@Composable
fun CategoryGridView(item: Category, onClick: (Int, String) -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onClick(item.categoryId, item.categoryName)
        }
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageLoader(
                url = item.categoryUrl,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth()
                    .height(60.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            Text(
                text = item.categoryName, fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis, maxLines = 1
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
    }
}
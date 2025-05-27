package com.benidict.common_ui.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.buy_now.product.Product
import com.benidict.common_ui.icon.HotIcon
import com.benidict.common_ui.image.ImageLoader
import com.benidict.common_ui.text.NewTag
import com.benidict.common_utils.transform.convertToPeso

@Composable
fun ProductCardView(item: Product, modifier: Modifier, onClick: (Int) -> Unit) {
    Column(modifier = modifier.clickable {
        onClick(item.productId)
    }) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .height(350.dp)
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.White)
        ) {
            Column {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if(item.isHot) HotIcon()
                    if(item.isNew) NewTag()
                }
                ImageLoader(item.productImageUrl, Modifier.requiredSize(200.dp).padding(top = 20.dp))
            }
            Text(
                text = item.productName,
                maxLines = 1,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Text(
                text = item.price.convertToPeso(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Text(
                text = item.weight,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
        }
    }
}
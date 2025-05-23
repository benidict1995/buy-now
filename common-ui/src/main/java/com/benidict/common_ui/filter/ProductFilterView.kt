package com.benidict.common_ui.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductFilterView(
    items: List<String>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        itemsIndexed(items) { index, item ->
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(40.dp)
                    .padding(end = 16.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(50))
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                text = item,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
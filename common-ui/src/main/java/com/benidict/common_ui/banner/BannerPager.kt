package com.benidict.common_ui.banner

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BannerPager(
    items: List<Color>, // list of image URLs or identifiers
) {
    val listState = rememberLazyListState()

    Column(modifier = Modifier) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(350.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = item)
                ) {

                }
            }
        }
    }

    Spacer(modifier = Modifier.height(12.dp))
    // Dots Indicator
    val visibleItemIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEachIndexed { index, _ ->
            Log.d("makerChecker", "visibleItemIndex:${visibleItemIndex.value}, index:$index")
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(
                        if (index == visibleItemIndex.value) {
                            10.dp
                        } else 8.dp
                    )
                    .background(
                        if (index == visibleItemIndex.value) Color.DarkGray else Color.LightGray,
                        shape = CircleShape
                    )
            )
        }
    }
}
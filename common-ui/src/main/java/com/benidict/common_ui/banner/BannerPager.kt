package com.benidict.common_ui.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.benidict.buy_now.banner.Banner
import com.benidict.common_ui.image.ImageLoader

@Composable
fun BannerPager(
    items: List<Banner>, // list of image URLs or identifiers
) {
    val listState = rememberLazyListState()

    Column(modifier = Modifier) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            itemsIndexed(items) { _, item ->
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(350.dp)
                        .fillMaxHeight()

                ) {
                    ImageLoader(
                        url = item.bannerUrl,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                    )
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
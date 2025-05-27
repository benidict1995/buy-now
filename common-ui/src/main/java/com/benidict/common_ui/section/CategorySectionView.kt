package com.benidict.common_ui.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.benidict.buy_now.category.Category
import com.benidict.common_ui.banner.SmallBannerView

@Composable
fun CategorySectionView(
    items: List<Category>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Categories",
                modifier = Modifier.align(Alignment.CenterStart))
            Text(text = "See all",
                modifier = Modifier.align(Alignment.CenterEnd))
        }
        Spacer(modifier =  Modifier.height(10.dp).fillMaxWidth())
        SmallBannerView(items)
    }
}
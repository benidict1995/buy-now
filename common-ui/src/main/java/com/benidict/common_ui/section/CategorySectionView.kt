package com.benidict.common_ui.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.buy_now.category.Category
import com.benidict.common_ui.banner.SmallBannerView

@Composable
fun CategorySectionView(
    items: List<Category>,
    onNavigateProductByCategory: (Int, String) -> Unit,
    onViewAll: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Categories",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart))
            Text(text = "See all",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.CenterEnd).clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onViewAll()
                })
        }
        Spacer(modifier =  Modifier.height(10.dp).fillMaxWidth())
        SmallBannerView(items, onClick = { categoryId, categoryName ->
            onNavigateProductByCategory(categoryId, categoryName)
        })
    }
}
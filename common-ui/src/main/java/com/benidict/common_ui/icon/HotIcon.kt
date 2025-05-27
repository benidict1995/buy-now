package com.benidict.common_ui.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HotIcon() {
    Icon(
        imageVector = Icons.Filled.Whatshot,
        contentDescription = "Hot icon",
        tint = Color.Red,
        modifier = Modifier
    )
}
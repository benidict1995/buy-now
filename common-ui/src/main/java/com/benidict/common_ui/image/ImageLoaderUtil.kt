package com.benidict.common_ui.image

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageLoader(url: String, modifier: Modifier) {
    AsyncImage(
        model = url,
        modifier = modifier,
        contentDescription = ""
    )
}


package com.benidict.common_ui.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.benidict.common_ui.icon.CircleFavorite

@Composable
fun ImageLoader(url: String, modifier: Modifier) {
    AsyncImage(
        model = url,
        modifier = modifier,
        contentDescription = ""
    )
}


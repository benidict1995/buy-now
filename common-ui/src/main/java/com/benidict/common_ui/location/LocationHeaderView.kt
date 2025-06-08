package com.benidict.common_ui.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LocationHeaderView(locationName: String, onNavigateToProfile: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.size(40.dp).align(Alignment.CenterStart).clickable {
                onNavigateToProfile()
            },
            painter = painterResource(com.benidict.common_ui.R.drawable.baseline_account_circle_24),
            contentDescription = ""
        )
        LocationSelectorView(
            locationName = locationName,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
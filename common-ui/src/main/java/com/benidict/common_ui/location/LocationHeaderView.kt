package com.benidict.common_ui.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun LocationHeaderView(locationName: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(com.benidict.common_ui.R.drawable.baseline_account_circle_24),
            contentDescription = ""
        )
        LocationSelectorView(
            locationName = locationName
        )
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(com.benidict.common_ui.R.drawable.baseline_notifications_none_24),
            contentDescription = ""
        )
    }
}
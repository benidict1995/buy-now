package com.benidict.common_ui.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.benidict.common_ui.R

@Composable
fun LocationSelectorView(locationName: String, modifier: Modifier) {
    Row(modifier = modifier) {
       Text(text = locationName)
       Spacer(modifier = Modifier.width(10.dp))
       Image(
           painter =  painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
           contentDescription = ""
       )
    }
}
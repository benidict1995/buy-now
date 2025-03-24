package com.benidict.common_ui.button

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CommonButtonView(buttonLabel: String = "Okay", modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onClick() }) {
        Text(text = buttonLabel)
    }
}

@Composable
fun CommonOutlinedButtonView(buttonLabel: String = "Okay", modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton (
        modifier = modifier,
        onClick = { onClick() }) {
        Text(text = buttonLabel)
    }
}
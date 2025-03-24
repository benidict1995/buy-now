package com.benidict.common_ui.field

import android.annotation.SuppressLint
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun CommonTextFieldView(text: String = "", label: String, @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
                        textStyle: TextStyle = TextStyle(), onTextChanged: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = { value ->
            onTextChanged(value)
        },
        label = { Text(label) },
        maxLines = 1,
        textStyle = textStyle,
        modifier = modifier
    )
}

@Composable
fun CommonOutlinedTextFieldView(text: String = "", label: String, @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
                        textStyle: TextStyle = TextStyle(), onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = { value ->
            onTextChanged(value)
        },
        label = { Text(label) },
        maxLines = 1,
        textStyle = textStyle,
        modifier = modifier
    )
}
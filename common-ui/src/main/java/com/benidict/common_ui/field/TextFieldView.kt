package com.benidict.common_ui.field

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import kotlin.math.sin

@Composable
fun CommonTextFieldView(
    text: String = "",
    label: String,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    onTextChanged: (String) -> Unit
) {
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
fun CommonOutlinedTextFieldView(
    text: String = "",
    label: String,
    suffix: String = "",
    prefix: String = "",
    maxCharacter: Int = 250,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardType = KeyboardType.Text,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { value ->
            if (value.length <= maxCharacter) {
                onTextChanged(value)
            }
        },
        singleLine = singleLine,
        prefix = { Text(prefix) },
        suffix = { Text(suffix) },
        label = { Text(label) },
        maxLines = 1,
        textStyle = textStyle,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardOptions
        )
    )
}
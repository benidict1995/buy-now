package com.benidict.common_ui.field

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
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
    errorMessage: String = "",
    hasError: Boolean = false,
    label: String,
    suffix: String = "",
    prefix: String = "",
    maxCharacter: Int = 250,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
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
        enabled = enabled,
        singleLine = singleLine,
        prefix = { Text(prefix) },
        suffix = { Text(suffix) },
        label = { Text(label) },
        maxLines = 1,
        visualTransformation = visualTransformation,
        textStyle = textStyle,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardOptions
        )
    )
    if (hasError) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PasswordCommonOutlinedTextFieldView(
    text: String = "",
    errorMessage: String = "",
    hasError: Boolean = false,
    label: String,
    suffix: String = "",
    prefix: String = "",
    maxCharacter: Int = 250,
    singleLine: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    onTextChanged: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(value = false) }
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
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        textStyle = textStyle,
        modifier = modifier,
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_password"
                    )
                }
            } else {
                IconButton(
                    onClick = { showPassword = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide_password"
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
    if (hasError) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 12.sp
        )
    }
}
package com.benidict.common_ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.benidict.common_ui.R
import com.benidict.common_ui.field.CommonOutlinedTextFieldView
import com.benidict.common_utils.validation.isEmailValid

@Composable
fun SearchFilterView() {
    val search = remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        CommonOutlinedTextFieldView(
            text = search.value,
            onTextChanged = {
                search.value = it
            },
            hasLeadingIcon = true,
            leadingIconResId = R.drawable.baseline_search_24,
            keyboardOptions = KeyboardType.Email,
            maxCharacter = 50,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth(),
            label = "Search Product"
        )
    }
}
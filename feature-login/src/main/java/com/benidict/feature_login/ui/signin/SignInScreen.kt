package com.benidict.feature_login.ui.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.benidict.common_ui.layout.MainLayout

@Composable
fun SignInScreen() {
    MainLayout { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {

        }
    }
}
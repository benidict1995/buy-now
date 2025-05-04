package com.benidict.feature_login.ui.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.benidict.common_ui.field.PasswordCommonOutlinedTextFieldView
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.loader.FullScreenLoaderView
import com.benidict.common_ui.theme.GrayishWhite
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EnterPasswordScreen(
    email: String,
    navController: NavController,
    onNext: () -> Unit
) {
    val viewModel = hiltViewModel<EnterPasswordViewModel>()

    val password = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }
    val showLoader = remember { mutableStateOf(false) }

    LaunchedEffect(showLoader.value) {
        if (showLoader.value) {
            delay(2000)
            viewModel.login(email, password.value)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.state.collectLatest { state ->
            when (state) {
                EnterPasswordState.NavigateToHome -> onNext()
                is EnterPasswordState.ShowError -> {
                    showLoader.value = false
                    errorMessage.value = state.error
                }
            }
        }
    }

    if (showLoader.value) {
        FullScreenLoaderView()
    } else {
        MainLayout(
            hasTopBar = true,
            hasBackButton = true,
            hasNextButton = true,
            errorMessage = errorMessage.value,
            enableNextButton = password.value.isEmpty().not(),
            onResetErrorMessage = {
                errorMessage.value = ""
            },
            onNextPressed = {
                showLoader.value = true
            },
            onBackPressed = {
                navController.popBackStack()
            },
            containerColor = GrayishWhite
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Enter Password",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(50.dp))
                PasswordCommonOutlinedTextFieldView(
                    text = password.value,
                    onTextChanged = {
                        password.value = it
                    },
                    maxCharacter = 50,
                    modifier = Modifier.fillMaxWidth(),
                    label = "Password"
                )
            }
        }
    }
}
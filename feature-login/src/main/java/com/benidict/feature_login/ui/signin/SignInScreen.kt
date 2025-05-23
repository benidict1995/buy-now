package com.benidict.feature_login.ui.signin

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.benidict.common_ui.field.CommonOutlinedTextFieldView
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.loader.FullScreenLoaderView
import com.benidict.common_ui.theme.GrayishWhite
import com.benidict.common_utils.validation.isEmailValid
import com.benidict.common_utils.validation.isPhoneValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("ContextCastToActivity")
@Composable
fun SignInScreen(
    navController: NavController,
    onNavigateToPassword: (String) -> Unit,
    onNavigateToSignUp: (String) -> Unit
) {
    val viewModel = hiltViewModel<SignInViewModel>()

    var hasEmailBeenTouched by remember { mutableStateOf(false) }
    val isEmailHasError = remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("") }

    val showLoader = remember { mutableStateOf(false) }

    LaunchedEffect(showLoader.value) {
        if (showLoader.value) {
            delay(5000)
            viewModel.checkEmailExists(email.value)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.state.collectLatest { state ->
            when (state) {
                SignInState.NavigateToSignUp -> onNavigateToSignUp(email.value)
                SignInState.NavigateToPassword -> onNavigateToPassword(email.value)
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
            enableNextButton = isEmailHasError.value.not() && hasEmailBeenTouched,
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
                Text(text = "Enter Email Address", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "Enter your email address to log in.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(50.dp))
                CommonOutlinedTextFieldView(
                    text = email.value,
                    onTextChanged = {
                        email.value = it
                        hasEmailBeenTouched = true
                        isEmailHasError.value = isEmailValid(email.value).not()
                    },
                    keyboardOptions = KeyboardType.Email,
                    maxCharacter = 50,
                    modifier = Modifier.fillMaxWidth(),
                    label = "Email Address",
                    hasError = isEmailHasError.value,
                    errorMessage = "Invalid email address."
                )
            }
        }
    }
}
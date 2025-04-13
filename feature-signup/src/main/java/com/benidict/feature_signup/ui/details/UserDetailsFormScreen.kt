package com.benidict.feature_signup.ui.details

import android.util.Log
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.benidict.common_ui.field.CommonOutlinedTextFieldView
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite
import androidx.hilt.navigation.compose.hiltViewModel
import com.benidict.common_ui.loader.FullScreenLoaderView
import com.benidict.common_utils.validation.isEmailValid
import kotlinx.coroutines.delay

@Composable
fun UserDetailsFormScreen(
    navController: NavController,
    onNext: () -> Unit
) {
    val viewModel = hiltViewModel<UserDetailsFormViewModel>()

    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val isEmailHasError = remember { mutableStateOf(false) }
    val showLoader = remember { mutableStateOf(false) }

    LaunchedEffect(showLoader.value) {
        if (showLoader.value) {
            delay(5000)
            onNext()
        }
    }

    if (showLoader.value) {
        FullScreenLoaderView()
    } else {
        MainLayout(
            hasTopBar = true,
            hasBackButton = true,
            hasNextButton = true,
            enableNextButton = viewModel.isFormFieldsValid(
                email = email.value,
                firstName = firstName.value,
                lastName = lastName.value
            ),
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
                    text = "Enter Personal Details",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(50.dp))
                CommonOutlinedTextFieldView(
                    text = firstName.value,
                    onTextChanged = {
                        firstName.value = it
                    },
                    keyboardOptions = KeyboardType.Text,
                    maxCharacter = 50,
                    modifier = Modifier.fillMaxWidth(),
                    label = "First Name"
                )

                Spacer(modifier = Modifier.height(8.dp))
                CommonOutlinedTextFieldView(
                    text = lastName.value,
                    onTextChanged = {
                        lastName.value = it
                    },
                    keyboardOptions = KeyboardType.Text,
                    maxCharacter = 50,
                    modifier = Modifier.fillMaxWidth(),
                    label = "Last Name"
                )

                Spacer(modifier = Modifier.height(8.dp))
                CommonOutlinedTextFieldView(
                    text = email.value,
                    onTextChanged = {
                        email.value = it
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
package com.benidict.feature_signup.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.benidict.common_utils.validation.isPhoneValid

@Composable
fun UserDetailsFormScreen(
    navController: NavController
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    MainLayout(
        hasTopBar = true,
        hasBackButton = true,
        hasNextButton = true,
        enableNextButton = false,
        onNextPressed = {

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
            Text(text = "Enter Personal Details", fontSize = 28.sp, fontWeight = FontWeight.Bold)
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
                },
                keyboardOptions = KeyboardType.Email,
                maxCharacter = 50,
                modifier = Modifier.fillMaxWidth(),
                label = "Email Address"
            )
        }
    }
}
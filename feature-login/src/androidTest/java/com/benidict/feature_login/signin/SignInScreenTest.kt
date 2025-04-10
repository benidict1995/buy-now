package com.benidict.feature_login.signin

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.benidict.common_ui.field.CommonOutlinedTextFieldView
import com.benidict.common_utils.validation.isPhoneValid
import com.benidict.feature_login.ui.signin.SignInScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInScreenTest {

    @get:Rule
    val rule = createComposeRule()

    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        rule.setContent {
            SignInScreen(navController = NavHostController(instrumentationContext)) { }
            val isMobileNumberIncorrect = remember { mutableStateOf(false) }
            val mobileNumber = remember { mutableStateOf("") }
            CommonOutlinedTextFieldView(
                text = mobileNumber.value,
                onTextChanged = {
                    mobileNumber.value = it
                    isMobileNumberIncorrect.value = isPhoneValid(mobileNumber.value).not()
                },
                hasError = isMobileNumberIncorrect.value,
                errorMessage = "Incorrect mobile number.",
                keyboardOptions = KeyboardType.Number,
                maxCharacter = 10,
                modifier = Modifier.fillMaxWidth().testTag("mobileNumberInputField"),
                prefix = "+63",
                label = "Mobile Number"
            )
        }
    }

    @Test
    fun checkIf_Title_and_Description_isDisplayed(){
          rule.onNodeWithText(text = "Enter Mobile Number").assertIsDisplayed()
          rule.onNodeWithText(text = "Enter your mobile number to log in.").assertIsDisplayed()
    }

    @Test
    fun checkIf_mobileNumberTextField_isInput_CorrectNumber() {
        rule.onNodeWithTag("mobileNumberInputField")
            .performClick().assertIsFocused()

        // Perform text input
        rule
            .onNodeWithTag("mobileNumberInputField")
            .performTextInput("9568997912")

        // Assert that the text is displayed
        rule
            .onNodeWithTag("mobileNumberInputField")
            .assertTextContains("9568997912")
    }
}
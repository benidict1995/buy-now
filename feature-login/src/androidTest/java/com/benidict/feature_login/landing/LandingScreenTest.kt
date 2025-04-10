package com.benidict.feature_login.landing

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.benidict.common_ui.button.CommonButtonView
import com.benidict.common_ui.button.CommonOutlinedButtonView
import com.benidict.feature_login.ui.landing.LandingScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LandingScreenTest {

    @get:Rule
    val rule = createComposeRule()

    private var continueButtonWasClicked = false

    private var guestButtonWasClicked = false

    @Before
    fun setup() {
        rule.setContent {
            LandingScreen {

            }

            CommonButtonView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("continueButton"),
                buttonLabel = "Continue"
            ) {
                continueButtonWasClicked = true
            }

            CommonOutlinedButtonView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("guestButton"),
                buttonLabel = "Continue as a Guest"
            ) {
                guestButtonWasClicked = false
            }
        }
    }

    @Test
    fun checkIf_ConstantText_isDisplayed() {
        rule.onNodeWithText("Buy now").assertIsDisplayed()
        rule.onNodeWithText("OR").assertIsDisplayed()
    }

    @Test
    fun checkIf_ContinueButton_isWorking_As_Expected() {
        // Assert text display
        rule.onNodeWithTag("continueButton")
            .assertIsDisplayed()
            .assertTextEquals("Continue")

        // Perform the click
        rule.onNodeWithTag("continueButton")
            .assertHasClickAction()
            .performClick()

        // Assert the click changed the state
        //assert(continueButtonWasClicked)
    }

    @Test
    fun checkIf_GuestButton_isWorking_As_Expected() {
        //Assert text display
        rule.onNodeWithTag("guestButton")
            .assertIsDisplayed()
            .assertTextEquals("Continue as a Guest")

        // Perform the click
        rule.onNodeWithTag("guestButton")
            .assertHasClickAction()
            .performClick()

        //Assert the click changed the state
        //assert(guestButtonWasClicked)
    }
}
package com.benidict.feature_login.ui.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import com.benidict.common_ui.layout.MainLayout
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benidict.common_ui.button.CommonButtonView
import com.benidict.common_ui.button.CommonOutlinedButtonView

@Composable
fun LandingScreen(onContinue: () -> Unit) {
    MainLayout { paddingValue ->
        Column(
            modifier = Modifier.padding(paddingValue)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Buy now", fontSize = 36.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(100.dp))
            CommonButtonView(
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp),
                buttonLabel = "Continue"
            ) {
               onContinue()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.width(150.dp))
                Text(text = "OR", fontWeight = FontWeight.Normal, fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp))
                HorizontalDivider(thickness = 1.dp, modifier = Modifier.width(150.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            CommonOutlinedButtonView(
                modifier = Modifier.fillMaxWidth()
                    .height(56.dp),
                buttonLabel = "Continue as a Guest"
            ) {

            }
        }
    }
}
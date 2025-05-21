package com.benidict.common_ui.bottomnav

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomNavigationView(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    content: @Composable RowScope.() -> Unit
) {
    val shape = remember {
        GenericShape { size, _ ->
            val height = size.height
            val width = size.width
            val sideRadius = (height / 2f)
            val curveRadius = 80f
            val centerX = width / 2

            moveTo(0f, 0f)
            lineTo(centerX - curveRadius * 2, 0f)

            cubicTo(
                centerX - curveRadius, 0f,
                centerX - curveRadius, curveRadius,
                centerX, curveRadius
            )
            cubicTo(
                centerX + curveRadius, curveRadius,
                centerX + curveRadius, 0f,
                centerX + curveRadius * 2, 0f
            )
            arcTo(
                rect = Rect(
                    left = width - 2 * sideRadius,
                    top = 0f,
                    right = width,
                    bottom = height
                ),
                startAngleDegrees = -90f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(
                    left = 0f,
                    top = 0f,
                    right = 2 * sideRadius,
                    bottom = height
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            close()

        }
    }

    Box(
        modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = 16.dp)
            .background(color = backgroundColor, shape = shape),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
    Spacer(modifier = Modifier.height(130.dp))
}
package com.benidict.feature_product.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.benidict.common_ui.addtocart.QuantityPickerView
import com.benidict.common_ui.icon.CircleClose
import com.benidict.common_ui.icon.CircleFavorite
import com.benidict.common_ui.icon.CircleShare
import com.benidict.common_ui.image.ImageLoader
import com.benidict.common_ui.layout.MainLayout
import com.benidict.common_ui.theme.GrayishWhite
import com.benidict.common_utils.transform.convertToPeso
import com.benidict.common_utils.view.transparentLinearBrush
import com.benidict.common_utils.view.transparentVerticalBrush

@Composable
fun ProductDetailsScreen(navController: NavHostController, productId: Int) {
    val viewModel = hiltViewModel<ProductDetailsViewModel>()
    val product = viewModel.productState.collectAsState()
    val quantity = remember { mutableIntStateOf(1) }

    LaunchedEffect(productId) {
        viewModel.loadProductDetails(productId)
    }
    MainLayout(
        containerColor = GrayishWhite
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    CircleClose {
                        navController.popBackStack()
                    }

                    Row(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        CircleShare {

                        }
                        CircleFavorite {
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    ImageLoader(
                        url = product.value.productImageUrl,
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxWidth()
                            .height(400.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Text(
                            text = product.value.productName,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 10.dp)
                        )
                        Text(
                            text = product.value.weight,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = product.value.price.convertToPeso(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Text(
                            text = "Description",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(16.dp, top = 10.dp)
                        )
                        Text(
                            text = product.value.productDescription,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(16.dp, vertical = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
            Box(
                modifier = Modifier
                    .background(
                        brush = transparentLinearBrush(
                            colors = listOf(Color.White, Color.White),
                            alphaPercent = .80f
                        )
                    )
                    .align(Alignment.BottomCenter)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .align(Alignment.TopCenter)
                        .background(
                            brush = transparentVerticalBrush(
                                colors = listOf(Color.Black, Color.Transparent),
                                alphaPercent = 0.15f
                            )
                        )
                )
                Log.d("makerChecker", "productDetails:${product.value}")
                QuantityPickerView(
                    quantity = product.value.quantity,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(vertical = 10.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onUpdateQuantity = {
                        quantity.intValue = it
                    },
                    onAddToCart = {
                        viewModel.addToCart(
                            productId = productId,
                            quantity = quantity.intValue
                        )
                    })
            }
        }
    }
}
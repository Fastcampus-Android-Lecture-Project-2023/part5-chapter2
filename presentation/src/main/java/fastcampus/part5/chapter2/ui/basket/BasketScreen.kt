package fastcampus.part5.chapter2.ui.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fastcampus.part5.chapter2.ui.component.Price
import fastcampus.part5.chapter2.ui.popupSnackBar
import fastcampus.part5.chapter2.ui.theme.Purple200
import fastcampus.part5.chapter2.utils.NumberUtils
import fastcampus.part5.chapter2.viewmodel.basket.BasketAction
import fastcampus.part5.chapter2.viewmodel.basket.BasketEvent
import fastcampus.part5.chapter2.viewmodel.basket.BasketViewModel
import fastcampus.part5.di.R
import fastcampus.part5.domain.model.BasketProduct
import fastcampus.part5.domain.model.Product
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BasketScreen(
    scaffoldState: ScaffoldState,
    viewModel: BasketViewModel = hiltViewModel()
) {
    val basketProducts by viewModel.basketProducts.collectAsState(initial = listOf())
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is BasketEvent.ShowSnackBar -> {
                    popupSnackBar(scope, scaffoldState, "결제 되었습니다.")
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(basketProducts.size) { index ->
                BasketProductCard(basketProduct = basketProducts[index]) {
                    viewModel.dispatch(BasketAction.RemoveProduct(it))
                }
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.dispatch(BasketAction.CheckoutBasket(basketProducts))
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Purple200
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Check, "CheckIcon")

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                fontSize = 16.sp,
                text = "${getTotalPrice(basketProducts)} 결제하기."
            )
        }
    }
}

@Composable
fun BasketProductCard(basketProduct: BasketProduct, removeProduct: (Product) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_image),
                "description",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(120.dp)
                    .aspectRatio(1f)
            )
            Column(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1f)
            ) {
                Text(
                    fontSize = 14.sp,
                    text = basketProduct.product.shop.shopName,
                    modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                )

                Text(
                    fontSize = 14.sp,
                    text = basketProduct.product.productName,
                    modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
                )
                Price(product = basketProduct.product)
            }
            Text(
                text = "${basketProduct.count} 개",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp)
            )
        }
        IconButton(
            onClick = { removeProduct(basketProduct.product) },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(Icons.Filled.Close, "CloseIcon")
        }
    }
}

private fun getTotalPrice(list: List<BasketProduct>): String {
    val totalPrice = list.sumOf { it.product.price.finalPrice * it.count }
    return NumberUtils.numberFormatPrice(totalPrice)
}
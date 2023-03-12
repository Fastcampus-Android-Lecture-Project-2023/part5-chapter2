package fastcampus.part5.chapter2.ui.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fastcampus.part5.chapter2.ui.component.ProductCard
import fastcampus.part5.chapter2.viewmodel.category.CategoryViewModel
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Product

@Composable
fun CategoryScreen(
    category: Category,
    navHostController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()
    LaunchedEffect(key1 = category) {
        viewModel.updateCategory(category)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(products.size) { index ->
            ProductCard(navHostController = navHostController, presentationVM = products[index])
        }
    }
}
package fastcampus.part5.chapter2.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fastcampus.part5.chapter2.ui.component.BannerCard
import fastcampus.part5.chapter2.ui.component.BannerListCard
import fastcampus.part5.chapter2.ui.component.CarouselCard
import fastcampus.part5.chapter2.ui.component.ProductCard
import fastcampus.part5.chapter2.viewmodel.MainViewModel
import fastcampus.part5.di.R
import fastcampus.part5.domain.model.Banner
import fastcampus.part5.domain.model.BannerList
import fastcampus.part5.domain.model.Carousel
import fastcampus.part5.domain.model.ModelType
import fastcampus.part5.domain.model.Product


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(cells = GridCells.Fixed(columnCount)) {
        items(modelList.size, span = { index ->
            val item = modelList[index]
            val spanCount = getSpanCountByType(item.type, columnCount)

            GridItemSpan(spanCount)
        }) {
            when (val item = modelList[it]) {
                is Banner -> BannerCard(model = item) { model ->
                    viewModel.openBanner(model)
                }
                is BannerList -> BannerListCard(model = item) { model ->
                    viewModel.openBannerList(model)
                }
                is Product -> ProductCard(product = item) { model ->
                    viewModel.openProduct(model)
                }
                is Carousel -> CarouselCard(model = item) { model ->
                    viewModel.openCarouselProduct(model)
                }
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST,
        ModelType.CAROUSEL -> defaultColumnCount
    }
}

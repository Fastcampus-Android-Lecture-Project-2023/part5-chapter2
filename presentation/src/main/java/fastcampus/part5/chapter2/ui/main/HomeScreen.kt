package fastcampus.part5.chapter2.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import fastcampus.part5.chapter2.model.BannerListVM
import fastcampus.part5.chapter2.model.BannerVM
import fastcampus.part5.chapter2.model.CarouselVM
import fastcampus.part5.chapter2.model.ProductVM
import fastcampus.part5.chapter2.model.RankingVM
import fastcampus.part5.chapter2.ui.component.BannerCard
import fastcampus.part5.chapter2.ui.component.BannerListCard
import fastcampus.part5.chapter2.ui.component.CarouselCard
import fastcampus.part5.chapter2.ui.component.ProductCard
import fastcampus.part5.chapter2.ui.component.RankingCard
import fastcampus.part5.chapter2.viewmodel.MainViewModel
import fastcampus.part5.domain.model.Banner
import fastcampus.part5.domain.model.BannerList
import fastcampus.part5.domain.model.Carousel
import fastcampus.part5.domain.model.ModelType
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.Ranking


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainHomeScreen(navController: NavHostController, viewModel: MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    val testId = "ca-app-pub-3940256099942544/6300978111"
    val adId = "ca-app-pub-3897260489773203/2972373963"
    val adRequest = AdRequest.Builder().build()

    LazyVerticalGrid(cells = GridCells.Fixed(columnCount)) {
        items(modelList.size, span = { index ->
            val item = modelList[index]
            val spanCount = getSpanCountByType(item.model.type, columnCount)

            GridItemSpan(spanCount)
        }) {
            when (val item = modelList[it]) {
                is BannerVM -> BannerCard(presentationVM = item)
                is BannerListVM -> BannerListCard(presentationVM = item)
                is ProductVM -> ProductCard(navHostController = navController, presentationVM = item)
                is CarouselVM -> CarouselCard(navHostController = navController, presentationVM = item)
                is RankingVM -> RankingCard(navHostController = navController, presentationVM = item)
            }
        }
    }

    AndroidView(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        factory = {
            AdView(it).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = testId
                loadAd(adRequest)
            }
        }, update = {
            it.loadAd(adRequest)
        })

}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
    return when (type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST,
        ModelType.CAROUSEL, ModelType.RANKING -> defaultColumnCount
    }
}

package fastcampus.part5.chapter2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import fastcampus.part5.chapter2.model.BannerListVM
import fastcampus.part5.di.R
import fastcampus.part5.domain.model.BannerList
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun BannerListCard(presentationVM: BannerListVM) {
    val pagerState = rememberPagerState()
    LaunchedEffect(key1 = pagerState) {
        autoScrollInfinity(pagerState)
    }
    HorizontalPager(count = presentationVM.model.imageList.size, state = pagerState) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .shadow(20.dp),
            onClick = { presentationVM.openBannerList(presentationVM.model.bannerId) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_image),
                "description",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f)
            )
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("pageNumber : $it")
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
private suspend fun autoScrollInfinity(pagerState: PagerState) {
    while (true) {
        delay(3000)
        val currentPage = pagerState.currentPage
        var nextPage = currentPage + 1
        if (nextPage >= pagerState.pageCount) {
            nextPage = 0
        }
        pagerState.animateScrollToPage(nextPage)
    }
}
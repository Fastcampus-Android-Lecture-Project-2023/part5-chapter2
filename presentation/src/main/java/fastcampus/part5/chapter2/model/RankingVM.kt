package fastcampus.part5.chapter2.model

import androidx.navigation.NavHostController
import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.Ranking

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) : PresentationVM<Ranking>(model) {

    fun openRankingProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendRankingLog()
        //+@
    }

    fun likeProduct(product: Product) {
        productDelegate.likeProduct(product)
    }

    private fun sendRankingLog() {

    }

}
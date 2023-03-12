package fastcampus.part5.chapter2.model

import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.Ranking

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) : PresentationVM() {

    fun openRankingProduct(product: Product) {
        productDelegate.openProduct(product)
        sendRankingLog()
        //+@
    }

    private fun sendRankingLog() {

    }

}
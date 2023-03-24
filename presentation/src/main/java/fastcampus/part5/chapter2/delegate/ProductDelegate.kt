package fastcampus.part5.chapter2.delegate

import androidx.navigation.NavHostController
import fastcampus.part5.domain.model.Product

interface ProductDelegate {
    fun openProduct(navHostController: NavHostController, product: Product)

    fun likeProduct(product: Product)
}

package fastcampus.part5.chapter2.model

import androidx.navigation.NavHostController
import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.domain.model.Carousel
import fastcampus.part5.domain.model.Product

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate): PresentationVM<Carousel>(model) {

    fun openCarouselProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendCarouselLog()
    }

    fun likeProduct(product: Product) {
        productDelegate.likeProduct(product)
    }

    fun sendCarouselLog(){

    }
}
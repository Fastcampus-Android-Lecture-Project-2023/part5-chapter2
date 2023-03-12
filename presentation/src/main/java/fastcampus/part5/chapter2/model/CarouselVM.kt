package fastcampus.part5.chapter2.model

import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.domain.model.Carousel
import fastcampus.part5.domain.model.Product

class CarouselVM(model: Carousel,private val productDelegate: ProductDelegate): PresentationVM<Carousel>(model) {

    fun openCarouselProduct(product: Product) {
        productDelegate.openProduct(product)
        sendCarouselLog()
    }
    fun sendCarouselLog(){

    }
}
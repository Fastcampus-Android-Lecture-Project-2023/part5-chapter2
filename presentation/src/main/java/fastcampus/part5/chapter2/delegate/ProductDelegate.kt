package fastcampus.part5.chapter2.delegate

import fastcampus.part5.domain.model.Product

interface ProductDelegate {
    fun openProduct(product: Product)
}